package fdrc.client;

import com.fiserv.merchant.gmfv10.*;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;
import fdrc.utils.Utils;

import java.io.Serializable;
import java.util.List;

class ReversalRequest extends GenericRequest implements Serializable {

    @Override
    public String buildRequest(Request request) {
        String errorMsg = null;
        VoidTOReversalRequestDetails reversalRequestDetails = new VoidTOReversalRequestDetails();
        FiServRequest fiServRequest = new FiServRequest(request);
        try {
            reversalRequestDetails.setCommonGrp(fiServRequest.getCommonGrp());
            reversalRequestDetails.setCardGrp(fiServRequest.getCardGrp());
            reversalRequestDetails.getAddtlAmtGrp();
            List<AddtlAmtGrp> addtlAmtGr = reversalRequestDetails.getAddtlAmtGrp();
            List<AddtlAmtGrp> addlGrps = fiServRequest.getAddtlAmtGrp();
            if (addlGrps != null)
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGr.add(grp);
                }
            if (Utils.isNotNullOrEmpty(request.cardType))
                switch (Utils.toEnum(CardTypeType.class, request.cardType)) {
                    case VISA:
                        reversalRequestDetails.setVisaGrp(fiServRequest.getVisaGrp());
                        break;
                    case MASTER_CARD:
                        reversalRequestDetails.setMCGrp(fiServRequest.getMasterCardGrp());
                        break;
                    case JCB:
                    case DISCOVER:
                    case DINERS:
                        reversalRequestDetails.setDSGrp(fiServRequest.getDiscoverGrp());
                        break;
                    case AMEX:
                        reversalRequestDetails.setAmexGrp(fiServRequest.getAmexGrp());
                        break;
//                default: // todo: what if cardtype is null
//                    errorMsg = "Unknown Crd Type";
                }
            reversalRequestDetails.setEcommGrp(fiServRequest.getEcommGrp());
            reversalRequestDetails.setOrigAuthGrp(fiServRequest.getOrigAuthGrp());
            gmfmv.setReversalRequest(reversalRequestDetails);
        } catch (IllegalArgumentException | NullPointerException e) {
            errorMsg = e.getMessage();
        } catch (RuntimeException e) {
            errorMsg = e.getMessage();
        } finally {
            fiServRequest = null;
        }
        return errorMsg;
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, Response response) {
        boolean result = false;
        if (gmfmvResponse.getReversalResponse() == null) {
            throw new RuntimeException("invalid response");
        }

        VoidTOReversalResponseDetails reversalResponse = gmfmvResponse.getReversalResponse();
        response.respCode = reversalResponse.getRespGrp().getRespCode();
        response.addtlRespData = reversalResponse.getRespGrp().getAddtlRespData();
        response.origRespCode = reversalResponse.getRespGrp().getRespCode();
        response.origAuthID = reversalResponse.getRespGrp().getAuthID();

        response.origSTAN = reversalResponse.getCommonGrp().getSTAN();//?
        response.origLocalDateTime = reversalResponse.getCommonGrp().getLocalDateTime();
        response.origTranDateTime = reversalResponse.getCommonGrp().getTrnmsnDateTime();
        response.refNum = reversalResponse.getCommonGrp().getRefNum();
        response.orderNum = reversalResponse.getCommonGrp().getOrderNum();
        if (reversalResponse.getCardGrp() != null)
            if (reversalResponse.getMCGrp() != null) {
                response.banknetData = reversalResponse.getMCGrp().getBanknetData();
            }
        if (reversalResponse.getDSGrp() != null) {
            response.discNRID = reversalResponse.getDSGrp().getDiscNRID();
            response.discTransQualifier = reversalResponse.getDSGrp().getDiscTransQualifier();
        }
        if (reversalResponse.getVisaGrp() != null) {
            response.aci = reversalResponse.getVisaGrp().getACI();
            response.cardLevelResult = reversalResponse.getVisaGrp().getCardLevelResult();
            response.transID = reversalResponse.getVisaGrp().getTransID();
            response.spendQInd = reversalResponse.getVisaGrp().getSpendQInd();
        }
        if (reversalResponse.getAmexGrp() != null) {
            response.amexTranID = reversalResponse.getAmexGrp().getAmExTranID();
        }
        result = true;
        return result;
    }

}
