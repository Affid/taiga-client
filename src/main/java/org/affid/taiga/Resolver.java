package org.affid.taiga;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Resolver {

    public static String projects(String authToken, String serverUrl, String project) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/resolver?project=" + project);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con).get("project").toString();
    }

    public static JSONObject userStories(String authToken, String serverUrl, String project, String us) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/resolver?project=" + project + "&us=" + us);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject issues(String project, String issue, String authToken, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/resolver?project=" + project + "&issue=" + issue);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject tasks(String project, String task, String authToken, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/resolver?project=" + project + "&task=" + task);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject milestones(String project, String milestone, String authToken, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/resolver?project=" + project + "&milestone=" + milestone);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject wikiPages(String project, String wikiPage, String authToken, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/resolver?project=" + project + "&wikipage=" + wikiPage);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject multipleResolution(String project, String task, String us, String wikiPage, String authToken, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/resolver?project=" + project + "&task=" + task + "&us=" + us + "&wikipage=" + wikiPage);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }

    public static JSONObject byRefValue(String project, String ref, String authToken, String serverUrl) throws IOException {
        URL url = new URL(serverUrl + "/api/v1/resolver?project=" + project + "&ref=" + ref);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con,authToken,"GET");

        return Utils.getResponse(con);
    }
}
