package fdrc.client;

import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import com.fiserv.merchant.gmfv10.RejectResponseDetails;
import fdrc.Exceptions.InvalidRequest;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;
import fdrc.common.Serialization;
import fdrc.http.HTTPPostHandler;
import fdrc.utils.Utils;


abstract class GenericRequest {
    GMFMessageVariants gmfmv = new GMFMessageVariants();

    public GMFMessageVariants submitRequest(GMFMessageVariants gmfmv, Response response) {
        String requestXml = "";
        String responseString = "";
        GMFMessageVariants gmfmvResponse = null;
        String  errorMsg = "";
        Serialization serialization = new Serialization();
        requestXml = serialization.getXMLPayload(gmfmv, errorMsg);
        /* Jackson does not support */
//        serialization.validateXMLSchema(requestXml);

        if (!errorMsg.equals("")) throw new InvalidRequest(errorMsg); // todo:

        System.out.println("GMF Request == " + requestXml); // todo: debug purpose only
        try {
           responseString = new HTTPPostHandler().SendMessage(requestXml);
            if (responseString == null || responseString.equals("")) {
                throw new InvalidResponseXml("Empty response");
            }
            responseString = responseString.replaceAll("&gt;", ">").replaceAll("&lt;", "<");
            response.responseRaw = responseString; // todo: debug purpose only
            gmfmvResponse = serialization.getObjectXML(responseString);
        } catch (Exception e) {
            response.errorMsg = e.getMessage();
        }
        System.out.println("Successful HTTP POST response: " + "\n" + responseString + "\n"); // todo: debug purpose only
        return gmfmvResponse;
    }

    public abstract String buildRequest(final Request request, FiServRequest fiServRequest);

    public abstract boolean getResponse(GMFMessageVariants gmfmvResponse,Response response);

    public Response processRequest(Request request) {
        FiServRequest fiServRequest = new FiServRequest(request);
        Response response = new Response();

        String errorMsg = null;
        try {
            errorMsg = buildRequest(request, fiServRequest);
        } catch (IllegalArgumentException | UnsupportedEnumValueException e) {
            errorMsg = e.getMessage();
        } catch (Exception e) {
            errorMsg = e.getMessage();
        } finally {
            fiServRequest = null;
        }
        if (Utils.isNotNullOrEmpty(errorMsg))
            return new Response(errorMsg);

        GMFMessageVariants gmfResponse = submitRequest(gmfmv, response);

        if (response.errorMsg != null) {
            return response;
        }
        // if rejection, send in errorMsg msg of response
        if (gmfResponse.getRejectResponse() != null){
            RejectResponseDetails responseDetails = gmfResponse.getRejectResponse();
            response.errorMsg = responseDetails.getRespGrp().getErrorData();
            return response;
        }

        try {
            if (!getResponse(gmfResponse, response))
                response.errorMsg = "Error while parsing Response";
        }
        catch (Exception e) {
            response.errorMsg = e.getMessage();
        }
        return response;
    }

}
