package fr.rabian.ovhApi.http;

/**
 * Created by adrien on 27/03/15.
 */
import fr.rabian.ovhApi.requestBeans.RequestProperty;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public abstract class HttpRequests {

    public static int sendGet(String url, StringBuffer out) throws Exception {
        return sendGet(url, out, null);
    }

    public static int sendGet(String url, StringBuffer out, List<RequestProperty> headers) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        if (headers != null) {
            for (RequestProperty header : headers) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            out.append(inputLine);
        }
        in.close();

        return responseCode;
    }

    public static int sendPost(String url, StringBuffer out, String body) throws Exception {
        return sendPost(url, out, body, null);
    }

    public static int sendPost(String url, StringBuffer out, String body, List<RequestProperty> headers) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        if (headers != null) {
            for (RequestProperty header : headers) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(body);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            out.append(inputLine);
        }
        in.close();

        return responseCode;
    }

}
