package fdrc.service;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
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
}