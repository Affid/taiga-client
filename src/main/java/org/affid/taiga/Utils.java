package org.affid.taiga;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
    static HttpURLConnection getConnection(URL url, boolean isHttps) throws IOException {
        HttpURLConnection con;
        if(isHttps)
            con = (HttpsURLConnection) url.openConnection();
        else
            con = (HttpURLConnection) url.openConnection();
        return con;
    }

    static void sendRequest(URLConnection con, JSONObject request) throws IOException {
        con.setDoOutput(true);
        OutputStream out = con.getOutputStream();

        PrintWriter writer = new PrintWriter(out,true);
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
}
