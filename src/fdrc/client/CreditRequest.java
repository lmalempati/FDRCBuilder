package fdrc.client;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

import fdrc.common.FiServRequest;
import fdrc.base.Request;
import fdrc.proxy.*;

public class CreditRequest implements Serializable {

    GMFMessageVariants gmfmv = new GMFMessageVariants();
    /* This class is generated from XSD file */
    CreditRequestDetails creditReqDtl = new CreditRequestDetails();
    FiServRequest fiServRequest = null;

    public CreditRequest(final Request request) {
        try {
            fiServRequest = new FiServRequest(request);
        } catch (InvalidObjectException e) {
            throw new RuntimeException(e.getMessage());
        }
        try {
            OrigAuthGrp origAuthGrp = fiServRequest.getOrigAuthGrp();
            creditReqDtl.setOrigAuthGrp(origAuthGrp);

            creditReqDtl.setCommonGrp(fiServRequest.getCommonGrp());

            creditReqDtl.setAltMerchNameAndAddrGrp(fiServRequest.getAltMerchNameAndAddrGrp());

            creditReqDtl.setCardGrp(fiServRequest.getCardGrp());
            // CardTypeType.valueOf(request.cardInfo.cardType.toUpperCase())
            switch (CardTypeType.fromValue(request.cardType)) {
                case VISA:
                    creditReqDtl.setVisaGrp(fiServRequest.getVisaGrp());
                    break;
                case MASTER_CARD:
                    creditReqDtl.setMCGrp(fiServRequest.getMasterCardGrp());
                    break;
            }

            /* Addtl Amount Group
             * Getting the reference object of the AddtlAmtGrp list and add the
             * AddtlAmtGrp object to the list
             */
            List<AddtlAmtGrp> addtlAmtGr = creditReqDtl.getAddtlAmtGrp();
            if (request.addtlAmtInfo != null) {
                List<AddtlAmtGrp> addlGrps = fiServRequest.getAddtlAmtGrp();
                for (AddtlAmtGrp grp : addlGrps
                ) {
                    addtlAmtGr.add(grp);
                }
            }
            /* ECommerce Group */
            creditReqDtl.setEcommGrp(fiServRequest.getEcommGrp());

            /* CustInfoGrp Group
             * Assign the CustInfoGrp Group object to the property of CreditSaleRequest object */
            creditReqDtl.setCustInfoGrp(fiServRequest.getCustInfoGrp());
            /* Add the credit request object to GMF message variant object */
            gmfmv.setCreditRequest(creditReqDtl);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /* Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
    public String GetClientRef() {
        String clientRef = "";

        CreditRequestDetails creditReq = gmfmv.getCreditRequest();
        clientRef = creditReq.getCommonGrp().getSTAN() + "|" + creditReq.getCommonGrp().getTPPID();
        clientRef = "00" + clientRef;

        return clientRef;
    }

    /* The below method will transform the transaction request object to an XML string in UTF-8 encoding.
     * It will convert gmfmv object into serialized XML data which will be sent to Data wire.
     * */
    public String GetXMLData() {
        StringWriter stringWriter = new StringWriter();
        String returnValue = "";
        try {
            JAXBContext context = null;
            Marshaller marshaller = null;
            context = JAXBContext.newInstance(GMFMessageVariants.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(gmfmv, stringWriter);

            returnValue = stringWriter.toString();
        } catch (JAXBException j) {
            System.out.println("GetXMLData Exception: " + j);
            j.printStackTrace();
        } catch (Exception e) {
            System.out.println("GetXMLData Exception: " + e);
            e.printStackTrace();
        }
        return returnValue;
    }

}
