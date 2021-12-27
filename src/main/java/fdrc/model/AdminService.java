package fdrc.model;

import com.fiserv.merchant.gmfv10.AdminRequestDetails;
import com.fiserv.merchant.gmfv10.AdminResponseDetails;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.RCRequest;
import fdrc.base.RCResponse;
import fdrc.common.FDRCRequestService;

class AdminService extends GenericService {

    @Override
    public String buildRequest(RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        String errorMsg = "";
        AdminRequestDetails adminRequestDetails = new AdminRequestDetails();
        adminRequestDetails.setCommonGrp(FDRCRequestService.getCommonGrp());
        adminRequestDetails.setHostTotGrp(FDRCRequestService.getHostTotGrp());

        gmfmv.setAdminRequest(adminRequestDetails);
        return errorMsg;
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, RCRequest request, RCResponse response) {
        boolean result = false;
        if (gmfmvResponse.getAdminResponse() == null) {
            throw new InvalidResponseXml("invalid response");
        }
        AdminResponseDetails adminResponse = gmfmvResponse.getAdminResponse();

        response.respCode = adminResponse.getRespGrp().getRespCode();
        response.addtlRespData = adminResponse.getRespGrp().getAddtlRespData();

        response.origAuthID = adminResponse.getRespGrp().getAuthID();
        response.origSTAN = adminResponse.getCommonGrp().getSTAN();//?
        response.origRespCode = adminResponse.getRespGrp().getRespCode();

        adminResponse.getHostTotGrp().getTotReqDate();
        adminResponse.getHostTotGrp().getPassword();
        adminResponse.getHostTotGrp().getNetSettleAmt();

        result = true;
        return result;
    }
}
