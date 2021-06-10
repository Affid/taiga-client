package org.affid.taiga;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Users {

    public static JSONObject get(String serverUrl, String authToken, long id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/users/" + id);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject list(String serverUrl, String authToken, long projectId) throws IOException {
        URL url = new URL(serverUrl + "api/v1/users?project=" + projectId);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject list(String serverUrl, String authToken) throws IOException {
        URL url = new URL(serverUrl + "api/v1/users");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static boolean cancel(String token, String cancelToken, String serverURL) throws IOException {
        URL url = new URL(serverURL + "/api/v1/users/cancel");
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);

        JSONObject request = new JSONObject();

        request.put("cancel_token", cancelToken);

        Utils.sendRequest(con, request);

        return con.getResponseCode() == 204;
    }

    public static boolean delete(String token, String serverURL, String id) throws IOException {
        URL url = new URL(serverURL + "/api/v1/users/" + id);
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);

        return con.getResponseCode() == 204;
    }

    public static JSONObject me(String token, String serverURL) throws IOException {
        URL url = new URL(serverURL + "/api/v1/users/me");
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);

        return Utils.getResponse(con);
    }
}
