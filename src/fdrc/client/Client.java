package fdrc.client;

import fdrc.base.IRequestProcessor;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.ValidateRequest;
import fdrc.proxy.PymtTypeType;
import fdrc.utils.JsonBuilder;

public class Client {
    // this is called by POS router service
    public String Call(String json){
        Request request = JsonBuilder.getRequestFromJsonString(json);
        Response response = processRequest(request);
        return response.responseRaw;
//        return null; // todo, return response
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.processRequest(null);
    }

    public Response processRequest(Request request){
        Response resposne = null;
        IRequestProcessor requestProcessor = null;
        //todo: temp code, to remove in prod: begin
        if (request == null)
            request = JsonBuilder.getRequestFromJson("payload.json");
        // todo: end
        if (request == null) return new Response("invalid payload.");
        String errorMessage = new ValidateRequest().validate(request);
        if (errorMessage != "")
            return new Response(errorMessage);

        // todo: introduce an interface and implement it by each request class such as credit, debit, EBT
        switch (PymtTypeType.fromValue(request.pymtType)){
            case CREDIT:
                requestProcessor = new CreditRequest();
                resposne = requestProcessor.processRequest(request);
                break;
                // todo:
            case DEBIT:
                requestProcessor = new DebitRequest();
                resposne = requestProcessor.processRequest(request);
                break;
        }
        return resposne;
    }

    public Response processCreditRequest(final Request request){
        /* Create request for a Credit: sale, auth etc., */
        Response response = null;
        CreditRequest creditReq = new CreditRequest();
        response = creditReq.processRequest(request);
        return response;
    }

    public Response processDebitRequest(){
        Response response = null;
        DebitRequest debitRequest = new DebitRequest();
//        response = debitRequest.processDebitRequest(request); // todo
        return response;
    }
}
