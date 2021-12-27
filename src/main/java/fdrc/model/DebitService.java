package fdrc.model;

import com.fiserv.merchant.gmfv10.AddtlAmtGrp;
import com.fiserv.merchant.gmfv10.DebitRequestDetails;
import com.fiserv.merchant.gmfv10.DebitResponseDetails;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.RCRequest;
import fdrc.base.RCResponse;
import fdrc.common.FDRCRequestService;

import java.io.Serializable;
import java.util.List;

class DebitService extends GenericService implements Serializable {
    /* builds request object, if */
    public String buildRequest(RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        DebitRequestDetails debitReqDtl = new DebitRequestDetails();
        FDRCRequestService = new FDRCRequestService(RCRequest);
        debitReqDtl = new DebitRequestDetails();
        debitReqDtl.setCommonGrp(FDRCRequestService.getCommonGrp());
        /* Card Group */
        /* Populate values for Card Group */
        debitReqDtl.setCardGrp(FDRCRequestService.getCardGrp());

        List<AddtlAmtGrp> addtlAmtGr = null;
        List<AddtlAmtGrp> addlGrps = FDRCRequestService.getAddtlAmtGrp();
        if (addlGrps != null) {
            addtlAmtGr = debitReqDtl.getAddtlAmtGrp();
            for (AddtlAmtGrp grp : addlGrps
            ) {
                addtlAmtGr.add(grp);
            }
        }
        debitReqDtl.setPINGrp(FDRCRequestService.getPINGrp());
        /* Add the Debit request object to GMF message variant object */
        gmfmv.setDebitRequest(debitReqDtl);
        return "";
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, RCRequest request, RCResponse response) {
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
        response.trnmsnDateTime = request.trnsmitDateTime;
        response.origRespCode = debitResponse.getRespGrp().getRespCode();
        result = true;
        return result;
    }

}
