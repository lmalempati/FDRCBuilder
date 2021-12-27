package fdrc.model;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.RCRequest;
import fdrc.base.RCResponse;
import fdrc.common.FDRCRequestService;

class TransArmorService extends GenericService {

    @Override
    public String buildRequest(RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        String errorMsg = null;
        TARequestDetails taReqDetails = null;
        try {
            taReqDetails = new TARequestDetails();
            taReqDetails.setCommonGrp(FDRCRequestService.getCommonGrp());
            taReqDetails.setTAGrp(FDRCRequestService.getTAGrp(RCRequest));
            gmfmv.setTransArmorRequest(taReqDetails);
        } catch (IllegalArgumentException e) {
            errorMsg = e.getMessage();
        } catch (Exception e) {
            errorMsg = e.getMessage();
        } finally {
            FDRCRequestService = null;
        }
        return errorMsg;
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, RCRequest request, RCResponse response) {
        RespGrp respGrp = null;
        boolean result = false;
        if (gmfmvResponse.getTransArmorResponse() == null) {
            throw new InvalidResponseXml("invalid response");
        }
        TAResponseDetails taResponse = gmfmvResponse.getTransArmorResponse();

        response.respCode = taResponse.getRespGrp().getRespCode();
        response.addtlRespData = taResponse.getRespGrp().getAddtlRespData();
        response.origAuthID = taResponse.getRespGrp().getAuthID();
        response.origSTAN = taResponse.getCommonGrp().getSTAN();//?
        response.origLocalDateTime = taResponse.getCommonGrp().getLocalDateTime();
        response.origTranDateTime = taResponse.getCommonGrp().getTrnmsnDateTime();
        response.trnmsnDateTime = request.trnsmitDateTime;
        response.origRespCode = taResponse.getRespGrp().getRespCode();
        response.refNum = taResponse.getCommonGrp().getRefNum();
        response.orderNum = taResponse.getCommonGrp().getOrderNum();

        result = true;
        return result;
    }
}
