package fdrc.client;

import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.ValidateRequest;
import fdrc.http.HTTPPostHandler;
import fdrc.proxy.PymtTypeType;
import fdrc.utils.JsonBuilder;

import javax.xml.ws.http.HTTPException;

public class Client {
    // this is called by POS router service
    public String Call(String json){
        Request request = JsonBuilder.getRequestFromJsonString(json);
        Response response = ProcessRequestActual(request);
        return response.errorMsg;
//        return null; // todo, return response
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.ProcessRequest(null);
    }

    public String ProcessRequest(Request request) {
        return "message from FDRC builder";
    }

    public Response ProcessRequestActual(Request request){
        Response resposne = null;
        //todo: temp code, to remove in prod: begin
        if (request == null)
            request = JsonBuilder.GetRequestFromJson("payload.json");
        // todo: end
        if (request == null) return new Response("invalid payload.");
        String errorMessage = new ValidateRequest().validate(request);
        if (errorMessage != "")
            return new Response(errorMessage);

        // todo: introduce an interface and implement it by each request class such as credit, debit, EBT
        switch (PymtTypeType.fromValue(request.pymtType)){
            case CREDIT:
                resposne = ProcessCreditRequest(request);
                break;
                // todo:
//            case DEBIT:
        }
        return resposne;
    }

    public Response ProcessCreditRequest(final Request request){
        /* Create request for a Credit: sale, auth etc., */
        Response response = null;
        CreditRequest creditReq = new CreditRequest();
        response = creditReq.processRequest(request);
        return response;
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
