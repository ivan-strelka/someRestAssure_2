package headers;

import java.util.HashMap;
import java.util.Map;

public class HeadersTest {

    public static Map getDefoultHeaders() {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("Accept-Language", "en-US");
        requestHeaders.put("User-Agent", "Mozilla/5.0");
        requestHeaders.put("Content-Type", "application/json");
        return requestHeaders;
    }
}
