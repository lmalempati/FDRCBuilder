import fdrc.base.CardInfo;
import fdrc.base.Request;
import fdrc.client.Client;
import fdrc.client.TransactionData;
import fdrc.proxy.TxnTypeType;

public class Test {

    public static void main(String[] args) {
        Client client = new Client();
        Request request = new Request();
        request.txnType = "AUTHORIZATION";
        CardInfo cardInfo = new CardInfo();
//        request.un
        client.Call("");
    }
}
