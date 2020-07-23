/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decagon.stageone.service.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Olugbenga.Falodun
 */
public class HttpUtil {

    private static final Logger L = Logger.getLogger(HttpUtil.class);
    private final static String USER_AGENT = "Mozilla/5.0";
    public static JsonObject connectionPostParameters = null;

    public static String[] sendGet(JsonObject data) throws Exception {
        String url = data.get("url").getAsString();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        con = setConnectionProperties(con, data);

        int responseCode = con.getResponseCode();
        L.info("Sending 'GET' request to URL : " + url);
        L.info("Response Code : " + responseCode);

        System.out.println("Sending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        InputStream stream = responseCode == 200 ? con.getInputStream() : con.getErrorStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String[] resp = {responseCode + "", response.toString()};

        L.info(response.toString());
        System.out.println("response from service " + resp[0] + "  " + resp[1]);
        return resp;
    }

    private static HttpURLConnection setConnectionProperties(HttpURLConnection con, JsonObject data) {

        if (data != null) {
            for (Map.Entry<String, JsonElement> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().getAsString();
                if (!key.equalsIgnoreCase("url")) {
                    con.setRequestProperty(key, value);
                }
            }
        }
        return con;
    }
}
