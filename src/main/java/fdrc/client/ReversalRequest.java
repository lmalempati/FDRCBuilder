package fdrc.client;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;
import fdrc.utils.Utils;

import java.io.Serializable;
import java.util.List;

class ReversalRequest extends GenericRequest implements Serializable {

    @Override
    public String buildRequest(Request request, FiServRequest fiServRequest) {
        String errorMsg = null;
        VoidTOReversalRequestDetails reversalRequestDetails = new VoidTOReversalRequestDetails();
        reversalRequestDetails.setCommonGrp(fiServRequest.getCommonGrp());
        reversalRequestDetails.setAltMerchNameAndAddrGrp(fiServRequest.getAltMerchNameAndAddrGrp());
        reversalRequestDetails.setCardGrp(fiServRequest.getCardGrp());
        List<AddtlAmtGrp> addtlAmtGr = null;
        List<AddtlAmtGrp> addlGrps = fiServRequest.getAddtlAmtGrp();
        if (addlGrps != null) {
            addtlAmtGr = reversalRequestDetails.getAddtlAmtGrp();
            for (AddtlAmtGrp grp : addlGrps
            ) {
                addtlAmtGr.add(grp);
            }
        }
        if (Utils.isNotNullOrEmpty(request.cardType))
            switch (Utils.toEnum(CardTypeType.class, request.cardType)) {
                case VISA:
                    reversalRequestDetails.setVisaGrp(fiServRequest.getVisaGrp());
                    break;
                case MASTER_CARD:
                    reversalRequestDetails.setMCGrp(fiServRequest.getMasterCardGrp());
                    break;
                case JCB:
                case DISCOVER:
                case DINERS:
                    reversalRequestDetails.setDSGrp(fiServRequest.getDiscoverGrp());
                    break;
                case AMEX:
                    reversalRequestDetails.setAmexGrp(fiServRequest.getAmexGrp());
                    break;
            }
        reversalRequestDetails.setEcommGrp(fiServRequest.getEcommGrp());
        reversalRequestDetails.setOrigAuthGrp(fiServRequest.getOrigAuthGrp());
        gmfmv.setReversalRequest(reversalRequestDetails);
        return errorMsg;
    }

    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, Response response) {
        boolean result = false;
        if (gmfmvResponse.getReversalResponse() == null) {
            throw new InvalidResponseXml("invalid response");
        }

        VoidTOReversalResponseDetails reversalResponse = gmfmvResponse.getReversalResponse();
        response.respCode = reversalResponse.getRespGrp().getRespCode();
        response.addtlRespData = reversalResponse.getRespGrp().getAddtlRespData();
        response.origRespCode = reversalResponse.getRespGrp().getRespCode();
        response.origAuthID = reversalResponse.getRespGrp().getAuthID();

        response.origSTAN = reversalResponse.getCommonGrp().getSTAN();//?
        response.origLocalDateTime = reversalResponse.getCommonGrp().getLocalDateTime();
        response.origTranDateTime = reversalResponse.getCommonGrp().getTrnmsnDateTime();
        response.refNum = reversalResponse.getCommonGrp().getRefNum();
        response.orderNum = reversalResponse.getCommonGrp().getOrderNum();
        if (reversalResponse.getCardGrp() != null)
            if (reversalResponse.getMCGrp() != null) {
                response.banknetData = reversalResponse.getMCGrp().getBanknetData();
            }
        if (reversalResponse.getDSGrp() != null) {
            response.discNRID = reversalResponse.getDSGrp().getDiscNRID();
            response.discTransQualifier = reversalResponse.getDSGrp().getDiscTransQualifier();
        }
        if (reversalResponse.getVisaGrp() != null) {
            response.aci = reversalResponse.getVisaGrp().getACI();
            response.cardLevelResult = reversalResponse.getVisaGrp().getCardLevelResult();
            response.transID = reversalResponse.getVisaGrp().getTransID();
            response.spendQInd = reversalResponse.getVisaGrp().getSpendQInd();
        }
        if (reversalResponse.getAmexGrp() != null) {
            response.amexTranID = reversalResponse.getAmexGrp().getAmExTranID();
        }
        result = true;
        return result;
    }

}
