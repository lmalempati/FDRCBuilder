package fdrc.model;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.RCRequest;
import fdrc.base.RCResponse;
import fdrc.common.FDRCRequestService;
import fdrc.utils.Utils;

import java.io.Serializable;
import java.util.List;

class CreditService extends GenericService implements Serializable {

    @Override
    public String buildRequest(final RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        String errorMsg = "";
        CreditRequestDetails creditReqDtl = new CreditRequestDetails();

        creditReqDtl.setOrigAuthGrp(FDRCRequestService.getOrigAuthGrp());

        creditReqDtl.setCommonGrp(FDRCRequestService.getCommonGrp());

        creditReqDtl.setAltMerchNameAndAddrGrp(FDRCRequestService.getAltMerchNameAndAddrGrp());

        creditReqDtl.setCardGrp(FDRCRequestService.getCardGrp());
        // CardTypeType.valueOf(request.cardInfo.cardType.toUpperCase())
        if (Utils.isNotNullOrEmpty(RCRequest.cardType))
            switch (Utils.toEnum(CardTypeType.class, RCRequest.cardType)) {
                case VISA:
                    creditReqDtl.setVisaGrp(FDRCRequestService.getVisaGrp());
                    break;
                case MASTER_CARD:
                    creditReqDtl.setMCGrp(FDRCRequestService.getMasterCardGrp());
                    break;
                case JCB:
                case DISCOVER:
                case DINERS:
                    creditReqDtl.setDSGrp(FDRCRequestService.getDiscoverGrp());
                    break;
                case AMEX:
                    creditReqDtl.setAmexGrp(FDRCRequestService.getAmexGrp());
            }

        /* Addtl Amount Group
         * Getting the reference object of the AddtlAmtGrp list and add the
         * AddtlAmtGrp object to the list
         */
        List<AddtlAmtGrp> addtlAmtGr = null;
        List<AddtlAmtGrp> addlGrps = FDRCRequestService.getAddtlAmtGrp();
        if (addlGrps != null) {
            addtlAmtGr = creditReqDtl.getAddtlAmtGrp();
            for (AddtlAmtGrp grp : addlGrps
            ) {
                addtlAmtGr.add(grp);
            }
        }
        /* ECommerce Group */
        creditReqDtl.setEcommGrp(FDRCRequestService.getEcommGrp());

        /* CustInfoGrp Group
         * Assign the CustInfoGrp Group object to the property of CreditSaleRequest object */
        creditReqDtl.setCustInfoGrp(FDRCRequestService.getCustInfoGrp());
        // TA grp
        creditReqDtl.setTAGrp(FDRCRequestService.getTAGrp(RCRequest));

        /* Add the credit request object to GMF message variant object */
        gmfmv.setCreditRequest(creditReqDtl);
        return errorMsg;
    }

    /*Transaction response in XML format received from Data wire */
    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, RCRequest request, RCResponse response) {
        boolean result = false;
        if (gmfmvResponse.getCreditResponse() == null) {
            throw new InvalidResponseXml("invalid response");
        }
        CreditResponseDetails creditResponse = gmfmvResponse.getCreditResponse();
        response.respCode = creditResponse.getRespGrp().getRespCode();
        response.addtlRespData = creditResponse.getRespGrp().getAddtlRespData();

        response.origAuthID = creditResponse.getRespGrp().getAuthID();
        response.origSTAN = creditResponse.getCommonGrp().getSTAN();//?
        response.origLocalDateTime = creditResponse.getCommonGrp().getLocalDateTime();
        response.origTranDateTime =  creditResponse.getCommonGrp().getTrnmsnDateTime();
        response.origRespCode = creditResponse.getRespGrp().getRespCode();
        response.trnmsnDateTime = request.trnsmitDateTime;
        response.refNum = creditResponse.getCommonGrp().getRefNum();
        response.orderNum = creditResponse.getCommonGrp().getOrderNum();

        if (creditResponse.getVisaGrp() != null) {
            response.transID = creditResponse.getVisaGrp().getTransID();
            response.cardLevelResult = creditResponse.getVisaGrp().getCardLevelResult();
            response.aci = creditResponse.getVisaGrp().getACI();
            response.spendQInd = creditResponse.getVisaGrp().getSpendQInd();
        }
        if (creditResponse.getMCGrp() != null) {
            response.banknetData = creditResponse.getMCGrp().getBanknetData();
        }
        if (creditResponse.getDSGrp() != null) {
            response.discNRID = creditResponse.getDSGrp().getDiscNRID();
            response.discTransQualifier = creditResponse.getDSGrp().getDiscTransQualifier();
        }
        if (creditResponse.getAmexGrp() != null) {
            response.amexTranID = creditResponse.getAmexGrp().getAmExTranID();
        }

        if (creditResponse.getTAGrp() != null) {
//            response.sctyLvl = creditResponse.getTAGrp().getSctyLvl().toString();
//            response.tknType = creditResponse.getTAGrp().getTknType();
            response.tkn = creditResponse.getTAGrp().getTkn();
        }

        result = true;
        return result;
    }
}
