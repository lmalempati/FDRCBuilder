//package fdrc.http;
//
//import fdrc.common.Constants;
//import fdrc.types.EnumRequestType;
//import okhttp3.OkHttpClient;
//import retrofit2.Call;
//import retrofit2.Retrofit;
//import retrofit2.converter.scalars.ScalarsConverterFactory;
//
//import java.net.SocketTimeoutException;
//import java.net.UnknownHostException;
//
//public class RetrofitManager {
//    public static String submitHttpRequest(String xml, EnumRequestType requestType) throws Exception {
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        String url = Constants.STG_POST_URL;
//
//        if (requestType.val != "transaction")
//            url = Constants.STG_DATAWIRE_SRS;
//
//        Call<String> callSync;
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .client(httpClient.build())
//                .build();
//
//        ResponseService service = retrofit.create(ResponseService.class);
//
//        switch (requestType)
//        {
//            case trans:
//                callSync = service.submitRCTxn(xml);
//                break;
//            case discovery:
//                callSync = service.submitDiscovery();
//                break;
//            case register:
//                callSync = service.submitRegistration(xml);
//                break;
//            case activate:
//                callSync = service.submitActivation(xml);
//                break;
//            default:
//                callSync = service.submitRCTxn(xml);
//        }
//
//        try {
//            retrofit2.Response<String> response = callSync.execute();
//            return response.body();
//        } catch (SocketTimeoutException e) {
//            throw new Exception(Constants.TIME_OUT);
//        }
//        catch(UnknownHostException e){
//            throw new Exception("No Network Connection");
//        }
//        catch (Exception e){ throw new Exception(e.getMessage());}
//    }
//}
