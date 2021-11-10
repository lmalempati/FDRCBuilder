package fdrc.client;

import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import com.fiserv.merchant.gmfv10.RejectResponseDetails;
import fdrc.Exceptions.InvalidRequest;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.RequestUtils;
import fdrc.common.Serialization;
import fdrc.http.HTTPPostHandler;
import fdrc.utils.Utils;

import javax.xml.ws.http.HTTPException;

public abstract class GenericRequest {
    GMFMessageVariants gmfmv = new GMFMessageVariants();

    public GMFMessageVariants submitRequest(GMFMessageVariants gmfmv, Response response) {
        String requestXml = "";
        String responseString = "";
        GMFMessageVariants gmfmvResponse = null;
        String  errorMsg = "";
        Serialization serialization = new Serialization();
        requestXml = serialization.getXMLData (gmfmv, errorMsg);
        if (!errorMsg.equals("")) throw new InvalidRequest(errorMsg); // todo:

        System.out.println("GMF Request == " + requestXml); // todo: debug purpose only
        String clientRef = RequestUtils.getClientRef();
        try {

            responseString = new HTTPPostHandler().SendMessage(requestXml, clientRef);
            if (responseString == "") {
                throw new InvalidResponseXml("Empty response");
            }
            responseString = responseString.replaceAll("&gt;", ">").replaceAll("&lt;", "<");
            response.responseRaw = responseString; // debug purpose only
            gmfmvResponse = serialization.getObjectXML(responseString);
        } catch (HTTPException e) {
            response.errorMsg = e.getMessage();
        }
        System.out.println("Successful HTTP POST response: " + "\n" + responseString + "\n"); // todo: debug purpose only
        return gmfmvResponse;
    }

    public abstract String buildRequest(final Request request);

    public abstract boolean getResponse(GMFMessageVariants gmfmvResponse,Response response);

    public Response processRequest(Request request) {
        String anyError = null;
        String requestString = "";
        String responseString = "";
        Response response = new Response();
        String error = buildRequest(request);
        if (Utils.isNotNullOrEmpty(error))
            return new Response(error);
        GMFMessageVariants responseObj = submitRequest(gmfmv, response);
        if (response.errorMsg != null) {
            return response;
        }
        // if rejection, sed the error msg in response
        if (responseObj.getRejectResponse() != null){
            RejectResponseDetails responseDetails = responseObj.getRejectResponse();
            response.errorMsg = responseDetails.getRespGrp().getErrorData();
            return response;
        }
        try {
            if (!getResponse(responseObj, response))
                response.errorMsg = "Error while parsing Response";
        } catch (HTTPException e) {
            response.errorMsg = e.getMessage();
        } catch (Exception e) {
            response.errorMsg = e.getMessage();
        }
        return response;
    }

}
