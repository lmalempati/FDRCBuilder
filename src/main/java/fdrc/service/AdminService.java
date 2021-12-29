package fdrc.service;

import com.fiserv.merchant.gmfv10.AdminRequestDetails;
import com.fiserv.merchant.gmfv10.AdminResponseDetails;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
import fdrc.common.FDRCRequestService;

class AdminService extends GenericService {

    @Override
    public String buildRequest(RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        String message = "";
        try {
            AdminRequestDetails adminRequestDetails = getAdminRequestDetails(FDRCRequestService);

            gmfmv.setAdminRequest(adminRequestDetails);
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    private AdminRequestDetails getAdminRequestDetails(FDRCRequestService FDRCRequestService) {
        AdminRequestDetails adminRequestDetails = new AdminRequestDetails();
        adminRequestDetails.setCommonGrp(FDRCRequestService.getCommonGrp());
        adminRequestDetails.setHostTotGrp(FDRCRequestService.getHostTotGrp());
        return adminRequestDetails;
    }
}
