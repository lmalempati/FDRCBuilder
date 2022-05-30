package fdrc.http;

import fdrc.common.Constants;
import fdrc.common.Serialization;
import fdrc.service.FDRCRequestService;
import fdrc.types.HttpMethod;
import fdrc.utils.Utils;
import fdrc.xml.*;
import java.math.BigInteger;

public class HTTPPostHandler {
    /* The below method will take the XML request and returns the XML response received from Data wire.
     * */
    @SuppressWarnings("deprecation")
    public String Submit(String gmfrequest) {
        String response = "";
        /* Create the instance of the Request that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        ObjectFactory factory = new ObjectFactory();
        Request gmfTransactionRequest = factory.createRequest();

        /* Create the instance of the TransactionType that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        TransactionType transactionType = factory.createTransactionType();
        transactionType.setServiceID(Constants.SERVICE_ID);

        /* Create the instance of PayloadType that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        PayloadType payloadType = new PayloadType();
        payloadType.setEncoding("cdata");
        payloadType.setValue(gmfrequest);
        transactionType.setPayload(payloadType);
        gmfTransactionRequest.setTransaction(transactionType);
        /* Create the instance of ReqClientIDType that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        ReqClientIDType reqClientIDType = new ReqClientIDType();
        reqClientIDType.setApp(Constants.APP);
        reqClientIDType.setAuth(String.format("%s%s|%s", FDRCRequestService.getRcRequest().groupID, FDRCRequestService.getRcRequest().merchantMID, FDRCRequestService.getRcRequest().termID)); // todo: user termid off Request
        /* Set the clientRef value*/
        reqClientIDType.setClientRef(Utils.getClientRef(FDRCRequestService.getRcRequest().tppID));
        /* Set the DID value*/
        reqClientIDType.setDID(FDRCRequestService.getRcRequest().dataWireID);

        gmfTransactionRequest.setReqClientID(reqClientIDType);
        /*Set client timeout value*/
        gmfTransactionRequest.setClientTimeout(new BigInteger("35"));
        gmfTransactionRequest.setVersion("3");

        // Transform the gmfTransactionRequest object into XML string.
        String gmffomattedRequest = Serialization.getXmlFromObj(gmfTransactionRequest, null);

        try {
            /* Call executeMethod to post the data to the designated URL. */
            return Utils.upload(Constants.STG_POST_URL, gmffomattedRequest, HttpMethod.POST);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}