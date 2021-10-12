package fdrc.base;

public class Constants {
    // Parameters for transaction request
    public static final String REQUEST_TPPID = "RSU005";
    public static final String REQUEST_TERMID = "00000003";
    //    public static final String REQUEST_MERCHID =  "RCTST1000091636"; // Moto
    public static final String REQUEST_MERCHID =  "RCTST1000091637"; // Restt
//    public static final String REQUEST_MERCHID = "RCTST1000091557"; // Restt
    public static final String REQUEST_GROUPID = "20001";
    public static final String REQUEST_DEBIT_TRACK2 = "4017779995555556=24041200000000001";
    public static final String REQUEST_DEBIT_PINDATA = "99A14CA1B65D821B";
    public static final String REQUEST_DEBIT_KEYSERIALNUMDATA = "F8765432100015200578";

    // Parameters for TCPIP protocol
    public static final String TCP_HOST = "XX.XXX.XX.X";
    public static final int TCP_PORT = 1234;

    public static String SRS_URL = "https://stg.dw.us.fdcnet.biz/rc";
    //    public static final String HTTP_DID = "00041372277848179310"; // Moto
    public static final String HTTP_DID = "00035488451724571345"; // Restaurant
//    public static final String HTTP_DID = "00035469814383142846"; // Restaurant

    // "00035469814383142846"; /*Restaurant 10001 */ "00041198330131720090"; /*Moto 10001 */ "00041836971547330349"; /*moto old one*/
}
