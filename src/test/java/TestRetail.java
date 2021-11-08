import fdrc.base.Response;
import fdrc.client.Client;
import fdrc.utils.JsonBuilder;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class TestRetail {
    public static final String PATH = "src/test/resources/Retail/";

    @ParameterizedTest
    @ValueSource(strings =
            {
                    "debit-payload-200070040010.json;000", //cashback
                    "debit-payload-200070100010.json;000;1", "debit-payload-200070100011.json;000",
                    "debit-sale-void-200070690010.json;000;1", "debit-sale-void-200070690011.json;000",
                    "Amex-Sale-200069950010.json;000",
                    "EBT-Sale-201008250010.json;000"
            }

    )
    public void runAnyTest(String fileName) {
        String[] inputs=  fileName.split(";");
        if (inputs.length < 2) return; // not entered response code to check with? stop processing.

        String fn =  inputs[0];
        String rc = inputs[1];
        boolean subsequent = inputs.length > 2 && inputs[2].equals("1");

        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson(PATH + fn));
        assertEquals(rc, response.respCode);

        if (subsequent){
            int dotIndex = fileName.lastIndexOf(".");
            if (fn.charAt(dotIndex - 1) == '1')  return;

            StringBuffer buffer = new StringBuffer(fn);
            buffer.setCharAt(dotIndex-1, '1');
            fn = buffer.toString();
            JsonBuilder.updateCompletionPayload(response, PATH + fn);
        }
    }
}
