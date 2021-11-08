package fdrc.client;

import com.fiserv.merchant.gmfv10.AddtlAmtGrp;
import com.fiserv.merchant.gmfv10.EBTRequestDetails;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;
import java.io.Serializable;
import java.util.List;

public class EBTRequest extends GenericRequest implements Serializable {
    public String buildRequest(Request request) {
        String errorMsg = null;
        FiServRequest fiServRequest = null;
        EBTRequestDetails ebtReqDtl = null;
        try {
            fiServRequest = new FiServRequest(request);
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
            ebtReqDtl.setPINGrp(fiServRequest.getPINGrp());
            ebtReqDtl.setEbtGrp(fiServRequest.getEBTGrp(request));
            gmfmv.setEBTRequest(ebtReqDtl);
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
        return false;
    }
}