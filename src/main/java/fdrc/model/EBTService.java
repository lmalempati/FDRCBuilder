package fdrc.model;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.RCRequest;
import fdrc.base.RCResponse;
import fdrc.common.FDRCRequestService;

import java.io.Serializable;
import java.util.List;

class EBTService extends GenericService implements Serializable {
    @Override
    public String buildRequest(RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        String errorMsg = null;
        EBTRequestDetails ebtReqDtl = null;
        ebtReqDtl = new EBTRequestDetails();
        ebtReqDtl.setCommonGrp(FDRCRequestService.getCommonGrp());
        ebtReqDtl.setCardGrp(FDRCRequestService.getCardGrp());

        List<AddtlAmtGrp> addtlAmtGr = null;
        List<AddtlAmtGrp> addlGrps = FDRCRequestService.getAddtlAmtGrp();
        if (addlGrps != null) {
            addtlAmtGr = ebtReqDtl.getAddtlAmtGrp();
            for (AddtlAmtGrp grp : addlGrps
            ) {
                addtlAmtGr.add(grp);
            }
        }
        ebtReqDtl.setPINGrp(FDRCRequestService.getPINGrp());
        ebtReqDtl.setEbtGrp(FDRCRequestService.getEBTGrp(RCRequest));
        gmfmv.setEBTRequest(ebtReqDtl);
        return errorMsg;
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, RCRequest request, RCResponse response) {
        RespGrp respGrp = null;
        boolean result = false;
        if (gmfmvResponse.getEBTResponse() == null) {
            throw new InvalidResponseXml("invalid response");
        }
        EBTResponseDetails ebtResponse = gmfmvResponse.getEBTResponse();

        response.respCode = ebtResponse.getRespGrp().getRespCode();
        response.addtlRespData = ebtResponse.getRespGrp().getAddtlRespData();
        response.origAuthID = ebtResponse.getRespGrp().getAuthID();
        response.origSTAN = ebtResponse.getCommonGrp().getSTAN();//?
        response.origLocalDateTime = ebtResponse.getCommonGrp().getLocalDateTime();
        response.origTranDateTime = ebtResponse.getCommonGrp().getTrnmsnDateTime();
        response.trnmsnDateTime = request.trnsmitDateTime;
        response.origRespCode = ebtResponse.getRespGrp().getRespCode();
        response.refNum = ebtResponse.getCommonGrp().getRefNum();
        response.orderNum = ebtResponse.getCommonGrp().getOrderNum();

        result = true;
        return result;
    }
}