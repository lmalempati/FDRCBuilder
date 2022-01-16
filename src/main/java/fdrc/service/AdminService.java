package fdrc.service;

import com.fiserv.merchant.gmfv10.AdminRequestDetails;
import fdrc.model.RCRequest;

class AdminService extends BaseService {

    @Override
    String buildRequest(RCRequest RCRequest, FDRCRequestService requestService) {
        String message = "";
        try {
            AdminRequestDetails adminRequestDetails = getAdminRequestDetails(requestService);
            getGmfmv().setAdminRequest(adminRequestDetails);
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
