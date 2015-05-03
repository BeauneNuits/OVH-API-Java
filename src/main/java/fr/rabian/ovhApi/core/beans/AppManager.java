package fr.rabian.ovhApi.core.beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.rabian.ovhApi.core.http.Header;
import fr.rabian.ovhApi.core.http.HttpRequests;
import fr.rabian.ovhApi.core.utils.HashFunctions;
import fr.rabian.ovhApi.core.utils.NOKResponseException;
import fr.rabian.ovhApi.core.utils.Timestamps;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class designed to handle the basic operations of an application.
 *
 * @author Adrien Rabian
 * @version 0.1
 */
public class AppManager {

    /**
     * Application to manage
     */
    private Application app;

    /**
     * Timestamps instance to use
     */
    private Timestamps ts;

    /**
     * Endpoint to use
     */
    private Endpoint ep;

    /**
     * Creates an instance of AppManager
     *
     * @param app Application to manage
     */
    protected AppManager(Application app) {
        this.app = app;
        this.ep = app.getEndpoint();
        ts = new Timestamps(ep);
    }

    /**
     * Gets a new customer token
     *
     * @param scope Authorization scope
     * @param redirectURL URL to redirect to after authentication
     * @return Feteched Consumer
     * @throws NOKResponseException If the response differs from 200 - OK
     */
    public Consumer getConsumer(List<ScopeElement> scope, String redirectURL) throws NOKResponseException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Header> headers = new ArrayList<>();
        headers.add(new Header("Content-type", "application/json"));
        headers.add(new Header("X-Ovh-Application", app.getPubKey()));
        StringBuffer out = new StringBuffer();

        RequestCredentials rc = new RequestCredentials(scope, redirectURL);
        String req = gson.toJson(rc);
        int result = HttpRequests.sendPost(ep.getURL() + "/auth/credential", out, req, headers);
        Consumer c;
        if (result == 200) {
            c = gson.fromJson(out.toString(), Consumer.class);
            c.setScope(scope);
        } else {
            throw new NOKResponseException(result);
        }
        return c;
    }

    /**
     * Carries out a GET request.
     *
     * @param path Path to the function
     * @param c Consumer concerned
     * @return Response body
     * @throws NOKResponseException If the response differs from 200 - OK
     */
    public String sendGetReq(String path, Consumer c) throws NOKResponseException {
        return sendReq(path, "GET", c, "");
    }

    /**
     * Carries out a DELETE request.
     *
     * @param path Path to the function
     * @param c Consumer concerned
     * @return Response body
     * @throws NOKResponseException If the response differs from 200 - OK
     */
    public String sendDeleteReq(String path, Consumer c) throws NOKResponseException {
        return sendReq(path, "DELETE", c, "");
    }

    /**
     * Carries out a POST request.
     *
     * @param path Path to the function
     * @param c Consumer concerned
     * @param body Request body
     * @return Response body
     * @throws NOKResponseException If the response differs from 200 - OK
     */
    public String sendPostReq(String path, Consumer c, String body) throws NOKResponseException {
        return sendReq(path, "POST", c, body);
    }

    /**
     * Carries out a PUT request.
     *
     * @param path Path to the function
     * @param c Consumer concerned
     * @param body Request body
     * @return Response body
     * @throws NOKResponseException If the response differs from 200 - OK
     */
    public String sendPutReq(String path, Consumer c, String body) throws NOKResponseException {
        return sendReq(path, "PUT", c, body);
    }

    /**
     * Carries out a request.
     *
     * @param path Path to the function
     * @param method HTTP method
     * @param c Consumer concerned
     * @param body Request body
     * @return Response body
     * @throws NOKResponseException If the response differs from 200 - OK
     */
    private String sendReq(String path, String method, Consumer c, String body) throws NOKResponseException{
        List<Header> headers = new ArrayList<>();
        path = ep.getURL() + path;
        long time = ts.getTime();
        StringBuilder forSig = new StringBuilder();
        forSig.append(app.getSecKey());
        forSig.append("+");
        forSig.append(c.getConsumerKey());
        forSig.append("+");
        forSig.append(method);
        forSig.append("+");
        forSig.append(path);
        forSig.append("+");
        forSig.append(body);
        forSig.append("+");
        forSig.append(Long.toString(time));

        String sig = "$1$";
        try {
            sig = sig + HashFunctions.hashMD("SHA-1", forSig.toString());
        } catch (NoSuchAlgorithmException e) {
            //Silenced because always available.
        }

        headers.add(new Header("X-Ovh-Application", app.getPubKey()));
        headers.add(new Header("X-Ovh-Consumer", c.getConsumerKey()));
        headers.add(new Header("Accept", "application/json"));
        headers.add(new Header("Content-Type", "application/json;charset=utf-8"));
        headers.add(new Header("X-Ovh-Timestamp", Long.toString(time)));
        headers.add(new Header("X-Ovh-Signature", sig));

        int result;
        StringBuffer out = new StringBuffer();

        switch(method) {
            case "GET":
                result = HttpRequests.sendGet(path, out, headers);
                break;
            case "POST":
                result = HttpRequests.sendPost(path, out, body, headers);
                break;
            case "PUT":
                result = HttpRequests.sendPut(path, out, body, headers);
                break;
            case "DELETE":
                result = HttpRequests.sendDelete(path, out, headers);
                break;
            default:
                throw new IllegalArgumentException("Incorrect HTTP method.");
        }

        if (result == 200) {
            return out.toString();
        } else {
            throw new NOKResponseException(result);
        }
    }
}
