package org.affid.taiga;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Applications {
    public static JSONObject get(String id, String serverUrl, String authToken) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/applications/" + id);
        HttpURLConnection con = Utils.getConnection(url,serverUrl.contains("https"));

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("Authorization","Bearer " + authToken);

        return Utils.getResponse(con);
    }

    public static JSONObject getToken(String id, String serverUrl, String authToken) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/applications/" + id + "/token");
        HttpURLConnection con = Utils.getConnection(url,serverUrl.contains("https"));

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("Authorization","Bearer " + authToken);

        return Utils.getResponse(con);
    }
}
