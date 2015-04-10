package fr.rabian.ovhApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.rabian.ovhApi.http.HttpRequests;
import fr.rabian.ovhApi.requestBeans.RequestCredentials;
import fr.rabian.ovhApi.requestBeans.RequestProperty;
import fr.rabian.ovhApi.utils.HashFunctions;
import fr.rabian.ovhApi.utils.Timestamps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrien on 31/03/2015.
 */
public class Application {

    private final String name;
    private final String pubKey;
    private final String secKey;

    public Application(String name, String pubKey, String secKey) {
        this.name = name;
        this.pubKey = pubKey;
        this.secKey = secKey;
    }

    public Consumer getConsumer(List<ScopeElement> scope, String redirectURL) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<RequestProperty> headers = new ArrayList<>();
        headers.add(new RequestProperty("Content-type", "application/json"));
        headers.add(new RequestProperty("X-Ovh-Application", this.pubKey));
        StringBuffer out = new StringBuffer();

        RequestCredentials rc = new RequestCredentials(scope, redirectURL);
        String req = gson.toJson(rc);
        int result = 0;
        try {
            result = HttpRequests.sendPost("https://eu.api.ovh.com/1.0/auth/credential", out, req, headers);
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

    public int sendReq(String URL, String method, Consumer c, String body, StringBuffer out, List<RequestProperty> headers) throws Exception{
        if (headers == null) {
            headers = new ArrayList<>();
        }
        URL = "https://eu.api.ovh.com/1.0/" + URL;
        System.out.println(URL);
        long ts = Timestamps.getTime();
        System.out.println(ts);
        StringBuilder forSig = new StringBuilder();
        forSig.append(this.secKey);
        forSig.append("+");
        forSig.append(c.getConsumerKey());
        forSig.append("+");
        forSig.append(method);
        forSig.append("+");
        forSig.append(URL);
        forSig.append("+");
        forSig.append(body);
        forSig.append("+");
        forSig.append(Long.toString(ts));
        System.out.println(forSig);
        String sig = "$1$" + HashFunctions.hashMD("SHA-1", forSig.toString());
        System.out.println(sig);

        headers.add(new RequestProperty("X-Ovh-Application", this.pubKey));
        headers.add(new RequestProperty("X-Ovh-Consumer", c.getConsumerKey()));
        headers.add(new RequestProperty("Accept", "application/json"));
        headers.add(new RequestProperty("X-Ovh-Timestamp", Long.toString(ts)));
        headers.add(new RequestProperty("X-Ovh-Signature", sig));

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
