package fdrc.http;

import fdrc.Exceptions.InvalidResponseXml;
import fdrc.common.Constants;
import fdrc.service.FDRCRequestService;
import fdrc.common.Serialization;
import fdrc.utils.Utils;
import fdrc.xml.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.math.BigInteger;

public class HTTPPostHandler {
    /* The below method will take the XML request and returns the XML response received from Data wire.
     * */
    @SuppressWarnings("deprecation")
    public String Submit(String gmfrequest) throws IOException {
        String response = "";
        /* Create the instance of the Request that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        ObjectFactory factory = new ObjectFactory();
        Request gmfTransactionRequest = factory.createRequest();

        /* Create the instance of the TransactionType that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        TransactionType transactionType = factory.createTransactionType();
        transactionType.setServiceID("160");

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
        reqClientIDType.setApp("RAPIDCONNECTSRS");
        reqClientIDType.setAuth(String.format("%s%s|%s", FDRCRequestService.getRcRequest().groupID, FDRCRequestService.getRcRequest().merchantMID, Constants.REQUEST_TERMID)); // todo: user termid off Request
        /* Set the clientRef value*/
        reqClientIDType.setClientRef(Utils.getClientRef());
        /* Set the DID value*/
        reqClientIDType.setDID(Utils.mapMidToDID(FDRCRequestService.getRcRequest().merchantMID));

        gmfTransactionRequest.setReqClientID(reqClientIDType);
        /*Set client timeout value*/
        gmfTransactionRequest.setClientTimeout(new BigInteger("30"));
        gmfTransactionRequest.setVersion("3");

        String gmffomattedRequest = "";
        //Transform the gmfTransactionRequest object into XML string.
        gmffomattedRequest = Serialization.getXmlObject(gmfTransactionRequest, null);

        /* URL that will consume the transaction request.*/
        final String postURL = Constants.STG_POST_URL;
        final PostMethod post = new PostMethod(postURL);
        final HttpClient httpclient = new HttpClient();
        /*Set various parameters of HTTP requet header*/
        post.setRequestHeader("User-Agent", "TRR-Formatter");
        post.setRequestHeader("Host", postURL);
        post.setRequestHeader("Connection", "Keep-Alive");
        post.setRequestHeader("Cache-Control", "no-cache");
        post.setRequestHeader("Content-Length",
                Integer.toString(gmffomattedRequest.length()));
        post.setRequestHeader("Content-type", "text/xml");
        post.setRequestBody(gmffomattedRequest);

        try {
            /*Call executeMethod to post the data to the designated URL.*/
            final int returnCode = httpclient.executeMethod(post);

            /*Do error handling and parse the response before returning.*/
            if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
                System.err.println("The Post method is not implemented by this URI");
                return null;
            } else if (returnCode == HttpStatus.SC_OK) {
                return post.getResponseBodyAsString();
            }
        } catch (Exception e) {
            throw new InvalidResponseXml(e.getMessage());
        } finally {
            post.releaseConnection();
        }
        return null;
    }
}