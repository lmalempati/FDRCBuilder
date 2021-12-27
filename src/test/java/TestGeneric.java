import fdrc.base.RCResponse;
import fdrc.model.Client;
import fdrc.utils.JsonBuilder;
import static org.junit.Assert.assertEquals;

public class TestGeneric {
    public void parameterizedTest(String fileName, String PATH) {
        String[] values = fileName.split(";");
        if (values.length < 2) return;
        String fileSaleAuth = values[0];
        String rc = values[1];
        boolean subsequent = values.length > 2 && values[2].equals("1");

        RCResponse RCResponse = new Client().submitRequest(JsonBuilder.getRequestFromJson(PATH + fileSaleAuth));
        assertEquals(rc, RCResponse.respCode);

        if (subsequent) {
            int dotIndex = fileName.lastIndexOf(".");
            if (fileSaleAuth.charAt(dotIndex - 1) == '1') return;

            StringBuffer buffer = new StringBuffer(fileSaleAuth);
            buffer.setCharAt(dotIndex - 1, '1');
            fileSaleAuth = buffer.toString();
            JsonBuilder.updateCompletionPayload(RCResponse, PATH + fileSaleAuth);
        }
    }

}