package org.affid.taiga;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class Utils {
    static HttpURLConnection getConnection(URL url, boolean isHttps) throws IOException {
        HttpURLConnection con;
        if (isHttps)
            con = (HttpsURLConnection) url.openConnection();
        else
            con = (HttpURLConnection) url.openConnection();
        return con;
    }

    static void sendRequest(URLConnection con, JSONObject request) throws IOException {
        con.setDoOutput(true);
        OutputStream out = con.getOutputStream();

        PrintWriter writer = new PrintWriter(out, true);
        writer.print(request);
        writer.flush();
        writer.close();
    }

    static JSONObject getResponse(URLConnection con) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        JSONObject resp = new JSONObject(reader.readLine());
        reader.close();

        return resp;
    }

    static JSONArray getList(String serverUrl, String authToken, String service) throws IOException {
        URL url = new URL(serverUrl + service);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line = reader.readLine();

        return new JSONArray(line);
    }

    public static String getNextRandomString(String strAllowedCharacters, int length) {
        Random random = new Random();
        StringBuilder sbRandomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {

            int randomInt = random.nextInt(strAllowedCharacters.length());

            //get char from randomInt index from string and append in StringBuilder
            sbRandomString.append(strAllowedCharacters.charAt(randomInt));
        }

        return sbRandomString.toString();

    }

    static void configure(HttpURLConnection con, String authToken, String method) throws ProtocolException {
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + authToken);
    }
}
