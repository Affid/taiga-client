package org.affid.taiga;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApplicationTokens {
    public static JSONObject list(String serverUrl, String authToken) throws IOException {
        URL url = new URL(serverUrl + "api/v1/application-tokens");
        HttpURLConnection con = Utils.getConnection(url,serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject get(String id,String serverUrl, String authToken) throws IOException {
        URL url = new URL(serverUrl + "api/v1/application-tokens/" + id);
        HttpURLConnection con = Utils.getConnection(url,serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static boolean delete(String id, String serverUrl, String authToken) throws IOException {
        URL url = new URL(serverUrl + "api/v1/application-tokens/" + id);
        HttpURLConnection con = Utils.getConnection(url,serverUrl.contains("https"));

        Utils.configure(con,authToken,"DELETE");

        return con.getResponseCode()== 204;
    }

    public static JSONObject authorize(String application, String authToken, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "api/v1/application-tokens/authorize");
        HttpURLConnection con = Utils.getConnection(url,serverUrl.contains("https"));

        Utils.configure(con,authToken,"POST");

        JSONObject request = new JSONObject();

        request.put("application",application);
        request.put("state", Utils.getNextRandomString("QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm123456789",15));

        Utils.sendRequest(con, request);

        return Utils.getResponse(con);
    }

    public static String validate(String application, String authCode, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "api/v1/application-tokens/validate");
        HttpURLConnection con = Utils.getConnection(url,serverUrl.contains("https"));

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");

        JSONObject request = new JSONObject();

        request.put("application",application);
        request.put("auth_code", authCode);
        request.put("state", Utils.getNextRandomString("QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm123456789",15));

        Utils.sendRequest(con, request);

        return Utils.getResponse(con).get("token").toString();
    }


}
