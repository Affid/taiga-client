package org.affid.taiga;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WikiPage {

    public static JSONObject create(String serverUrl, String authToken, JSONObject body) throws IOException {
        URL url = new URL(serverUrl + "api/v1/wiki");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        Utils.sendRequest(con, body);

        return Utils.getResponse(con);
    }

    public static JSONObject getBySlug(String serverUrl, String authToken, String slug, String project) throws IOException {
        URL url = new URL(serverUrl + "api/v1/wiki/by_slug?slug=" + slug + "&project="+project);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "GET");
        if(con.getResponseCode() == 404)
            return new JSONObject().put("response-code","404");
        return Utils.getResponse(con).put("response-code","200");
    }

    public static JSONObject edit(String serverUrl, String authToken, JSONObject body, String pageId) throws IOException {
        URL url = new URL(serverUrl + "api/v1/wiki/" + pageId);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        Utils.sendRequest(con, body);
        return Utils.getResponse(con);
    }

    public static JSONObject createOrEdit(String serverUrl, String authToken, String slug, String project, String content) throws IOException {
        JSONObject pageInfo = getBySlug(serverUrl, authToken, slug, project);
        if("404".equals(pageInfo.remove("response-code"))){
            return create(serverUrl, authToken, new JSONObject().put("slug", slug).put("project", project).put("content",content));
        }
        else{
            return edit(serverUrl, authToken,
                    new JSONObject().put("content", content).put("version",(pageInfo.getInt("version"))),
                    pageInfo.get("id").toString());
        }
    }
}
