package fdrc.service;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
import fdrc.common.FDRCRequestService;
import fdrc.utils.Utils;

import java.io.Serializable;
import java.util.List;

class CreditService extends GenericService implements Serializable {

    @Override
    public String buildRequest(final RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        String message = "";
        try {
            CreditRequestDetails creditReqDtl = getCreditRequestDetails(RCRequest, FDRCRequestService);
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
            List<AddtlAmtGrp> addtlAmtGrpList = null;
            List<AddtlAmtGrp> addlGrps = FDRCRequestService.getAddtlAmtGrp();
            if (addlGrps != null) {
                addtlAmtGrpList = creditReqDtl.getAddtlAmtGrp();
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGrpList.add(grp);
                }
            }
            /* Add the credit request object to GMF message variant object */
            gmfmv.setCreditRequest(creditReqDtl);
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }
    private CreditRequestDetails getCreditRequestDetails(RCRequest RCRequest, FDRCRequestService FDRCRequestService) {
        CreditRequestDetails creditReqDtl = new CreditRequestDetails();
        creditReqDtl.setOrigAuthGrp(FDRCRequestService.getOrigAuthGrp());
        creditReqDtl.setCommonGrp(FDRCRequestService.getCommonGrp());
        creditReqDtl.setAltMerchNameAndAddrGrp(FDRCRequestService.getAltMerchNameAndAddrGrp());
        creditReqDtl.setCardGrp(FDRCRequestService.getCardGrp());
        /* ECommerce Group */
        creditReqDtl.setEcommGrp(FDRCRequestService.getEcommGrp());
        /* CustInfoGrp Group
         * Assign the CustInfoGrp Group object to the property of CreditSaleRequest object */
        creditReqDtl.setCustInfoGrp(FDRCRequestService.getCustInfoGrp());
        // TA grp
        creditReqDtl.setTAGrp(FDRCRequestService.getTAGrp(RCRequest));
        return creditReqDtl;
    }
}
