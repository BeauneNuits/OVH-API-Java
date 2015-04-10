package fr.rabian.ovhApi.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * This class handles HTTP requests.
 *
 * @author Adrien Rabian
 * @version 0.1
 */

public abstract class HttpRequests {

    /**
     * GET request.
     *
     * @param url URL
     * @param out StringBuffer to store response in
     * @return Response code
     * @throws Exception Exception Occuring during the request
     */
    public static int sendGet(String url, StringBuffer out) throws Exception {
        return sendGet(url, out, null);
    }

    /**
     * GET request.
     *
     * @param url URL
     * @param out StringBuffer to store response in
     * @param headers HTTP headers
     * @return Response code
     * @throws Exception Occuring during the request
     */
    public static int sendGet(String url, StringBuffer out, List<Header> headers) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        if (headers != null) {
            for (Header header : headers) {
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

    /**
     * POST request.
     * @param url URL
     * @param out StringBuffer to store response in
     * @param body Request body
     * @return Response code
     * @throws Exception Occuring during the request
     */
    public static int sendPost(String url, StringBuffer out, String body) throws Exception {
        return sendPost(url, out, body, null);
    }

    /**
     * POST request.
     *
     * @param url URL
     * @param out StringBuffer to store response in
     * @param body Request body
     * @param headers HTTP headers
     * @return Response code
     * @throws Exception Occuring during the request
     */
    public static int sendPost(String url, StringBuffer out, String body, List<Header> headers) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        if (headers != null) {
            for (Header header : headers) {
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
