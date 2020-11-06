package org.affid.taiga;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class Auth {

    public static String normalLogin(String username, String password, String serverURL) throws IOException {
        URL url = new URL(serverURL + "/api/v1/auth");
        HttpURLConnection con = Utils.getConnection(url,serverURL.contains("https"));

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");

        JSONObject request = new JSONObject();

        request.put("username",username);
        request.put("type","normal");
        request.put("password",password);

        return getAuthToken(con,request);

    }

    public static String publicRegister(String username, String password, String email, String fullName, String serverURL) throws IOException {
        URL url = new URL(serverURL + "/api/v1/auth/register");
        HttpURLConnection con = Utils.getConnection(url,serverURL.contains("https"));

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");

        JSONObject request = new JSONObject();

        request.put("username",username);
        request.put("type","public");
        request.put("password",password);
        request.put("email",email);
        request.put("full_name",fullName);
        request.put("accepted_terms",true);

        return getAuthToken(con, request);
    }

    private static String getAuthToken(URLConnection con, JSONObject request) throws IOException {
        Utils.sendRequest(con, request);

        JSONObject resp = Utils.getResponse(con);

        return resp.get("auth_token").toString();
    }

}
