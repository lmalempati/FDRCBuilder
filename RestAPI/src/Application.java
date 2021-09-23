import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import com.sun.net.httpserver.HttpServer;
import fdrc.client.Client;


import static java.util.stream.Collectors.*;
//import static sun.net.www.ParseUtil.decode;

public class Application {
    public static void main(String[] args) throws IOException {
        int serverPort = 8000;
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(serverPort), 0);
        httpServer.createContext("/api/fdrc", (exchange -> {
            String respText = "Hello!";

            byte[] reqBody  =  exchange.getRequestBody().readAllBytes();
            String rawReq = new String(reqBody);
            respText = CallFDRC(rawReq);

//            Map<String, List<String>> params = splitQuery(exchange.getRequestURI().getRawQuery());
//            String noNameText = "Anonymous";
//            String name = params.getOrDefault("name", List.of(noNameText)).stream().findFirst().orElse(noNameText);
//            respText = String.format("Hello %s!", name);
//
//            respText = rawReq;


            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(respText.getBytes());
            outputStream.flush();
            exchange.close();
            }));
        httpServer.setExecutor(null); // creates a default executor
        httpServer.start();
    }

    static Map<String, List<String>> splitQuery(String query){
        if (query == null || "".equals(query)){
            return Collections.emptyMap();
        }

        return Pattern.compile("&").splitAsStream(query)
                .map(s -> Arrays.copyOf(s.split("="), 2))
                .collect(groupingBy(s -> s[0], mapping(s -> s[1], toList())));
    }

    static String CallFDRC(String json){
        Client client = new Client();
        return client.ProcessRequest(json);
        }

//    private static String decode(final String s) {
//        try {
//            if (s == null ? null : URLDecoder.decode(s, Charset.defaultCharset());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
}
