package fdrc.common;

public class Constants {
    public static final String GMF = "<GMF>";
    public static final String GMF_NS = "<GMF xmlns=\"com/fiserv/Merchant/gmfV10.02\">";
    public static final String[] StatusCodesToReverse = {"Timeout"};
    public static final String[] ReturnCodesToReverse = {"203", "204", "205", "206", "405", "505", "008"};
    public static final String[] RetryCodes = { "Retry", "InternalError", "OtherError"};

    // Parameters for transaction request
    public static final String REQUEST_TPPID = "RSU005";
    public static final String REQUEST_TERMID = "00000003";
    public static final String REQUEST_MERCHID = "RCTST1000091557"; // "RCTST1000091556";
    //"RCTST1000092492"; moto old one
    public static final String REQUEST_GROUPID = "10001"; // "20001"
    public static final String REQUEST_DEBIT_TRACK2 = "4017779995555556=24041200000000001";
    public static final String REQUEST_DEBIT_PINDATA = "99A14CA1B65D821B";
    public static final String REQUEST_DEBIT_KEYSERIALNUMDATA = "F8765432100015200578";
    public static final String stgUrl = "https://stg.dw.us.fdcnet.biz/sd/srsxml.rc"; // dev
    public static final String prodUrl = ""; // prod
    public static final String STG_POST_URL = "https://stg.dw.us.fdcnet.biz/rc";

    public static final String HTTP_DID = "00035469814383142846"; /*Restaurant 10001 */
    public static final String APP = "RAPIDCONNECTSRS";
    public static final String SERVICE_ID = "160";
    public static final String REGISTRATION_TEMPLATE =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<Request Version=\"3\">\n" +
                    "    <ReqClientID>\n" +
                    "        <DID></DID>\n" +
                    "        <App>" + APP + "</App>\n" +
                    "        <Auth>{groupId}{merchantId}|{terminalId}</Auth>\n" +
                    "        <ClientRef>{clientRef}</ClientRef>\n" +
                    "    </ReqClientID>\n" +
                    "    <Registration>\n" +
                    "        <ServiceID>" + SERVICE_ID + "</ServiceID>\n" +
                    "    </Registration>\n" +
                    "</Request>\n";
    public static final String ACTIVATION_TEMPLATE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<Request Version=\"3\">\n" +
            "    <ReqClientID>\n" +
            "        <DID>{datawireId}</DID>\n" +
            "        <App>" + APP + "</App>\n" +
            "        <Auth>{groupId}{merchantId}|{terminalId}</Auth>\n" +
            "        <ClientRef>{clientRef}</ClientRef>\n" +
            "    </ReqClientID>\n" +
            "    <Activation>\n" +
            "        <ServiceID>" + SERVICE_ID + "</ServiceID>\n" +
            "    </Activation>\n" +
            "</Request>\n";
}