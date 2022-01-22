package fdrc.service;

import com.fiserv.merchant.gmfv10.TARequestDetails;
import fdrc.model.RCRequest;

class TransArmorService extends BaseService {

    @Override
    String buildRequest(RCRequest RCRequest, FDRCRequestService requestService) {
        String message = null;
        try {
            TARequestDetails taReqDetails = populateTaRequestDetails(requestService);
            getGmfmv().setTransArmorRequest(taReqDetails);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    private TARequestDetails populateTaRequestDetails(FDRCRequestService requestService) {
        TARequestDetails taReqDetails = new TARequestDetails();
        taReqDetails.setCommonGrp(requestService.getCommonGrp());
        taReqDetails.setAltMerchNameAndAddrGrp(requestService.getAltMerchNameAndAddrGrp());
        taReqDetails.setCardGrp(requestService.getCardGrp());
        taReqDetails.setTAGrp(requestService.getTAGrp());
        return taReqDetails;
    }
}
