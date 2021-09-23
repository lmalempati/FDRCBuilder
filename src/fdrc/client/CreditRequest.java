package fdrc.client;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

import fdrc.common.BuildRequest;
import fdrc.common.Request;
import fdrc.proxy.*;

/* The below code will prepare Credit Sale transaction request object populating various
 * transaction parameters. The parameter values used below are test data and should not used for
 * actual real-time authorization.
 * */
public class CreditRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /* This class is generated from XSD file */
    GMFMessageVariants gmfmv = new GMFMessageVariants();
    /* This class is generated from XSD file */
    CreditRequestDetails creditReqDtl = new CreditRequestDetails();
    BuildRequest prepareRequest = new BuildRequest();

    public CreditRequest(Request request) {
		/* Assigning value to the objects
		 * This class CommonGrp is generated from XSD file.
		 * Transaction elements inside this common group will be present to all transactions.
		/* Common Group */
        /*
         * Assign the Common Group object to the property of CreditSaleRequest
         * object
         */
        creditReqDtl.setCommonGrp(prepareRequest.getCommonGrp(request));

        creditReqDtl.setAltMerchNameAndAddrGrp(prepareRequest.getAltMerchNameAndAddrGrp(request));

        creditReqDtl.setCardGrp(prepareRequest.getCardGrp(request));

        creditReqDtl.setVisaGrp(prepareRequest.getVisaGrp());
        /* Addtl Amount Group */
        /*  Populate values for Addtl Amount Group */
        AddtlAmtGrp addAmtGrp = new AddtlAmtGrp();
        /* An identifier used to indicate whether or not the
         * terminal/software can support partial authorization approvals.  */
        addAmtGrp.setPartAuthrztnApprvlCapablt("1");

        /*
         * Getting the reference object of the AddtlAmtGrp list and add the
         * AddtlAmtGrp object to the list
         */
        List<AddtlAmtGrp> addtlAmtGr = creditReqDtl.getAddtlAmtGrp();
        addtlAmtGr.add(addAmtGrp);


//
//		/*
//		 * Assign the Visa Group object to the property of CreditSaleRequest
//		 * object
//		 */
//		creditReqDtl.setVisaGrp(visaGrp);


        /* This class is generated from XSD file */
        /* ECommerce Group */
        /* Populate values for ECommerce Group */

        /*
         * Assign the ECommerce Group object to the property of CreditSaleRequest
         * object
         */

        creditReqDtl.setEcommGrp(prepareRequest.getEcommGrp());

        /* This class is generated from XSD file.*/
        /* CustInfoGrp Group */

        /*
         * Assign the CustInfoGrp Group object to the property of CreditSaleRequest
         * object
         */
        creditReqDtl.setCustInfoGrp(prepareRequest.getCustInfoGrp(request));
        /* Add the credit request object to GMF message variant object */
        gmfmv.setCreditRequest(creditReqDtl);
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
