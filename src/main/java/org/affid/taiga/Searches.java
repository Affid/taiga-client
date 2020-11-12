package org.affid.taiga;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Searches {
    public static JSONObject search(String project, String text, String authToken, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/search?project=" + project + "&text=" + text);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }
}
