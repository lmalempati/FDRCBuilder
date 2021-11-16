import fdrc.base.Response;
import fdrc.client.Client;
import fdrc.utils.JsonBuilder;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

//@RunWith(Parameterized.class)
public class TestRestaurant extends TestGneric {
    public static final String PATH = "src/test/resources/Restaurant/";

    @ParameterizedTest
    @ValueSource(strings =
            {
                    "BalInq-VisaSaleSwipe-200066500010.json;000",
                    "Credit-Visa-AuthCompl-keyed-201012850010.json;000;1", "Credit-Visa-AuthCompl-keyed-201012850011.json;000",
                    "Credit-Visa-Refund-Keyedin-200070140010.json;000", // refund
                    "TATokenRequest-200292130010.json;000"
            }
    )
    public void runAnyTest(String fileName) {
        parametrizedTest(fileName, PATH);
    }


}
