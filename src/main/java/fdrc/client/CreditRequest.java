package fdrc.client;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.FiServRequest;
import fdrc.utils.Utils;

import java.io.Serializable;
import java.util.List;

public class CreditRequest extends GenericRequest implements Serializable {

    @Override
    public String buildRequest(final Request request) {
        String errorMsg = "";
        CreditRequestDetails creditReqDtl = new CreditRequestDetails();
        FiServRequest fiServRequest = new FiServRequest(request);
        try {
            creditReqDtl.setOrigAuthGrp(fiServRequest.getOrigAuthGrp());

            creditReqDtl.setCommonGrp(fiServRequest.getCommonGrp());

            creditReqDtl.setAltMerchNameAndAddrGrp(fiServRequest.getAltMerchNameAndAddrGrp());

            creditReqDtl.setCardGrp(fiServRequest.getCardGrp());
            // CardTypeType.valueOf(request.cardInfo.cardType.toUpperCase())
            if (Utils.isNotNullOrEmpty(request.cardType))
            switch (Utils.toEnum(CardTypeType.class, request.cardType)) {
                case VISA:
                    creditReqDtl.setVisaGrp(fiServRequest.getVisaGrp());
                    break;
                case MASTER_CARD:
                    creditReqDtl.setMCGrp(fiServRequest.getMasterCardGrp());
                    break;
                case JCB:
                case DISCOVER:
                case DINERS:
                        creditReqDtl.setDSGrp(fiServRequest.getDiscoverGrp());
                    break;
                case AMEX:
                    creditReqDtl.setAmexGrp(fiServRequest.getAmexGrp());
            }

            /* Addtl Amount Group
             * Getting the reference object of the AddtlAmtGrp list and add the
             * AddtlAmtGrp object to the list
             */
            List<AddtlAmtGrp> addtlAmtGr = creditReqDtl.getAddtlAmtGrp();
            List<AddtlAmtGrp> addlGrps = fiServRequest.getAddtlAmtGrp();
            if (addlGrps != null)
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGr.add(grp);
                }
            /* ECommerce Group */
            creditReqDtl.setEcommGrp(fiServRequest.getEcommGrp());

            /* CustInfoGrp Group
             * Assign the CustInfoGrp Group object to the property of CreditSaleRequest object */
            creditReqDtl.setCustInfoGrp(fiServRequest.getCustInfoGrp());
            /* Add the credit request object to GMF message variant object */
            gmfmv.setCreditRequest(creditReqDtl);
        } catch (IllegalArgumentException | UnsupportedEnumValueException e) {
            errorMsg = e.getMessage();
        } catch (Exception e) {
            errorMsg = e.getMessage();
        } finally {
            fiServRequest = null;
        }
        return errorMsg;
    }

    /*Transaction response in XML format received from Data wire */
    @Override
    public boolean getResponse(GMFMessageVariants gmfmvResponse, Response response) {
        boolean result = false;
        if (gmfmvResponse.getCreditResponse() == null) {
            throw new RuntimeException("invalid response");
        }
        CreditResponseDetails creditResponse = gmfmvResponse.getCreditResponse();

        response.respCode = creditResponse.getRespGrp().getRespCode();
        response.addtlRespData = creditResponse.getRespGrp().getAddtlRespData();

        response.origAuthID = creditResponse.getRespGrp().getAuthID();
        response.origSTAN = creditResponse.getCommonGrp().getSTAN();//?
        response.origLocalDateTime = creditResponse.getCommonGrp().getLocalDateTime();
        response.origTranDateTime = creditResponse.getCommonGrp().getTrnmsnDateTime();
        response.origRespCode = creditResponse.getRespGrp().getRespCode();
        response.refNum = creditResponse.getCommonGrp().getRefNum();
        response.orderNum = creditResponse.getCommonGrp().getOrderNum();

        if (creditResponse.getVisaGrp() != null) {
            response.transID = creditResponse.getVisaGrp().getTransID();
            response.cardLevelResult = creditResponse.getVisaGrp().getCardLevelResult();
            response.aci =  creditResponse.getVisaGrp().getACI();
        }
        if (creditResponse.getMCGrp() != null) {
            response.banknetData = creditResponse.getMCGrp().getBanknetData();
        }
        if (creditResponse.getDSGrp() != null) {
            response.discNRID = creditResponse.getDSGrp().getDiscNRID();
            response.discTransQualifier = creditResponse.getDSGrp().getDiscTransQualifier();
        }
        result = true;
        return result;
    }
}
