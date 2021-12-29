package fdrc.service;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidRequest;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.common.Constants;
import fdrc.common.FDRCRequestService;
import fdrc.common.Serialization;
import fdrc.http.HTTPPostHandler;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
import fdrc.utils.Utils;
import fdrc.xml.ObjectFactory;
import fdrc.xml.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;


abstract class GenericService {
    GMFMessageVariants gmfmv = new GMFMessageVariants();
    String responseXml = "";
    static Logger log = Logger.getLogger(GenericService.class.getName());

    abstract String buildRequest(final RCRequest RCRequest, FDRCRequestService FDRCRequestService);
    private String submit() {
        String requestXml = "";
        String errorMsg = "";
        requestXml = Serialization.getXmlObject(gmfmv, errorMsg);
        /* Jackson does not support */
//        serialization.validateXMLSchema(requestXml);
        if (!errorMsg.equals("")) throw new InvalidRequest(errorMsg); // todo:
        log.info("GMF Request == " + requestXml); // todo: use Log4j
        try {
            responseXml = new HTTPPostHandler().Submit(requestXml);
//            responseString = responseString.replaceAll("&gt;", ">").replaceAll("&lt;", "<");
        } catch (IOException e) {
            throw new InvalidRequest(e.getMessage());
        }
        return responseXml;
    }

    RCResponse processRequest(RCRequest request) {
        FDRCRequestService FDRCRequestService = new FDRCRequestService(request);

        String errorMsg = null;
        try {
            errorMsg = buildRequest(request, FDRCRequestService);
        } catch (IllegalArgumentException | UnsupportedEnumValueException e) {
            throw new InvalidRequest(e.getMessage());
        } catch (Exception e) {
            throw new InvalidRequest(e.getMessage());
        }
        if (Utils.isNotNullOrEmpty(errorMsg))
            return new RCResponse(errorMsg);
        submit();
        // read the response xml and load
        ResponseWrapper responseWrapper = new ResponseWrapper(responseXml, request);
        log.info("Successful HTTP POST response: " + "\n" + responseWrapper.rcResponse.responseRaw + "\n"); // todo: debug purpose only
        return responseWrapper.rcResponse;
    }
}

class ResponseWrapper extends RCResponse {
    RCResponse rcResponse;
    RespGrp respGrp;
    CommonGrp commonGrp;
    CardGrp cardGrp;
    VisaGrp visaGrp;
    MCGrp mcGrp;
    DSGrp dsGrp;
    AmexGrp amexGrp;
    TAGrp taGrp;
    HostTotGrp hostTotGrp;
    HostTotDetGrp hostTotDetGrp;

    ResponseWrapper(String respXml, RCRequest request) {
        loadResponseXml(respXml);
        if (torRequired()) {
            rcResponse.addtlRespData = "Timeout";
            return;
        }
        GMFMessageVariants gmfResponse = Serialization.getObjectXML(rcResponse.responseRaw);

        // if rejection, send in errorMsg msg of response
        if (gmfResponse.getRejectResponse() != null) {
            RejectResponseDetails responseDetails = gmfResponse.getRejectResponse();
            rcResponse.errorMsg = responseDetails.getRespGrp().getErrorData();
            return;
        }
        loadGroups(gmfResponse, request);
        loadResponseFromGrps(gmfResponse, request);
    }

    private void loadResponseFromGrps(GMFMessageVariants gmfMessageVariants, RCRequest request) {
        rcResponse.respCode = respGrp.getRespCode();
        rcResponse.addtlRespData = respGrp.getAddtlRespData();
        rcResponse.authID = respGrp.getAuthID();
        rcResponse.stan = commonGrp.getSTAN();
        rcResponse.localDateTime = commonGrp.getLocalDateTime();
        rcResponse.tranDateTime = commonGrp.getTrnmsnDateTime();
        rcResponse.respCode = respGrp.getRespCode();
        rcResponse.trnmsnDateTime = request.trnsmitDateTime;
        rcResponse.refNum = commonGrp.getRefNum();
        rcResponse.orderNum = commonGrp.getOrderNum();
        if (visaGrp != null) {
            rcResponse.transID = visaGrp.getTransID();
            rcResponse.cardLevelResult = visaGrp.getCardLevelResult();
            rcResponse.aci = visaGrp.getACI();
            rcResponse.spendQInd = visaGrp.getSpendQInd();
        }
        if (mcGrp != null) {
            rcResponse.banknetData = mcGrp.getBanknetData();
        }
        if (dsGrp != null) {
            rcResponse.discNRID = dsGrp.getDiscNRID();
            rcResponse.discTransQualifier = dsGrp.getDiscTransQualifier();
        }
        if (amexGrp != null) {
            rcResponse.amexTranID = amexGrp.getAmExTranID();
        }
        if (taGrp != null) {
            rcResponse.tkn = taGrp.getTkn();
        }
    }

    private boolean loadGroups(GMFMessageVariants gmf, RCRequest request) {
        if (gmf.getCreditResponse() != null) {
            respGrp = gmf.getCreditResponse().getRespGrp();
            commonGrp = gmf.getCreditResponse().getCommonGrp();
            visaGrp = gmf.getCreditResponse().getVisaGrp();
            mcGrp = gmf.getCreditResponse().getMCGrp();
            dsGrp = gmf.getCreditResponse().getDSGrp();
            amexGrp = gmf.getCreditResponse().getAmexGrp();
            taGrp = gmf.getCreditResponse().getTAGrp();
        } else if (gmf.getDebitResponse() != null) {
            respGrp = gmf.getDebitResponse().getRespGrp();
            commonGrp = gmf.getDebitResponse().getCommonGrp();
            taGrp = gmf.getDebitResponse().getTAGrp();
        } else if (gmf.getEBTResponse() != null) {
            respGrp = gmf.getEBTResponse().getRespGrp();
            commonGrp = gmf.getEBTResponse().getCommonGrp();
            taGrp = gmf.getEBTResponse().getTAGrp();
        } else if (gmf.getReversalResponse() != null) {
            respGrp = gmf.getReversalResponse().getRespGrp();
            commonGrp = gmf.getReversalResponse().getCommonGrp();
            visaGrp = gmf.getReversalResponse().getVisaGrp();
            mcGrp = gmf.getReversalResponse().getMCGrp();
            dsGrp = gmf.getReversalResponse().getDSGrp();
            amexGrp = gmf.getReversalResponse().getAmexGrp();
            taGrp = gmf.getReversalResponse().getTAGrp();
        } else if (gmf.getAdminResponse() != null) {
            respGrp = gmf.getAdminResponse().getRespGrp();
            commonGrp = gmf.getAdminResponse().getCommonGrp();
            taGrp = gmf.getAdminResponse().getTAGrp();
            hostTotGrp = gmf.getAdminResponse().getHostTotGrp();
        } else if (gmf.getTransArmorResponse() != null) {
            respGrp = gmf.getTransArmorResponse().getRespGrp();
            commonGrp = gmf.getTransArmorResponse().getCommonGrp();
            taGrp = gmf.getTransArmorResponse().getTAGrp();
        }
        return true;
    }

    /*The below method takes XML response received after post method execution.
     * and build Response object; then extract pay load data that is actual
     * transaction response received from Data Wire.*/
    private void loadResponseXml(String xml) {
        Response response;
        XmlMapper mapper = Serialization.getXmlMapperDeserializer(true);
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
            }
        } catch (JacksonException e) {
            throw new InvalidResponseXml(e.getMessage());
        }
    }

    /* Verify if a TOR needs to be submitted. */
    private boolean torRequired() {
        if (rcResponse == null) return true;
        if (Arrays.asList(Constants.ReturnCodesToReverse).contains(rcResponse.DWReturnCode)) return true;
        if (Arrays.asList(Constants.StatusCodesToReverse).contains(rcResponse.DWStatusCode)) return true;
        if (!rcResponse.DWReturnCode.equals("000") && !rcResponse.DWStatusCode.equals("AuthenticationError"))
            return true;
        return false;
    }
}
