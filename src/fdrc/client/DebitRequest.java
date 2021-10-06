package fdrc.client;

import fdrc.base.Request;
import fdrc.common.FiServRequest;
import fdrc.proxy.*;

import java.io.Serializable;
import java.util.List;

public class DebitRequest implements Serializable {
    GMFMessageVariants gmfmv = new GMFMessageVariants();
    FiServRequest fiServRequest = null;
    DebitRequestDetails debitReqDtl = new DebitRequestDetails();

    /* builds request object, if */
    public String buildRequest(Request request){
        try {
            FiServRequest fiServRequest = new FiServRequest(request);
            debitReqDtl.setCommonGrp(fiServRequest.getCommonGrp());
            /* Card Group */
            /* Populate values for Card Group */
            debitReqDtl.setCardGrp(fiServRequest.getCardGrp());

            List<AddtlAmtGrp> addtlAmtGr = debitReqDtl.getAddtlAmtGrp();
            List<AddtlAmtGrp> addlGrps = fiServRequest.getAddtlAmtGrp();
            if (addlGrps != null)
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGr.add(grp);
                }
            debitReqDtl.setPINGrp(fiServRequest.getPinGrp());
            /* Add the Debit request object to GMF message variant object */
            gmfmv.setDebitRequest(debitReqDtl);
            return  "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
