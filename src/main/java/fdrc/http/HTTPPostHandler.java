package fdrc.http;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.Constants;
import fdrc.base.RCResponse;
import fdrc.common.Serialization;
import fdrc.utils.RequestUtils;
import fdrc.xml.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.math.BigInteger;

public class HTTPPostHandler {

    /* The below method will take the XML request and returns the XML response received from Data wire.
     * */
    @SuppressWarnings("deprecation")
    public RCResponse Submit(String gmfrequest) {

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
                System.err
                        .println("The Post method is not implemented by this URI");
                return null;
            } else if (returnCode == HttpStatus.SC_OK) {

                return loadResponse(post
                        .getResponseBodyAsString());
            }
        } catch (Exception e) {
//            throw new InvalidResponseXml(e.getMessage());
            return null;
        } finally {
            post.releaseConnection();
        }
        return null;
    }


    /*The below method takes XML response received after post method execution.
     * and build Response object; then extract pay load data that is actual
     * transaction response received from Data Wire.*/
    public RCResponse loadResponse(String xml) {
        String payload = null;
        Response response = null;
        RCResponse rcResponse = null;
        XmlMapper mapper = Serialization.getXmlMapperDeserializer(true);
        Response response1 = null;
        ObjectFactory factory = null;
        try {
            response = (Response) mapper.readValue(xml, Response.class);
            if (response != null && response.getStatus() != null
                    && response.getStatus().getStatusCode() != null) {
                rcResponse = new RCResponse();
                if (response.getStatus().getStatusCode().equalsIgnoreCase("OK")) {
                    rcResponse.DWStatusCode = response.getStatus().getStatusCode();
                    if (response.getTransactionResponse() != null
                            && response.getTransactionResponse().getPayload() != null
                            && response.getTransactionResponse().getPayload()
                            .getEncoding() != null) {
                        rcResponse.DWReturnCode = response.getTransactionResponse().getReturnCode();
                        if (response.getTransactionResponse().getPayload()
                                .getEncoding().equals("cdata")) {
                            /*Extract pay load data that is the transaction response for cdata type encoded message.*/
                            rcResponse.responseRaw = response.getTransactionResponse()
                                    .getPayload().getValue();
                        } else if (response.getTransactionResponse()
                                .getPayload().getEncoding()
                                .equalsIgnoreCase("xml_escape")) {
                            /*Extract pay load data that is the transaction response for xml_escape type encoded message.*/
                            rcResponse.responseRaw = response.getTransactionResponse()
                                    .getPayload().getValue()
                                    .replaceAll("&gt;", ">")
                                    .replaceAll("&lt;", "<")
                                    .replaceAll("&amp;", "&");
                        }
                    }
                }
//                else{
//                    throw new InvalidResponseXml(response.getStatus().getStatusCode());
//            }
            }
//        else{
//            throw new InvalidResponseXml("Invalid Response");
//        }
        } catch (
                JacksonException e) {
            throw new InvalidResponseXml(e.getMessage());
        }
//        Return the transaction response.
        return rcResponse;
    }

}