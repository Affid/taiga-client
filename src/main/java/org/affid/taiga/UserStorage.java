package org.affid.taiga;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserStorage {
    public static JSONObject list(String authToken, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "api/v1/user-storage");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "GET");

        return Utils.getResponse(con);
    }

    public static JSONObject create(String authToken, String serverUrl, String key, String value) throws IOException {
        URL url = new URL(serverUrl + "api/v1/user-storage");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");

        JSONObject request = new JSONObject();

        request.put("key", key);
        request.put("value", value);

        Utils.sendRequest(con,request);

        return Utils.getResponse(con);
    }

    public static JSONObject edit(String authToken, String serverUrl, String key, String value) throws IOException {
        URL url = new URL(serverUrl + "api/v1/user-storage/" + key);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "PATCH");

        JSONObject request = new JSONObject();

        request.put("value", value);

        Utils.sendRequest(con,request);

        return Utils.getResponse(con);
    }

    public static boolean delete(String authToken, String serverUrl, String key) throws IOException {
        URL url = new URL(serverUrl + "api/v1/user-storage/" + key);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "DELETE");

        return con.getResponseCode() == 204;
    }
}
