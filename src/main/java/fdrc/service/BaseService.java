package fdrc.service;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidRequest;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.common.Constants;
import fdrc.common.Serialization;
import fdrc.http.HTTPPostHandler;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
import fdrc.utils.Utils;
import fdrc.xml.ObjectFactory;
import fdrc.xml.Response;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.fiserv.merchant.gmfv10.TxnTypeType.TA_KEY_REQUEST;

abstract class BaseService {
    private GMFMessageVariants gmfmv;
    private RCResponse rcResponse;
    private String responseXml = "";
    private static Logger logger = Logger.getLogger(BaseService.class.getName());

    protected BaseService() {
        this.gmfmv = new GMFMessageVariants();
    }

    protected GMFMessageVariants getGmfmv() {
        if (this.gmfmv != null)
            return gmfmv;
        else
            throw new NullPointerException("GMFMessageVariants is null");
    }

    abstract String buildRequest(final RCRequest RCRequest, FDRCRequestService requestService);

    RCResponse processRequest(RCRequest request) {
        FDRCRequestService fdrcRequestService = new FDRCRequestService(request);
        String errorMsg = null;
        errorMsg = buildRequest(request, fdrcRequestService);

        if (Utils.isNotNullOrEmpty(errorMsg))
            return new RCResponse(errorMsg);
        submit();
        // parse the response payload and read into RCResponse
        new ResponseWrapper(request);
        return rcResponse;
    }

    private String submit() {
        String requestXml = "";
        String errorMsg = "";
        requestXml = Serialization.getXmlFromObj(gmfmv, errorMsg);
        /* Jackson does not support */
//        serialization.validateXMLSchema(requestXml);
        if (!errorMsg.equals("")) throw new InvalidRequest(errorMsg); // todo:
        logger.info("FDRC: GMF Request == " + requestXml);
        try {
            responseXml = new HTTPPostHandler().Submit(requestXml);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidRequest(e.getMessage());
        }
        return responseXml;
    }

    private class ResponseWrapper extends RCResponse {
        private RespGrp respGrp;
        private CommonGrp commonGrp;
        private CardGrp cardGrp;
        private VisaGrp visaGrp;
        private MCGrp mcGrp;
        private DSGrp dsGrp;
        private AmexGrp amexGrp;
        private TAGrp taGrp;
        private HostTotGrp hostTotGrp;
        private List<HostTotDetGrp> hostTotDetGrp;
        private GMFMessageVariants gmfResponse;

        ResponseWrapper(RCRequest request) {
            loadResponseXml();
            if (torRequired()) {
                if (rcResponse == null) // possible when blank response
                    rcResponse = new RCResponse("Timeout");
                else
                    rcResponse.errorMsg = "Timeout";
                return;
            }
            if (Utils.isNotNullOrEmpty(rcResponse.errorMsg)) return;
            logger.info("FDRC: Successful HTTP POST response: " + "\n" + rcResponse.responsePayload + "\n"); // todo: debug purpose only
            gmfResponse = (GMFMessageVariants) Serialization.getObjectFromXML(GMFMessageVariants.class, rcResponse.responsePayload, false); // false because, loading rejection response is failing..
            loadGroups(gmfResponse, request);
            loadResponseFromGrps(gmfResponse, request);
        }

        private void loadResponseFromGrps(GMFMessageVariants gmfMessageVariants, RCRequest request) {
            if (respGrp != null) {
                rcResponse.respCode = respGrp.getRespCode();
                rcResponse.addtlRespData = respGrp.getAddtlRespData();
                rcResponse.authID = respGrp.getAuthID();
                rcResponse.respCode = respGrp.getRespCode();
                rcResponse.errorMsg = respGrp.getErrorData();
            }
            if (commonGrp != null) {
                rcResponse.stan = commonGrp.getSTAN();
                rcResponse.localDateTime = commonGrp.getLocalDateTime();
                rcResponse.tranDateTime = commonGrp.getTrnmsnDateTime();
                rcResponse.trnmsnDateTime = request.trnsmitDateTime;
                rcResponse.refNum = commonGrp.getRefNum();
                rcResponse.orderNum = commonGrp.getOrderNum();
            }
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
            //todo: use cardgrp, hosttotdetgrp
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
                cardGrp = gmf.getCreditResponse().getCardGrp();
            } else if (gmf.getDebitResponse() != null) {
                respGrp = gmf.getDebitResponse().getRespGrp();
                commonGrp = gmf.getDebitResponse().getCommonGrp();
                taGrp = gmf.getDebitResponse().getTAGrp();
                cardGrp = gmf.getDebitResponse().getCardGrp();
            } else if (gmf.getEBTResponse() != null) {
                respGrp = gmf.getEBTResponse().getRespGrp();
                commonGrp = gmf.getEBTResponse().getCommonGrp();
                taGrp = gmf.getEBTResponse().getTAGrp();
                cardGrp = gmf.getEBTResponse().getCardGrp();
            } else if (gmf.getReversalResponse() != null) {
                respGrp = gmf.getReversalResponse().getRespGrp();
                commonGrp = gmf.getReversalResponse().getCommonGrp();
                visaGrp = gmf.getReversalResponse().getVisaGrp();
                mcGrp = gmf.getReversalResponse().getMCGrp();
                dsGrp = gmf.getReversalResponse().getDSGrp();
                amexGrp = gmf.getReversalResponse().getAmexGrp();
                taGrp = gmf.getReversalResponse().getTAGrp();
                cardGrp = gmf.getReversalResponse().getCardGrp();
            } else if (gmf.getAdminResponse() != null) {
                respGrp = gmf.getAdminResponse().getRespGrp();
                commonGrp = gmf.getAdminResponse().getCommonGrp();
                taGrp = gmf.getAdminResponse().getTAGrp();
                hostTotGrp = gmf.getAdminResponse().getHostTotGrp();
                hostTotDetGrp = gmf.getAdminResponse().getHostTotDetGrp();
            } else if (gmf.getTransArmorResponse() != null) {
                respGrp = gmf.getTransArmorResponse().getRespGrp();
                commonGrp = gmf.getTransArmorResponse().getCommonGrp();
                taGrp = gmf.getTransArmorResponse().getTAGrp();
            } else if (gmf.getRejectResponse() != null) {
                respGrp = gmf.getRejectResponse().getRespGrp();
                commonGrp = gmf.getRejectResponse().getCommonGrp();
            }
            return true;
        }

        /*The below method takes XML response received after post method execution.
         * and build RegistrationResponse object; then extract pay load data that is actual
         * transaction response received from Data Wire.*/
        private void loadResponseXml() {
            if (responseXml.isEmpty()) throw new InvalidResponseXml("Empty Response");
            Response response;
//            XmlMapper mapper = Serialization.getXmlMapperDeserializer(true);
            ObjectFactory factory = null;
            //                response = (Response) mapper.readValue(responseXml, Response.class);
            response = (Response) Serialization.getObjectFromXML(Response.class, responseXml, true);
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
                            rcResponse.responsePayload = response.getTransactionResponse()
                                    .getPayload().getValue()
                                    .replaceAll("&gt;", ">")
                                    .replaceAll("&lt;", "<")
                                    .replaceAll("&amp;", "&");
                        } else if (response.getTransactionResponse()
                                .getPayload().getEncoding()
                                .equalsIgnoreCase("xml_escape")) {
                            /*Extract pay load data that is the transaction response for xml_escape type encoded message.*/
                            rcResponse.responsePayload = response.getTransactionResponse()
                                    .getPayload().getValue()
                                    .replaceAll("&gt;", ">")
                                    .replaceAll("&lt;", "<")
                                    .replaceAll("&amp;", "&");
                        }
                    }
                } else
                    rcResponse.errorMsg = response.getStatus().getStatusCode();
            }
        }

        /* Verify if a TOR needs to be submitted. */
        private boolean torRequired() {
            if (rcResponse == null) return true;
            if (Arrays.asList(Constants.ReturnCodesToReverse).contains(rcResponse.DWReturnCode)) return true;
            if (Arrays.asList(Constants.StatusCodesToReverse).contains(rcResponse.DWStatusCode)) return true;
            if (Utils.isNotNullOrEmpty(rcResponse.DWReturnCode) && Utils.isNotNullOrEmpty(rcResponse.DWStatusCode))
                if (!rcResponse.DWReturnCode.equals("000") && !rcResponse.DWStatusCode.equals("AuthenticationError"))
                    return true;
            return false;
        }
    }
}

class ServiceUtil {
    BaseService getGMFServiceFromRequest(RCRequest rcRequest) {
        String errorMessage = "";
        if (rcRequest == null) throw new InvalidRequest("invalid payload.");
        errorMessage = Utils.validate(rcRequest); // basic validations on payload
        if (errorMessage != "") throw new InvalidRequest(errorMessage);
        // a reversal request?
        BaseService baseService = getReversalService(rcRequest);
        if (baseService == null)
            baseService = getCreditDebitEBTService(rcRequest);

        // a Host Totals request?
        if (baseService == null)
            if (Utils.toEnum(TxnTypeType.class, rcRequest.txnType) == TxnTypeType.HOST_TOTALS)
                baseService = new AdminService();
        return baseService;
    }

    private BaseService getCreditDebitEBTService(RCRequest RCRequest) {
        if (Utils.toEnum(TxnTypeType.class, RCRequest.txnType) == TA_KEY_REQUEST)
            return new TransArmorService();

        if (Utils.isNotNullOrEmpty(RCRequest.pymtType))
            switch (Utils.toEnum(PymtTypeType.class, RCRequest.pymtType)) {
                case CREDIT:
                    switch (Utils.toEnum(TxnTypeType.class, RCRequest.txnType)) {
                        case TA_TOKEN_REQUEST:
                            return new TransArmorService();
                        default:
                            return new CreditService();
                    }
                case DEBIT:
                    switch (Utils.toEnum(TxnTypeType.class, RCRequest.txnType)) {
                        case TA_TOKEN_REQUEST:
                            return new TransArmorService();
                        default:
                            return new DebitService();
                    }
                case EBT:
                    return new EBTService();
                default:
                    throw new UnsupportedEnumValueException(String.format("payment type %s", RCRequest.pymtType));
            }
        return null;
    }

    private BaseService getReversalService(RCRequest RCRequest) {
        if (Utils.isNotNullOrEmpty(RCRequest.reversalInd))
            switch (Utils.toEnum(TxnTypeType.class, RCRequest.txnType)) {
                case REFUND:
                    switch (Utils.toEnum(ReversalIndType.class, RCRequest.reversalInd)) {
                        case VOID:
                        case PARTIAL:
                        case TIMEOUT:
                            return new ReversalService();
                        default:
                            throw new UnsupportedEnumValueException(String.format("reversalInd %s", RCRequest.reversalInd));
                    }
            }
        return null;
    }
}