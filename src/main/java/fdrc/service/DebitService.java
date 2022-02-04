package fdrc.service;

import com.fiserv.merchant.gmfv10.AddtlAmtGrp;
import com.fiserv.merchant.gmfv10.DebitRequestDetails;
import fdrc.model.RCRequest;

import java.io.Serializable;
import java.util.List;

class DebitService extends BaseService implements Serializable {
    /* builds request object, if */
    public String buildFDRCRequest(RCRequest RCRequest, FDRCRequestService requestService) {
        String message = "";
        try {
            DebitRequestDetails debitReqDtl = getDebitRequestDetails(requestService);

            List<AddtlAmtGrp> addlGrps = requestService.getAddtlAmtGrp();
            if (addlGrps != null) {
                List<AddtlAmtGrp> addtlAmtGr = debitReqDtl.getAddtlAmtGrp();
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGr.add(grp);
                }
            }
            /* Add the Debit request object to GMF message variant object */
            getGmfmv().setDebitRequest(debitReqDtl);
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    private DebitRequestDetails getDebitRequestDetails(FDRCRequestService requestService) {
        DebitRequestDetails debitReqDtl = new DebitRequestDetails();
        debitReqDtl.setCommonGrp(requestService.getCommonGrp());
        /* Card Group */
        /* Populate values for Card Group */
        debitReqDtl.setCardGrp(requestService.getCardGrp());
        debitReqDtl.setPINGrp(requestService.getPINGrp());
        debitReqDtl.setTAGrp(requestService.getTAGrp());
        return debitReqDtl;
    }
}
