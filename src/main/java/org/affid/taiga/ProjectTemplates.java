package org.affid.taiga;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProjectTemplates {
    public static JSONArray list(String authToken, String serverUrl) throws IOException {
        return Utils.getList(serverUrl, authToken, "api/v1/project-templates");
    }

    public static JSONObject create(String adminAuthToken, String serverUrl, JSONObject requestBody) throws IOException {
        URL url = new URL(serverUrl + "api/v1/project-templates");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, adminAuthToken, "POST");

        Utils.sendRequest(con, requestBody);

        return Utils.getResponse(con);
    }

    public static JSONObject create(String adminAuthToken, String serverUrl, String defaultOwnerRole, String description, String name) throws IOException {
        URL url = new URL(serverUrl + "api/v1/project-templates");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, adminAuthToken, "POST");

        JSONObject requestBody = new JSONObject();

        requestBody.put("default_owner_role", defaultOwnerRole);
        requestBody.put("description", description);
        requestBody.put("name", name);

        Utils.sendRequest(con, requestBody);

        return Utils.getResponse(con);
    }

    public static JSONObject get(String authToken, String serverUrl, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/project-templates/" + id);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject edit(String adminAuthToken, String serverUrl, String id, JSONObject newData) throws IOException {
        URL url = new URL(serverUrl + "api/v1/project-templates/" + id);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,adminAuthToken,"POST");
        con.setRequestProperty("X-HTTP-Method-Override", "PATCH");

        Utils.sendRequest(con,newData);

        return Utils.getResponse(con);
    }

    public static boolean delete(String adminAuthToken, String serverUrl, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/project-templates/" + id);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,adminAuthToken,"DELETE");

        return con.getResponseCode() == 204;
    }

}
