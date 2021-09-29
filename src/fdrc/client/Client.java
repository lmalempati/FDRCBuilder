package fdrc.client;

import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.ValidateRequest;
import fdrc.http.HTTPPostHandler;
import fdrc.proxy.PymtTypeType;
import fdrc.utils.JsonBuilder;

public class Client {
    // this is called by POS router service
    public String Call(String json){
        Request request = JsonBuilder.GetRequestFromJsonString(json);
        Response response = ProcessRequest(request);
        return response.errorMsg;
//        return null; // todo, return response
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.ProcessRequest(null);
    }

    public Response ProcessRequest(Request request){
        String resposne = "";
        //todo: temp code, to remove in prod: begin
        if (request == null)
            request = JsonBuilder.GetRequestFromJson();
        // todo :end
        if (request == null) return new Response("invalid payload.");
        String errorMessage = new ValidateRequest().validate(request);
        if (errorMessage != "")
            return new Response(errorMessage);

        // PymtTypeType.valueOf(request.pymtType.toUpperCase())
        switch (PymtTypeType.fromValue(request.pymtType)){
            case CREDIT:
                resposne = ProcessCreditRequest(request);
                break;
                // todo:
//            case DEBIT:
        }
        return new Response(resposne);
    }

    public String ProcessCreditRequest(final Request request){
        String requestString = "";
        /*Transaction response in XML format received from Data wire */
        String responseString = "";

        /* Create request for a Credit: sale, auth etc., */
        CreditRequest creditReq = new CreditRequest(request);

        /*Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
        String clientRef = creditReq.GetClientRef();

        requestString = creditReq.GetXMLData();
        requestString = requestString.replaceAll("gmfMessageVariants", "GMF");
        System.out.println("GMF Credit Request == "+requestString);

        try
        {
            //Send data using HTTP POST protocol
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
