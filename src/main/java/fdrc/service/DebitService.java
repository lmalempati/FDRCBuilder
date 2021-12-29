package fdrc.service;

import com.fiserv.merchant.gmfv10.AddtlAmtGrp;
import com.fiserv.merchant.gmfv10.DebitRequestDetails;
import com.fiserv.merchant.gmfv10.DebitResponseDetails;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
import fdrc.common.FDRCRequestService;

import java.io.Serializable;
import java.util.List;

class DebitService extends GenericService implements Serializable {
    /* builds request object, if */
    public String buildRequest(RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        String message = "";
        try {
            DebitRequestDetails debitReqDtl = getDebitRequestDetails(FDRCRequestService);

            List<AddtlAmtGrp> addtlAmtGr = null;
            List<AddtlAmtGrp> addlGrps = FDRCRequestService.getAddtlAmtGrp();
            if (addlGrps != null) {
                addtlAmtGr = debitReqDtl.getAddtlAmtGrp();
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGr.add(grp);
                }
            }
            /* Add the Debit request object to GMF message variant object */
            gmfmv.setDebitRequest(debitReqDtl);
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    private DebitRequestDetails getDebitRequestDetails(FDRCRequestService FDRCRequestService) {
        DebitRequestDetails debitReqDtl = new DebitRequestDetails();
        debitReqDtl.setCommonGrp(FDRCRequestService.getCommonGrp());
        /* Card Group */
        /* Populate values for Card Group */
        debitReqDtl.setCardGrp(FDRCRequestService.getCardGrp());
        debitReqDtl.setPINGrp(FDRCRequestService.getPINGrp());
        return debitReqDtl;
    }
}
