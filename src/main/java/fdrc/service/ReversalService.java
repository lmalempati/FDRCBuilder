package fdrc.service;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
import fdrc.common.FDRCRequestService;
import fdrc.utils.Utils;

import java.io.Serializable;
import java.util.List;

class ReversalService extends GenericService implements Serializable {

    @Override
    public String buildRequest(RCRequest request, FDRCRequestService requestService) {
        String message = "";
        try {
            VoidTOReversalRequestDetails reversalRequestDetails = populateReversalRequestDetails(requestService);
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
            gmfmv.setReversalRequest(reversalRequestDetails);
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
        return reversalRequestDetails;
    }
}
