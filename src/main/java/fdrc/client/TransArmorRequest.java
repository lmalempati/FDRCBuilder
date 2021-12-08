package fdrc.client;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;

public class TransArmorRequest extends GenericRequest {

    @Override
    public String buildRequest(Request request, FiServRequest fiServRequest) {
        String errorMsg = null;
        TARequestDetails taReqDetails = null;
        try {
            taReqDetails = new TARequestDetails();
            taReqDetails.setCommonGrp(fiServRequest.getCommonGrp());
            taReqDetails.setTAGrp(fiServRequest.getTAGrp(request));
            gmfmv.setTransArmorRequest(taReqDetails);
        } catch (IllegalArgumentException e) {
            errorMsg = e.getMessage();
        } catch (Exception e) {
            errorMsg = e.getMessage();
        } finally {
            fiServRequest = null;
        }
        return errorMsg;
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, Response response) {
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
        response.origRespCode = taResponse.getRespGrp().getRespCode();
        response.refNum = taResponse.getCommonGrp().getRefNum();
        response.orderNum = taResponse.getCommonGrp().getOrderNum();

        result = true;
        return result;
    }
}
