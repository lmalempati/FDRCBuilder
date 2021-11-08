import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.client.Client;
import fdrc.utils.JsonBuilder;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestsMoto {

    @Test // Auth & Compl Amex
    public void creditAmexAuthKeyed() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/Amex-Auth-payload.json"));
        assertEquals("002", response.respCode);
        if (JsonBuilder.updateCompletionPayload(response, "src/test/resources/Moto/Amex-Compl-payload.json"))
            creditAmexCompl();
    }

    private void creditAmexCompl() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/Amex-Compl-payload.json"));
        assertEquals("000", response.respCode);

    }

    @Test// Auth JCB
    public void creditJCBAuthKeyed100070950010() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/JCB-Auth-Keyed-100070950010.json"));
        assertEquals("000", response.respCode);
//        updateCompletionPayload(response, "payloads/Credit-Visa-AuthComple-swipe-200019960021.json");
        if (JsonBuilder.updateCompletionPayload(response, "src/test/resources/Moto/JCB-Compl-Keyed-100070950011.json"))
            creditJCBAuthKeyed100070950011();

    }

    @Test // Auth Discover
    public void creditDSAuthKeyed200070910010() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/DS-Auth-Keyed-200070910010.json"));
        assertEquals("002", response.respCode);
    }

    @Test // AuthVoid or ReversalRequest
    public void creditAuthVoidKeyed200070840010() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/credit-AuthVoid-200070840010.json"));
        assertEquals("000", response.respCode);
        if (JsonBuilder.updateCompletionPayload(response, "src/test/resources/Moto/credit-AuthVoid-200070840011.json")) {
            creditAuthVoidKeyed200070840011();
        }
    }

    public void creditAuthVoidKeyed200070840011() {
        Response response;
        response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/credit-AuthVoid-200070840011.json"));
        assertEquals("000", response.respCode);
    }

    public void creditJCBAuthKeyed100070950011() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/JCB-Compl-Keyed-100070950011.json"));
        assertEquals("000", response.respCode);
    }

    @Test // Refund
    public void refundTest() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/Credit-MC-Refund-Keyedin.json"));
        assertEquals("000", response.respCode);
    }

    @Test // Refund Void
    public void refund200081230010() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/Credit-MC-Refund-Keyed-200081230010.json"));
        assertEquals("000", response.respCode);
        if (JsonBuilder.updateCompletionPayload(response, "src/test/resources/Moto/Credit-MC-RefundVoid-Keyed-200081230011.json")) {
            refundVoid200081230011();
        }
    }

    public void refundVoid200081230011() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/Credit-MC-RefundVoid-Keyed-200081230011.json"));
        assertEquals("000", response.respCode);

    }

    @Test //
    public void balanceInquiry() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/JCB-Compl-Keyed-100070950011.json"));
        assertEquals("000", response.respCode);
    }

//    static Stream<Arguments> arguments = Stream.of(
//            Arguments.of("Discover-AuthCompl-200070910010.json", "000"),
//            Arguments.of("Discover-AuthCompl-200070910011.json", "000")
//    );
//
    //    @Documented
//    @Target(ElementType.METHOD)
//    @Retention(RetentionPolicy.RUNTIME)
//    @ArgumentsSource(VariableArgumentsProvider.class)
//    public @interface VariableSource {
//
//        /**
//         * The name of the static variable
//         */
//        String fileName = "";
//        String value();
//    }
//
//    class VariableArgumentsProvider
//            implements ArgumentsProvider, AnnotationConsumer<VariableSource> {
//
//        private String variableName;
//
//        @Override
//        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
//            return context.getTestClass()
//                    .map(this::getField)
//                    .map(this::getValue)
//                    .orElseThrow(() ->
//                            new IllegalArgumentException("Failed to load test arguments"));
//        }
//
//        @Override
//        public void accept(VariableSource variableSource) {
//            variableName = variableSource.value();
//        }
//
//        private Field getField(Class<?> clazz) {
//            try {
//                return clazz.getDeclaredField(variableName);
//            } catch (Exception e) {
//                return null;
//            }
//        }
//
//        @SuppressWarnings("unchecked")
//        private Stream<Arguments> getValue(Field field) {
//            Object value = null;
//            try {
//                value = field.get(null);
//            } catch (Exception ignored) {
//            }
//
//            return value == null ? null : (Stream<Arguments>) value;
//        }
//    }
//
//    @VariableSource("arguments")
    @ParameterizedTest
    @ValueSource(strings =
            {
//                    "VisaSaleKeyed-200003520020.json;002",
//                    "Credit-MC-AuthCompl-200021950010.json;000", "Credit-MC-AuthCompl-200021950011.json;000",
                    "Discover-AuthCompl-200070910010.json;000;", "Discover-AuthCompl-200070910011.json;000", // Auth & Compl Discover
//                    "Diners-AuthCompl-200070930010.json;000", "Diners-AuthCompl-200070930011.json;000",
//                    "Amex-AuthCompl-201013680010.json;002", "Amex-AuthCompl-201013680011.json;000",
//                    "credit-AuthVoid-200070840010.json;000", "credit-AuthVoid-200070840011.json;000" // Auth & Compl..
            })
    public void runParameterizedTests(String fileName) {
        String[] values = fileName.split(";");
        if (values.length < 2) return;
        String fileSaleAuth = values[0];
        String rc = values[1];
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("src/test/resources/Moto/" + fileSaleAuth));
        assertEquals(rc, response.respCode);

        int dotIndex = fileName.lastIndexOf(".");
        if (fileSaleAuth.charAt(dotIndex - 1) == '1') return;

        StringBuffer buffer = new StringBuffer(fileSaleAuth);
        buffer.setCharAt(dotIndex - 1, '1');
        fileSaleAuth = buffer.toString();
//        if (values.length == 5 && values[2].equals("1")) {
        JsonBuilder.updateCompletionPayload(response, "src/test/resources/Moto/" + fileSaleAuth);
//        JsonBuilder.updateCompletionPayload(response, "src/test/resources/Moto/" + values[3]);
//        runParameterizedTests(values[3] + ";" + values[4]);
//        }
    }

    public Request getPayload(String fileName) {
        return JsonBuilder.getRequestFromJsonString(fileName);
    }


}
