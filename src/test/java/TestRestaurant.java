import fdrc.base.Response;
import fdrc.client.Client;
import fdrc.utils.JsonBuilder;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

public class TestRestaurant extends TestGeneric {
    public static final String PATH = "src/test/resources/Restaurant/";

    @ParameterizedTest
    @ValueSource(strings =
            {
                    "BalInq-VisaSaleSwipe-200066500010.json;000",
                    "Credit-Visa-AuthCompl-keyed-201012850010.json;000;1", "Credit-Visa-AuthCompl-keyed-201012850011.json;000",
                    "Credit-Visa-Refund-Keyedin-200070140010.json;000", // refund
                    "TATokenRequest-200292130010.json;000",
                    "Credit-Visa-AuthCompl-Swipe-200070230010.json;000;1", "Credit-Visa-AuthCompl-Swipe-200070230011.json;000",
                    "Credit-JCB-AuthCompl-Swipe-200070490010.json;000;1", "Credit-JCB-AuthCompl-Swipe-200070490011.json;000",
                    "Credit-MC-AuthCompl-Swipe-201012950010.json;000;1",
                    "Credit-MC-AuthCompl-Swipe-201012950011.json;000",
                    "Credit-TA-MC-AuthCompl-Swipe-200293470010.json;002;1", "Credit-TA-MC-AuthCompl-Swipe-200293470011.json;000"
            }
    )
    public void runTestsRestaurant(String fileName) {
        parameterizedTest(fileName, PATH);
    }


}
