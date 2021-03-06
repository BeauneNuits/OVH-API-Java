package fr.rabian.ovhApi.core.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
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
     */
    public static int sendGet(String url, StringBuffer out) {
        return sendWithoutBody(url, "GET", out, null);
    }

    /**
     * GET request.
     *
     * @param url     URL
     * @param out     StringBuffer to store response in
     * @param headers HTTP headers
     * @return Response code
     */
    public static int sendGet(String url, StringBuffer out, List<Header> headers) {
        return sendWithoutBody(url, "GET", out, headers);
    }

    /**
     * DELETE request.
     *
     * @param url     URL
     * @param out     StringBuffer to store response in
     * @param headers HTTP headers
     * @return Response code
     */
    public static int sendDelete(String url, StringBuffer out, List<Header> headers) {
        return sendWithoutBody(url, "DELETE", out, headers);
    }

    /**
     * Send a request without body (GET, DELETE...)
     *
     * @param url     URL
     * @param method  HTTP method
     * @param out     StringBuffer to store response in
     * @param headers HTTP headers
     * @return Response code
     */
    private static int sendWithoutBody(String url, String method, StringBuffer out, List<Header> headers) {
        int responseCode = -1;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod(method);
            if (headers != null) {
                for (Header header : headers) {
                    con.setRequestProperty(header.getKey(), header.getValue());
                }
            }

            responseCode = con.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    out.append(inputLine);
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    /**
     * PUT request.
     *
     * @param url     URL
     * @param out     StringBuffer to store response in
     * @param body    Request body
     * @param headers HTTP headers
     * @return Response code
     */
    public static int sendPut(String url, StringBuffer out, String body, List<Header> headers) {
        return sendWithBody(url, "PUT", out, body, headers);
    }

    /**
     * POST request.
     *
     * @param url     URL
     * @param out     StringBuffer to store response in
     * @param body    Request body
     * @param headers HTTP headers
     * @return Response code
     */
    public static int sendPost(String url, StringBuffer out, String body, List<Header> headers) {
        return sendWithBody(url, "POST", out, body, headers);
    }

    /**
     * Send a request with a body (POST, PUT...).
     *
     * @param url     URL
     * @param method  HTTP method
     * @param out     StringBuffer to store response in
     * @param body    Request body
     * @param headers HTTP headers
     * @return Response code
     */
    private static int sendWithBody(String url, String method, StringBuffer out, String body, List<Header> headers)  {
        int responseCode = -1;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod(method);
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

            responseCode = con.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    out.append(inputLine);
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

}
