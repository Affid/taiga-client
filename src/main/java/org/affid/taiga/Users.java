package org.affid.taiga;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Users {

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
