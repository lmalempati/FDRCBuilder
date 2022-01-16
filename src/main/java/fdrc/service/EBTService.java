package fdrc.service;

import com.fiserv.merchant.gmfv10.AddtlAmtGrp;
import com.fiserv.merchant.gmfv10.EBTRequestDetails;
import fdrc.model.RCRequest;

import java.io.Serializable;
import java.util.List;

class EBTService extends BaseService implements Serializable {
    @Override
    String buildRequest(RCRequest RCRequest, FDRCRequestService requestService) {
        String errorMsg = null;
        EBTRequestDetails ebtReqDtl = null;
        ebtReqDtl = getEbtRequestDetails(requestService);

        List<AddtlAmtGrp> addlGrps = requestService.getAddtlAmtGrp();
        if (addlGrps != null) {
            List<AddtlAmtGrp> addtlAmtGr = ebtReqDtl.getAddtlAmtGrp();
            for (AddtlAmtGrp grp : addlGrps
            ) {
                addtlAmtGr.add(grp);
            }
        }
        getGmfmv().setEBTRequest(ebtReqDtl);
        return errorMsg;
    }

    private EBTRequestDetails getEbtRequestDetails(FDRCRequestService requestService) {
        EBTRequestDetails ebtReqDtl;
        ebtReqDtl = new EBTRequestDetails();
        ebtReqDtl.setCommonGrp(requestService.getCommonGrp());
        ebtReqDtl.setCardGrp(requestService.getCardGrp());
        ebtReqDtl.setPINGrp(requestService.getPINGrp());
        ebtReqDtl.setEbtGrp(requestService.getEBTGrp());
        ebtReqDtl.setTAGrp(requestService.getTAGrp());
        return ebtReqDtl;
    }
}