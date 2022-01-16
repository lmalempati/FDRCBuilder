package fdrc.service;

import com.fiserv.merchant.gmfv10.*;
import fdrc.model.RCRequest;

class TransArmorService extends BaseService {

    @Override
    String buildRequest(RCRequest RCRequest, FDRCRequestService requestService) {
        String message = null;
        try {
            TARequestDetails taReqDetails = new TARequestDetails();
            taReqDetails.setCommonGrp(requestService.getCommonGrp());
            taReqDetails.setAltMerchNameAndAddrGrp(requestService.getAltMerchNameAndAddrGrp());
            taReqDetails.setCardGrp(requestService.getCardGrp());
            taReqDetails.setTAGrp(requestService.getTAGrp());
            getGmfmv().setTransArmorRequest(taReqDetails);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }
}
