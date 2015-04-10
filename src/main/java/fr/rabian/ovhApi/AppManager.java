package fr.rabian.ovhApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.rabian.ovhApi.beans.*;
import fr.rabian.ovhApi.http.Header;
import fr.rabian.ovhApi.http.HttpRequests;
import fr.rabian.ovhApi.utils.HashFunctions;
import fr.rabian.ovhApi.utils.Timestamps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrien on 10/04/2015.
 */
public class AppManager {

    private Application app;
    private Timestamps ts;
    private Endpoint ep;

    public AppManager(Application app, Endpoint ep) {
        this.app = app;
        this.ep = ep;
        ts = new Timestamps(ep);
    }

    public Consumer getConsumer(List<ScopeElement> scope, String redirectURL) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Header> headers = new ArrayList<>();
        headers.add(new Header("Content-type", "application/json"));
        headers.add(new Header("X-Ovh-Application", app.getPubKey()));
        StringBuffer out = new StringBuffer();

        RequestCredentials rc = new RequestCredentials(scope, redirectURL);
        String req = gson.toJson(rc);
        int result = 0;
        try {
            result = HttpRequests.sendPost(ep.getURL() + "/auth/credential", out, req, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Consumer c = null;
        if (result == 200) {
            c = gson.fromJson(out.toString(), Consumer.class);
            c.setScope(scope);
        }
        return c;
    }

    public int sendReq(String URL, String method, Consumer c, String body, StringBuffer out, List<Header> headers) throws Exception{
        if (headers == null) {
            headers = new ArrayList<>();
        }
        URL = ep.getURL() + URL;
        long time = ts.getTime();
        StringBuilder forSig = new StringBuilder();
        forSig.append(app.getPubKey());
        forSig.append("+");
        forSig.append(c.getConsumerKey());
        forSig.append("+");
        forSig.append(method);
        forSig.append("+");
        forSig.append(URL);
        forSig.append("+");
        forSig.append(body);
        forSig.append("+");
        forSig.append(Long.toString(time));
        String sig = "$1$" + HashFunctions.hashMD("SHA-1", forSig.toString());

        headers.add(new Header("X-Ovh-Application", app.getPubKey()));
        headers.add(new Header("X-Ovh-Consumer", c.getConsumerKey()));
        headers.add(new Header("Accept", "application/json"));
        headers.add(new Header("X-Ovh-Timestamp", Long.toString(time)));
        headers.add(new Header("X-Ovh-Signature", sig));

        switch(method) {
            case "GET":
                return HttpRequests.sendGet(URL, out, headers);
            case "POST":
                return HttpRequests.sendPost(URL, out, body, headers);
            default:
                throw new IllegalArgumentException("Incorrect HTTP method.");
        }
    }
}
