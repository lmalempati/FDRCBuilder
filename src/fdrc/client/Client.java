package fdrc.client;

import fdrc.common.Request;
import fdrc.http.HTTPPostHandler;
import fdrc.proxy.PymtTypeType;
import fdrc.utils.JsonBuilder;
import fdrc.utils.Utils;

public class Client {
    public void Call(Request request){
        System.out.println(request.cardInfo.acctNum);
        System.out.println(request.txnType.toString());
    }
    public static void main(String[] args) {
//        ProcessCreditRequest(null);
        System.out.println(ProcessRequest(""));
    }

    public static String ProcessRequest(String json){
        System.out.println("this is from FDRC module");

        Request request = JsonBuilder.GetRequestFromJsonString(json);
        if (request == null)
            JsonBuilder.GetRequestFromJson();
        if (request == null) return "";
        String response = "";
        switch (PymtTypeType.valueOf(request.pymtType.toUpperCase())){
            case CREDIT:
                response = ProcessCreditRequest(request);
        }
        return response;
    }

    public static String ProcessCreditRequest(Request request){
        String requestString = "";

        /*Transaction response in XML format received from Data wire */
        String responseString = "";

//        Request request = JsonBuilder.GetRequestFromJson();
        request.refNum = Utils.getOrderNum();

        /*Create Authorization request for a sample Credit Sale transaction.*/
        CreditRequest creditSaleReq = new CreditRequest(request);

        /*Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
        String clientRef = creditSaleReq.GetClientRef();

        requestString = creditSaleReq.GetXMLData();
        requestString = requestString.replaceAll("gmfMessageVariants", "GMF");
        System.out.println("GMF Credit Request == "+requestString);

        //Send data using HTTP POST protocol
        try
        {
            responseString = new HTTPPostHandler().SendMessage(requestString, clientRef);
        }
        catch(Exception e)
        {
            System.out.println("HTTP Exception: " + e);
        }
        System.out.println("Successful HTTP POST Credit response: " + "\n" + responseString + "\n");

        return responseString;
    }

    public String processDebitRequest(){
        /*Create Authorization request for a sample Debit Sale transaction.*/
//        DebitRequest debitSaleReq = new DebitRequest();
//
//        /*Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
//        clientRef = debitSaleReq.GetClientRef();
//
//        requestString = debitSaleReq.GetXMLData();
//        requestString = requestString.replaceAll("gmfMessageVariants", "GMF");
//        System.out.println("GMF Debit Request == "+requestString);
//        //Send data using HTTP POST protocol
//        try
//        {
//            responseString = new HTTPPostHandler().SendMessage(requestString, clientRef);
//        }
//        catch(Exception e)
//        {
//            System.out.println("HTTP Exception: " + e.toString());
//        }
//        System.out.println("Successful HTTP POST Debit response: " + "\n" + responseString + "\n");
        return "";
    }
}
