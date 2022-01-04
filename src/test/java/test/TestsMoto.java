package test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class TestsMoto extends TestGeneric {
    public static final String PATH = "src/test/resources/Moto/";

    @ParameterizedTest
    @ValueSource(strings =
            {
                    "Amex-AuthCompl-payload-10.json;002;1"
//                    , "Amex-AuthCompl-payload-11.json;000", // Auth & Compl Amex
//                    "JCB-AuthCompl-Keyed-100070950010.json;000;1", "JCB-AuthCompl-Keyed-100070950011.json;000",
//                    "DS-Auth-Keyed-200070910010.json;000",
//                    "Credit-MC-Refund-Keyedin.json;000",
//                    "Credit-MC-RefundVoid-Keyed-200081230010.json;000;1", "Credit-MC-RefundVoid-Keyed-200081230011.json;000", // Refund Void
//                    "VisaSaleKeyed-200003520020.json;002",
//                    "Credit-MC-AuthCompl-200021950010.json;000;1", "Credit-MC-AuthCompl-200021950011.json;000",
//                    "Discover-AuthCompl-200070910010.json;000;1;", "Discover-AuthCompl-200070910011.json;000", // Auth & Compl Discover
//                    "Diners-AuthCompl-200070930010.json;000;1", "Diners-AuthCompl-200070930011.json;000",
//                    "Amex-AuthCompl-201013680010.json;002;1", "Amex-AuthCompl-201013680011.json;000",
//                    "credit-AuthVoid-200070840010.json;000;1", "credit-AuthVoid-200070840011.json;000", // Auth & void..
//                    "AddtlAmount-Credit-MC-Sale-200004170020.json;500", // Sale
//                    "Credit-TA-Auth-200021900010.json;000;1", "Credit-TA-Auth-200021900011.json;000",
//                    "TATokenRequest-200004710010.json;000",
//                    "Credit-TA-Refund-Disc-200292180010.json;000",
//                    "Credit-TA-Sale-MC-200299950010.json;002"
            })
    public void runTestsMoto(String fileName) {
        parameterizedTest (fileName, PATH);
    }
}
