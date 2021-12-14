package fdrc.client;

import com.fiserv.merchant.gmfv10.AdminRequestDetails;
import com.fiserv.merchant.gmfv10.AdminResponseDetails;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;

public class AdminRequest extends GenericRequest {

    @Override
    public String buildRequest(Request request, FiServRequest fiServRequest) {
        String errorMsg = "";
        AdminRequestDetails adminRequestDetails = new AdminRequestDetails();
        adminRequestDetails.setCommonGrp(fiServRequest.getCommonGrp());
        adminRequestDetails.setHostTotGrp(fiServRequest.getHostTotGrp());

        gmfmv.setAdminRequest(adminRequestDetails);
        return errorMsg;
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, Response response) {
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
