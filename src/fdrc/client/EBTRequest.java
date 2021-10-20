package fdrc.client;

import fdrc.base.IRequestProcessor;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;
import com.fiserv.merchant.gmfv10.AddtlAmtGrp;
import com.fiserv.merchant.gmfv10.DebitRequestDetails;
import com.fiserv.merchant.gmfv10.EBTRequestDetails;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;

import java.io.Serializable;
import java.util.List;

public class EBTRequest implements Serializable, IRequestProcessor {
    GMFMessageVariants gmfmv = new GMFMessageVariants();

    public String buildRequest(Request request) {
        FiServRequest fiServRequest = null;
        EBTRequestDetails ebtReqDtl = null;
        try {
            ebtReqDtl = new EBTRequestDetails();
            ebtReqDtl.setCommonGrp(fiServRequest.getCommonGrp());
            ebtReqDtl.setCardGrp(fiServRequest.getCardGrp());

            List<AddtlAmtGrp> addtlAmtGr = ebtReqDtl.getAddtlAmtGrp();
            List<AddtlAmtGrp> addlGrps = fiServRequest.getAddtlAmtGrp();
            if (addlGrps != null)
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGr.add(grp);
                }


        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public Response processRequest(Request request) {
        return null;
    }
}
