package fdrc.model;

import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import com.fiserv.merchant.gmfv10.RejectResponseDetails;
import fdrc.Exceptions.InvalidRequest;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.Constants;
import fdrc.base.RCRequest;
import fdrc.base.RCResponse;
import fdrc.common.FDRCRequestService;
import fdrc.common.Serialization;
import fdrc.http.HTTPPostHandler;
import fdrc.utils.Utils;

import java.util.Arrays;


abstract class GenericService {
    GMFMessageVariants gmfmv = new GMFMessageVariants();
    RCResponse rcResponse = null;

    abstract String buildRequest(final RCRequest RCRequest, FDRCRequestService FDRCRequestService);

    abstract boolean getResponse(GMFMessageVariants gmfmvResponse, RCRequest request, RCResponse response);

    /* Verify if a TOR needs to be generated. */
    private boolean torRequired() {
        if (rcResponse == null) return true;
        if (Arrays.asList(Constants.ReturnCodesToReverse).contains(rcResponse.DWReturnCode)) return true;
        if (Arrays.asList(Constants.StatusCodesToReverse).contains(rcResponse.DWStatusCode)) return true;
        if (!rcResponse.DWReturnCode.equals("000") && !rcResponse.DWStatusCode.equals("AuthenticationError"))
            return true;
        return false;
    }

    private void submitWithTOR(int torCount) {
        String requestXml = "";
        String errorMsg = "";
//        Serialization serialization = new Serialization();
        requestXml = Serialization.getXmlObject(gmfmv, errorMsg);
        /* Jackson does not support */
//        serialization.validateXMLSchema(requestXml);

        if (!errorMsg.equals("")) throw new InvalidRequest(errorMsg); // todo:

        System.out.println("GMF Request == " + requestXml); // debug purpose only
//        for (int i = 0; i < torCount; i++) {
        try {
            rcResponse = new HTTPPostHandler().Submit(requestXml);
//                rcResponse.responseRaw = responseString; // todo: debug purpose only
            if (torRequired())
                rcResponse.addtlRespData = "Timeout";

//            responseString = responseString.replaceAll("&gt;", ">").replaceAll("&lt;", "<");
        } catch (Exception e) {
            rcResponse.errorMsg = e.getMessage();
        }
//        } // for
    }

    RCResponse processRequest(RCRequest request) {
        FDRCRequestService FDRCRequestService = new FDRCRequestService(request);

        String errorMsg = null;
        try {
            errorMsg = buildRequest(request, FDRCRequestService);
        } catch (IllegalArgumentException | UnsupportedEnumValueException e) {
            errorMsg = e.getMessage();
        } catch (Exception e) {
            errorMsg = e.getMessage();
        } finally {
//            RCService = null;
        }
        if (Utils.isNotNullOrEmpty(errorMsg))
            return new RCResponse(errorMsg);

        submitWithTOR(3);

        if (rcResponse.errorMsg != null) {
            return rcResponse;
        }
        if (rcResponse.responseRaw.equals("")) {
            throw new InvalidResponseXml("Empty response");
        }
        System.out.println("Successful HTTP POST response: " + "\n" + rcResponse.responseRaw + "\n"); // todo: debug purpose only
        GMFMessageVariants gmfResponse = Serialization.getObjectXML(rcResponse.responseRaw);

        // if rejection, send in errorMsg msg of response
        if (gmfResponse.getRejectResponse() != null) {
            RejectResponseDetails responseDetails = gmfResponse.getRejectResponse();
            rcResponse.errorMsg = responseDetails.getRespGrp().getErrorData();
            return rcResponse;
        }

        try {
            if (!getResponse(gmfResponse, request, rcResponse))
                rcResponse.errorMsg = "Error while parsing Response";
        } catch (Exception e) {
            rcResponse.errorMsg = e.getMessage();
        }
        return rcResponse;
    }

}
