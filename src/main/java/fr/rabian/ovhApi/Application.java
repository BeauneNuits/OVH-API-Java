package fr.rabian.ovhApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.rabian.ovhApi.http.HttpRequests;
import fr.rabian.ovhApi.requestBeans.RequestCredentials;
import fr.rabian.ovhApi.requestBeans.RequestProperty;
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

}
