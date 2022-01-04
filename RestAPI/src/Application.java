import com.sun.net.httpserver.HttpServer;
import fdrc.service.Client;
import org.apache.commons.httpclient.HttpStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public class Application {
    public static final String addr = "/api/fdrc";
    public static final int serverPort = 8000;

    public static void main(String[] args) throws IOException {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(serverPort), 0);
            httpServer.createContext(addr, (exchange -> {
                String respText = "Hello!";

                byte[] reqBody = exchange.getRequestBody().readAllBytes();
                String rawReq = new String(reqBody);
                respText = CallFDRC(rawReq);
                exchange.sendResponseHeaders(HttpStatus.SC_OK, respText.getBytes().length);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(respText.getBytes());
                outputStream.flush();
                exchange.close();
            }));
            httpServer.setExecutor(null); // creates a default executor
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Map<String, List<String>> splitQuery(String query) {
        if (query == null || "".equals(query)) {
            return Collections.emptyMap();
        }

        return Pattern.compile("&").splitAsStream(query)
                .map(s -> Arrays.copyOf(s.split("="), 2))
                .collect(groupingBy(s -> s[0], mapping(s -> s[1], toList())));
    }

    static String CallFDRC(String json) {
        Client client = new Client();
        return client.Call(json);
    }


}
