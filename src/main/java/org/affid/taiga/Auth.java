package org.affid.taiga;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Auth {

    public static JSONObject normalLogin(String username, String password, String serverURL) throws IOException {
        URL url = new URL(serverURL + "/api/v1/auth");
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        JSONObject request = new JSONObject();

        request.put("username", username);
        request.put("type", "normal");
        request.put("password", password);

        Utils.sendRequest(con, request);

        return Utils.getResponse(con);

    }

    public static JSONObject publicRegistry(String username, String password, String email, String fullName, String serverURL) throws IOException {
        URL url = new URL(serverURL + "/api/v1/auth/register");
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        JSONObject request = new JSONObject();

        request.put("username", username);
        request.put("type", "public");
        request.put("password", password);
        request.put("email", email);
        request.put("full_name", fullName);
        request.put("accepted_terms", true);

        Utils.sendRequest(con, request);

        return Utils.getResponse(con);
    }

    public JSONObject githubLogin(String serverURL, String githubCode) throws IOException {
        URL url = new URL(serverURL + "/api/v1/auth");
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        JSONObject request = new JSONObject();

        request.put("code", githubCode);
        request.put("type", "github");

        Utils.sendRequest(con, request);

        return Utils.getResponse(con);
    }

    public JSONObject privateRegistry(String username, String password, String email, String fullName, String serverURL, String token, Boolean existing) throws IOException {
        URL url = new URL(serverURL + "/api/v1/auth/register");
        HttpURLConnection con = Utils.getConnection(url, serverURL.contains("https"));

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        JSONObject request = new JSONObject();

        request.put("username", username);
        request.put("type", "private");
        request.put("password", password);
        request.put("existing", existing.toString());
        if (!existing) {
            request.put("email", email);
            request.put("full_name", fullName);
        }
        request.put("token", token);
        request.put("accepted_terms", true);

        Utils.sendRequest(con, request);

        return Utils.getResponse(con);
    }

}
