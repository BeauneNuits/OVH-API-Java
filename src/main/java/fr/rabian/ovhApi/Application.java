package fr.rabian.ovhApi;

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

    public String getName() {
        return name;
    }

    public String getPubKey() {
        return pubKey;
    }

    public String getSecKey() {
        return secKey;
    }
}
