package fdrc.service;

import com.fiserv.merchant.gmfv10.*;
import fdrc.model.RCRequest;
import fdrc.utils.Utils;

import java.io.Serializable;
import java.util.List;

class ReversalService extends BaseService implements Serializable {

    @Override
    String buildFDRCRequest(RCRequest request, FDRCRequestService requestService) {
        String message = "";
        try {
            VoidTOReversalRequestDetails reversalRequestDetails = populateReversalRequestDetails(requestService);
            List<AddtlAmtGrp> addlGrps = requestService.getAddtlAmtGrp();
            if (addlGrps != null) {
                List<AddtlAmtGrp> addtlAmtGr = reversalRequestDetails.getAddtlAmtGrp();
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
            getGmfmv().setReversalRequest(reversalRequestDetails);
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    private VoidTOReversalRequestDetails populateReversalRequestDetails(FDRCRequestService requestService) {
        VoidTOReversalRequestDetails reversalRequestDetails = new VoidTOReversalRequestDetails();
        reversalRequestDetails.setCommonGrp(requestService.getCommonGrp());
        reversalRequestDetails.setAltMerchNameAndAddrGrp(requestService.getAltMerchNameAndAddrGrp());
        reversalRequestDetails.setCardGrp(requestService.getCardGrp());
        reversalRequestDetails.setEcommGrp(requestService.getEcommGrp());
        reversalRequestDetails.setOrigAuthGrp(requestService.getOrigAuthGrp());
        reversalRequestDetails.setEbtGrp(requestService.getEBTGrp());
        reversalRequestDetails.setSecrTxnGrp(requestService.getSecrTxnGrp());
        reversalRequestDetails.setTAGrp(requestService.getTAGrp());
        return reversalRequestDetails;
    }
}
