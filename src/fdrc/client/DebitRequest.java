package fdrc.client;

import fdrc.base.IRequestProcessor;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;
import fdrc.common.RequestUtils;
import fdrc.common.Serialization;
import fdrc.http.HTTPPostHandler;
import com.fiserv.merchant.gmfv10.*;

import javax.xml.ws.http.HTTPException;
import java.io.Serializable;
import java.util.List;

public class DebitRequest extends GenericRequest implements Serializable, IRequestProcessor {
    /* builds request object, if */
    public String buildRequest(Request request) {
        FiServRequest fiServRequest = null;
        DebitRequestDetails debitReqDtl = null;
        try {
            fiServRequest = new FiServRequest(request);
            debitReqDtl = new DebitRequestDetails();
            debitReqDtl.setCommonGrp(fiServRequest.getCommonGrp());
            /* Card Group */
            /* Populate values for Card Group */
            debitReqDtl.setCardGrp(fiServRequest.getCardGrp());

            List<AddtlAmtGrp> addtlAmtGr = debitReqDtl.getAddtlAmtGrp();
            List<AddtlAmtGrp> addlGrps = fiServRequest.getAddtlAmtGrp();
            if (addlGrps != null)
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGr.add(grp);
                }
            debitReqDtl.setPINGrp(fiServRequest.getPINGrp());
            /* Add the Debit request object to GMF message variant object */
            gmfmv.setDebitRequest(debitReqDtl);
            return "";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            fiServRequest = null;
        }
    }

//    @Override
//    public Response processRequest(Request request) {
//        String requestString = "";
//        String responseString = "";
//        Response response = null;
//        String error = buildRequest(request);
//        if (error != "")
//            return new Response(error);
//
//        requestString = RequestUtils.getXMLData(gmfmv);
////        requestString = requestString.replaceAll("gmfMessageVariants", "GMF");
//        System.out.println("GMF Credit Request == " + requestString);
//
//        /*Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
//        String clientRef = RequestUtils.getClientRef();
//        try {
//            //Send data using HTTP POST protocol
//            // todo: set any exception to Resposne.errorMsg
//            responseString = new HTTPPostHandler().SendMessage(requestString, clientRef);
//            response = getResponse(responseString);
//            response.responseRaw = responseString;
//        } catch (HTTPException e) {
//            System.out.println("HTTP Exception: " + e);// todo remove / handle it
//            response.errorMsg = e.getMessage();
//        }
//        System.out.println("Successful HTTP POST Credit response: " + "\n" + responseString + "\n");
//        return response;
//    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, Response response) {
        boolean result = false;
        if (gmfmvResponse.getDebitResponse() == null) {
            throw new RuntimeException("invalid response");
        }
        DebitResponseDetails debitResponse = gmfmvResponse.getDebitResponse();
        response.respCode = debitResponse.getRespGrp().getRespCode();
        response.addtlRespData = debitResponse.getRespGrp().getAddtlRespData();

        response.origAuthID = debitResponse.getRespGrp().getAuthID();
        response.origSTAN = debitResponse.getCommonGrp().getSTAN();
        response.origLocalDateTime = debitResponse.getCommonGrp().getLocalDateTime();
        response.origTranDateTime = debitResponse.getCommonGrp().getTrnmsnDateTime();
        response.origRespCode = debitResponse.getRespGrp().getRespCode();
        result = true;
        return result;
    }

}
