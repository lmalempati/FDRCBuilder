package fdrc.http;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.common.Constants;
import fdrc.model.RCResponse;
import fdrc.common.Serialization;
import fdrc.utils.RequestUtils;
import fdrc.xml.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.math.BigInteger;

public class HTTPPostHandler {

    /* The below method will take the XML request and returns the XML response received from Data wire.
     * */
    @SuppressWarnings("deprecation")
    public String Submit(String gmfrequest) throws IOException, HttpException {

        /*Response that will be returned.*/
        String response = "";
        /* Create the instance of the Request that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        ObjectFactory factory = new ObjectFactory();
        Request gmfTransactionRequest = factory.createRequest();

        /* Create the instance of the TransactionType that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        TransactionType transactionType = factory.createTransactionType();
        /*Set the service ID parameter.*/
        transactionType.setServiceID("160");

        /* Create the instance of PayloadType that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        PayloadType payloadType = new PayloadType();
        /* Set encoding type*/
        payloadType.setEncoding("cdata");
        /* Assign the XML request data as the pay load value*/
        payloadType.setValue(gmfrequest);
        /* Set transaction type pay load value*/
        transactionType.setPayload(payloadType);
        /* Assign transaction type object to the transaction request.*/
        gmfTransactionRequest.setTransaction(transactionType);
        /* Create the instance of ReqClientIDType that is a class generated from the Rapid connect Transaction
         * Service Schema file [rc.xsd]*/
        ReqClientIDType reqClientIDType = new ReqClientIDType();
        reqClientIDType.setApp("RAPIDCONNECTSRS");
        reqClientIDType.setAuth(String.format("%s%s|%s", Constants.REQUEST_GROUPID, RequestUtils.merchantID, Constants.REQUEST_TERMID));
        /* Set the clientRef value*/
        reqClientIDType.setClientRef(RequestUtils.getClientRef());
        /* Set the DID value*/
        reqClientIDType.setDID(RequestUtils.mapMidToDID(RequestUtils.merchantID));

        /*Assign ReqClientID object value to transaction request*/
        gmfTransactionRequest.setReqClientID(reqClientIDType);
        /*Set client timeout value*/
        gmfTransactionRequest.setClientTimeout(new BigInteger("30"));
        /*Set version value*/
        gmfTransactionRequest.setVersion("3");

        String gmffomattedRequest = "";
        //Transform the gmfTransactionRequest object into XML string.
        gmffomattedRequest = Serialization.getXmlObject(gmfTransactionRequest, null);

        /* URL that will consume the transaction request.*/
        final String postURL = "https://stg.dw.us.fdcnet.biz/rc"; // todo: hardcoded?
        /*Instantiate the POST method*/
        final PostMethod post = new PostMethod(postURL);
        /*Instantiate the HTTP client*/
        final HttpClient httpclient = new HttpClient();
        /*Set various parameters of HTTP requet header*/
        post.setRequestHeader("User-Agent", "TRR-Formatter");
        post.setRequestHeader("Host", postURL);
        post.setRequestHeader("Connection", "Keep-Alive");
        post.setRequestHeader("Cache-Control", "no-cache");
        post.setRequestHeader("Content-Length",
                Integer.toString(gmffomattedRequest.length()));
        post.setRequestHeader("Content-type", "text/xml");
        /*Set the request body with the XML transaction request*/
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