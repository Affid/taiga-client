package org.affid.taiga;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserStoryCustomAttribute {
    public static JSONArray list(String serverUrl, String authToken) throws IOException {
            return Utils.getList(serverUrl, authToken, "api/v1/userstory-custom-attributes");
    }

    public static JSONArray list(String serverUrl, String authToken, String projectId) throws IOException {
        return Utils.getList(serverUrl, authToken, "api/v1/userstory-custom-attributes?project=" + projectId);
    }

    public static class Values{
        public static JSONObject get(String serverUrl, String authToken, String userStoryId) throws IOException {
            URL url = new URL(serverUrl + "api/v1/userstories/custom-attributes-values/" + userStoryId);
            HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

            Utils.configure(con,authToken,"GET");

            return Utils.getResponse(con);
        }


    }
}
