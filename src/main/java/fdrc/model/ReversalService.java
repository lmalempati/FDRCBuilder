package fdrc.model;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.RCRequest;
import fdrc.base.RCResponse;
import fdrc.common.FDRCRequestService;
import fdrc.utils.Utils;

import java.io.Serializable;
import java.util.List;

class ReversalService extends GenericService implements Serializable {

    @Override
    public String buildRequest(RCRequest request, FDRCRequestService requestService) {
        String errorMsg = null;
        VoidTOReversalRequestDetails reversalRequestDetails = new VoidTOReversalRequestDetails();
        reversalRequestDetails.setCommonGrp(requestService.getCommonGrp());
        reversalRequestDetails.setAltMerchNameAndAddrGrp(requestService.getAltMerchNameAndAddrGrp());
        reversalRequestDetails.setCardGrp(requestService.getCardGrp());
        List<AddtlAmtGrp> addtlAmtGr = null;
        List<AddtlAmtGrp> addlGrps = requestService.getAddtlAmtGrp();
        if (addlGrps != null) {
            addtlAmtGr = reversalRequestDetails.getAddtlAmtGrp();
            for (AddtlAmtGrp grp : addlGrps
            ) {
                addtlAmtGr.add(grp);
            }
        }
        if (Utils.isNotNullOrEmpty(request.cardType))
            switch (Utils.toEnum(CardTypeType.class, request.cardType)) {
                case VISA:
                    reversalRequestDetails.setVisaGrp(requestService.getVisaGrp());
                    break;
                case MASTER_CARD:
                    reversalRequestDetails.setMCGrp(requestService.getMasterCardGrp());
                    break;
                case JCB:
                case DISCOVER:
                case DINERS:
                    reversalRequestDetails.setDSGrp(requestService.getDiscoverGrp());
                    break;
                case AMEX:
                    reversalRequestDetails.setAmexGrp(requestService.getAmexGrp());
                    break;
            }
        reversalRequestDetails.setEcommGrp(requestService.getEcommGrp());
        reversalRequestDetails.setOrigAuthGrp(requestService.getOrigAuthGrp());
        gmfmv.setReversalRequest(reversalRequestDetails);
        return errorMsg;
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, RCRequest request, RCResponse response) {
        boolean result = false;
        if (gmfmvResponse.getReversalResponse() == null) {
            throw new InvalidResponseXml("invalid response");
        }

        VoidTOReversalResponseDetails reversalResponse = gmfmvResponse.getReversalResponse();
        response.respCode = reversalResponse.getRespGrp().getRespCode();
        response.addtlRespData = reversalResponse.getRespGrp().getAddtlRespData();
        response.origRespCode = reversalResponse.getRespGrp().getRespCode();
        response.origAuthID = reversalResponse.getRespGrp().getAuthID();

        response.origSTAN = reversalResponse.getCommonGrp().getSTAN();//?
        response.origLocalDateTime = reversalResponse.getCommonGrp().getLocalDateTime();
        response.origTranDateTime = reversalResponse.getCommonGrp().getTrnmsnDateTime();
        response.trnmsnDateTime = request.trnsmitDateTime;
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
