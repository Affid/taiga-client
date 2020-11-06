package org.affid.taiga;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Users {

    public static boolean cancelUser(String token, String serverURL) throws IOException {
        URL url = new URL(serverURL + "/api/v1/users/cancel");
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);

        JSONObject request = new JSONObject();

        request.put("cancel_token", token);

        Utils.sendRequest(con, request);

        System.out.println(con.getResponseMessage());

        return con.getResponseCode() == 204;
    }

    public static boolean deleteUser(String token, String serverURL, String id) throws IOException {
        URL url = new URL(serverURL + "/api/v1/users/" + id);
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);

        return con.getResponseCode() == 204;
    }

    public static String me(String token, String serverURL) throws IOException {
        URL url = new URL(serverURL + "/api/v1/users/me");
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);

        JSONObject response = Utils.getResponse(con);
        return response.get("id").toString();
    }
}
