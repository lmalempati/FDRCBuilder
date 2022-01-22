package fdrc.service;

import com.fiserv.merchant.gmfv10.AddtlAmtGrp;
import com.fiserv.merchant.gmfv10.CardTypeType;
import com.fiserv.merchant.gmfv10.CreditRequestDetails;
import fdrc.model.RCRequest;
import fdrc.utils.Utils;

import java.util.List;

class CreditService extends BaseService {

    @Override
    String buildRequest(final RCRequest RCRequest, FDRCRequestService requestService) {
        String message = "";
        try {
            CreditRequestDetails creditReqDtl = populateCreditRequestDetails(RCRequest, requestService);
            // CardTypeType.valueOf(request.cardInfo.cardType.toUpperCase())
            if (Utils.isNotNullOrEmpty(RCRequest.cardType))
                switch (Utils.toEnum(CardTypeType.class, RCRequest.cardType)) {
                    case VISA:
                        creditReqDtl.setVisaGrp(requestService.getVisaGrp());
                        break;
                    case MASTER_CARD:
                        creditReqDtl.setMCGrp(requestService.getMasterCardGrp());
                        break;
                    case JCB:
                    case DISCOVER:
                    case DINERS:
                        creditReqDtl.setDSGrp(requestService.getDiscoverGrp());
                        break;
                    case AMEX:
                        creditReqDtl.setAmexGrp(requestService.getAmexGrp());
                }

            /* Addtl Amount Group
             * Getting the reference object of the AddtlAmtGrp list and add the
             * AddtlAmtGrp object to the list
             */
            List<AddtlAmtGrp> addlGrps = requestService.getAddtlAmtGrp();
            if (addlGrps != null) {
                List<AddtlAmtGrp> addtlAmtGrpList = creditReqDtl.getAddtlAmtGrp();
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGrpList.add(grp);
                }
            }
            /* Add the credit request object to GMF message variant object */
            getGmfmv().setCreditRequest(creditReqDtl);
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }
    private CreditRequestDetails populateCreditRequestDetails(RCRequest RCRequest, FDRCRequestService requestService) {
        CreditRequestDetails creditReqDtl = new CreditRequestDetails();
        creditReqDtl.setOrigAuthGrp(requestService.getOrigAuthGrp());
        creditReqDtl.setCommonGrp(requestService.getCommonGrp());
        creditReqDtl.setAltMerchNameAndAddrGrp(requestService.getAltMerchNameAndAddrGrp());
        creditReqDtl.setCardGrp(requestService.getCardGrp());
        /* ECommerce Group */
        creditReqDtl.setEcommGrp(requestService.getEcommGrp());
        /* CustInfoGrp Group
         * Assign the CustInfoGrp Group object to the property of CreditSaleRequest object */
        creditReqDtl.setCustInfoGrp(requestService.getCustInfoGrp());
        // TA grp
        creditReqDtl.setTAGrp(requestService.getTAGrp());
        return creditReqDtl;
    }
}