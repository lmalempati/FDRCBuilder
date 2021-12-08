package fdrc.client;

import com.fiserv.merchant.gmfv10.AddtlAmtGrp;
import com.fiserv.merchant.gmfv10.DebitRequestDetails;
import com.fiserv.merchant.gmfv10.DebitResponseDetails;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;

import java.io.Serializable;
import java.util.List;

class DebitRequest extends GenericRequest implements Serializable {
    /* builds request object, if */
    public String buildRequest(Request request, FiServRequest fiServRequest) {
        DebitRequestDetails debitReqDtl = new DebitRequestDetails();
        fiServRequest = new FiServRequest(request);
        debitReqDtl = new DebitRequestDetails();
        debitReqDtl.setCommonGrp(fiServRequest.getCommonGrp());
        /* Card Group */
        /* Populate values for Card Group */
        debitReqDtl.setCardGrp(fiServRequest.getCardGrp());

        List<AddtlAmtGrp> addtlAmtGr = null;
        List<AddtlAmtGrp> addlGrps = fiServRequest.getAddtlAmtGrp();
        if (addlGrps != null) {
            addtlAmtGr = debitReqDtl.getAddtlAmtGrp();
            for (AddtlAmtGrp grp : addlGrps
            ) {
                addtlAmtGr.add(grp);
            }
        }
        debitReqDtl.setPINGrp(fiServRequest.getPINGrp());
        /* Add the Debit request object to GMF message variant object */
        gmfmv.setDebitRequest(debitReqDtl);
        return "";
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, Response response) {
        boolean result = false;
        if (gmfmvResponse.getDebitResponse() == null) {
            throw new InvalidResponseXml("invalid response");
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
