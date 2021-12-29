package fdrc.service;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
import fdrc.common.FDRCRequestService;

class TransArmorService extends GenericService {

    @Override
    public String buildRequest(RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        String message = null;
        try {
            TARequestDetails taReqDetails = new TARequestDetails();
            taReqDetails.setCommonGrp(FDRCRequestService.getCommonGrp());
            taReqDetails.setTAGrp(FDRCRequestService.getTAGrp(RCRequest));
            gmfmv.setTransArmorRequest(taReqDetails);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }
}
