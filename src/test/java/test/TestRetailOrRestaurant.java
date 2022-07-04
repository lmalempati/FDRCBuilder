package test;

import fdrc.model.RCRequest;
import fdrc.utils.JsonBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class TestRetailOrRestaurant extends TestGeneric {
    public static final String PATH = "src/test/resources/RetailOrRestt/";

    @ParameterizedTest
    @ValueSource(strings =
            {
                    "Credit-Visa-Sale-dfrdAuthInd-Test.json;000;0",
                    "Credit-Visa-AuthCompl-swipe-200019960020.json;000;1", "Credit-Visa-AuthCompl-swipe-200019960021.json;000",
                    "Moto-Verification-NoEncr-4012000000000529.json;000" // Verification
            })
    public void runParameterizedTests(String fileName) {
        parameterizedTest(fileName, PATH);
    }

    public RCRequest getPayload(String fileName) {
        return JsonBuilder.getRequestFromJsonString(fileName);
    }


}
