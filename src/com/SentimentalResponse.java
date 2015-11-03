/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import twitter4j.JSONException;
import twitter4j.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *
 * @author sanji
 */
public class SentimentalResponse {

    private static String apiKey = "3e4688b51cf376c8869ab1a8829c407467ffa77c";
    private static String sentimentalAnalysistUrl = "http://access.alchemyapi.com/calls/text/TextGetTextSentiment?";
    private static String outputMode = "json";
    private static String url;

    public static String readJsonFromUrl(String text) throws IOException, JSONException {
        // System.out.println(text);
        // text=text.replaceAll(" ","%20");
        text = text.replaceAll("\n", " ");
        String[] str;
        str = text.split("http");
        text = str[0];
        text = text.replaceAll(" ", "%20")
                .replaceAll(":", "%3A")
                .replaceAll("/", "%2F")
                .replaceAll(";", "%3B")
                .replaceAll("@", "%40")
                .replaceAll("<", "%3C")
                .replaceAll(">", "%3E")
                .replaceAll("=", "%3D")
                .replaceAll("&", "%26")
                .replaceAll("%", "%25")
                .replaceAll("$", "%24")
                .replaceAll("#", "%23")
                .replaceAll(",", "%2C");
        //.replaceAll("++","%2B").replaceAll("?","%3F");
        //System.out.println(text);
        url = sentimentalAnalysistUrl + "apikey=" + apiKey + "&text=" + text + "&outputMode=" + outputMode;
        System.out.println(url);
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            if (json == null) {
                return "No tweets detection";
            } else {
                if (!json.has("docSentiment")) {
                    return "language not detection";
                } else {
                    JSONObject json2 = (JSONObject) json.get("docSentiment");
                    return json2.get("type").toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "error occur, retrying";
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();

    }
}
