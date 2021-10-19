package fdrc.client;

import javax.xml.ws.http.HTTPException;
import java.io.Serializable;
import java.util.List;

import fdrc.base.Response;
import fdrc.common.FiServRequest;
import fdrc.base.Request;
import fdrc.common.Serialization;
import fdrc.http.HTTPPostHandler;
import fdrc.proxy.*;

public class CreditRequest implements Serializable {

    GMFMessageVariants gmfmv = new GMFMessageVariants();
    /* This class is generated from XSD file */
    CreditRequestDetails creditReqDtl = new CreditRequestDetails();
    FiServRequest fiServRequest = null;

    public String buildRequest(final Request request) {
        String errorMsg = "";
        FiServRequest fiServRequest = new FiServRequest(request);
        try {
            OrigAuthGrp origAuthGrp = fiServRequest.getOrigAuthGrp();
            creditReqDtl.setOrigAuthGrp(origAuthGrp);

            creditReqDtl.setCommonGrp(fiServRequest.getCommonGrp());

            creditReqDtl.setAltMerchNameAndAddrGrp(fiServRequest.getAltMerchNameAndAddrGrp());

            creditReqDtl.setCardGrp(fiServRequest.getCardGrp());
            // CardTypeType.valueOf(request.cardInfo.cardType.toUpperCase())
            switch (CardTypeType.fromValue(request.cardType)) {
                case VISA:
                    creditReqDtl.setVisaGrp(fiServRequest.getVisaGrp());
                    break;
                case MASTER_CARD:
                    creditReqDtl.setMCGrp(fiServRequest.getMasterCardGrp());
                    break;
                case JCB:
                    if (TxnTypeType.fromValue(request.txnType) == TxnTypeType.COMPLETION)
                        creditReqDtl.setDSGrp(fiServRequest.getDiscoverGrp());
                    break;
            }

            /* Addtl Amount Group
             * Getting the reference object of the AddtlAmtGrp list and add the
             * AddtlAmtGrp object to the list
             */
            List<AddtlAmtGrp> addtlAmtGr = creditReqDtl.getAddtlAmtGrp();
            List<AddtlAmtGrp> addlGrps = fiServRequest.getAddtlAmtGrp();
//            if (request.addtlAmtInfo != null) {
            if (addlGrps != null)
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGr.add(grp);
                }
//            }
            /* ECommerce Group */
            creditReqDtl.setEcommGrp(fiServRequest.getEcommGrp());

            /* CustInfoGrp Group
             * Assign the CustInfoGrp Group object to the property of CreditSaleRequest object */
            creditReqDtl.setCustInfoGrp(fiServRequest.getCustInfoGrp());
            /* Add the credit request object to GMF message variant object */
            gmfmv.setCreditRequest(creditReqDtl);
        } catch (IllegalArgumentException e) {
            errorMsg = e.getMessage();
        } catch (RuntimeException e) {
            errorMsg = e.getMessage();
        } finally {
            fiServRequest = null;
        }
        return errorMsg;
    }

    /*Transaction response in XML format received from Data wire */
    public Response processRequest(Request request) {
        String requestString = "";
        String responseString = "";
        Response response = null;
        String error = buildRequest(request);
        if (error != "")
            return new Response(error);

        requestString = getXMLData();
        requestString = requestString.replaceAll("gmfMessageVariants", "GMF");
        System.out.println("GMF Credit Request == " + requestString);

        /*Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
        String clientRef = GetClientRef();
        try {
            //Send data using HTTP POST protocol
            // todo: set any exception to Resposne.errorMsg
            responseString = new HTTPPostHandler().SendMessage(requestString, clientRef);
//            CreditResponseDetails responseDetails =
            response = getResponse(responseString);
        } catch (HTTPException e) {
            System.out.println("HTTP Exception: " + e);
        }
        System.out.println("Successful HTTP POST Credit response: " + "\n" + responseString + "\n");
        return response;
    }

    /* Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
    public String GetClientRef() {
        String clientRef = "";

        creditReqDtl = gmfmv.getCreditRequest();
        clientRef = creditReqDtl.getCommonGrp().getSTAN() + "|" + creditReqDtl.getCommonGrp().getTPPID();
        clientRef = "00" + clientRef;

        return clientRef;
    }

    private String getXMLData() {
        Serialization serialization = new Serialization();
        return serialization.GetXMLData(gmfmv);
    }

    private Response getResponse(String xml) {
        Response response = new Response();
        Serialization serialization = new Serialization();
        GMFMessageVariants gmfMessageVariants = serialization.getObjectXML(xml);
        response.respCode = gmfMessageVariants.getCreditResponse().getRespGrp().getRespCode();
        response.addtlRespData = gmfMessageVariants.getCreditResponse().getRespGrp().getAddtlRespData();

//        response.respCode = xml.substring(xml.lastIndexOf("<RespCode>") + "<RespCode>".length(), xml.lastIndexOf("</RespCode>"));
//        if (response.respCode.equals("000"))
//            response.respCode = xml.substring(xml.lastIndexOf("<AddtlRespData>") + "<AddtlRespData>".length(), xml.lastIndexOf("</AddtlRespData>"));
        return response;
    }

}
