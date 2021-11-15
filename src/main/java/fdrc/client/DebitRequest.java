package fdrc.client;

import com.fiserv.merchant.gmfv10.AddtlAmtGrp;
import com.fiserv.merchant.gmfv10.DebitRequestDetails;
import com.fiserv.merchant.gmfv10.DebitResponseDetails;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;

import java.io.Serializable;
import java.util.List;

class DebitRequest extends GenericRequest implements Serializable {
    /* builds request object, if */
    public String buildRequest(Request request) {
        DebitRequestDetails debitReqDtl = new DebitRequestDetails();
        FiServRequest fiServRequest = new FiServRequest(request);
        try {
            fiServRequest = new FiServRequest(request);
            debitReqDtl = new DebitRequestDetails();
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
            debitReqDtl.setPINGrp(fiServRequest.getPINGrp());
            /* Add the Debit request object to GMF message variant object */
            gmfmv.setDebitRequest(debitReqDtl);
            return "";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            fiServRequest = null;
        }
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, Response response) {
        boolean result = false;
        if (gmfmvResponse.getDebitResponse() == null) {
            throw new RuntimeException("invalid response");
        }
        DebitResponseDetails debitResponse = gmfmvResponse.getDebitResponse();
        response.respCode = debitResponse.getRespGrp().getRespCode();
        response.addtlRespData = debitResponse.getRespGrp().getAddtlRespData();
        response.refNum = debitResponse.getCommonGrp().getRefNum();
        response.orderNum = debitResponse.getCommonGrp().getOrderNum();

        response.origAuthID = debitResponse.getRespGrp().getAuthID();
        response.origSTAN = debitResponse.getCommonGrp().getSTAN();
        response.origLocalDateTime = debitResponse.getCommonGrp().getLocalDateTime();
        response.origTranDateTime = debitResponse.getCommonGrp().getTrnmsnDateTime();
        response.origRespCode = debitResponse.getRespGrp().getRespCode();
        result = true;
        return result;
    }

}
