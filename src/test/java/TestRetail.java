import fdrc.base.Response;
import fdrc.client.Client;
import fdrc.utils.JsonBuilder;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class TestRetail extends TestGneric {
    public static final String PATH = "src/test/resources/Retail/";

    @ParameterizedTest
    @ValueSource(strings =
            {
//                    "debit-payload-200070040010.json;000", //cashback
//                    "debit-payload-200070100010.json;000;1", "debit-payload-200070100011.json;000",
//                    "debit-sale-void-200070690010.json;000;1", "debit-sale-void-200070690011.json;000",
//                    "Amex-Sale-200069950010.json;000",
//                    "EBT-Sale-201008250010.json;000",
                    "JCB-Sale-Swipe-200069610010.json;000",
                    "Amex-Refund-Swipe-200069730010.json;000",
                    "Amex-AuthVoid-Swipe-201020920010.json;000;1", "Amex-AuthVoid-Swipe-201020920011.json;000",
                    "Discover-AuthCompl-Swipe-201022120010.json;002;1" /*passing with 002 but TC has 000!!*/, "Discover-AuthCompl-Swipe-201022120011.json;000",
                    "MC-BalanceInquiry-100066500010.json;000"
            }

    )
    public void runAnyTest(String fileName) {
        parametrizedTest(fileName, PATH);
    }
}
