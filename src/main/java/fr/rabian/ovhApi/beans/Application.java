package fr.rabian.ovhApi.beans;

/**
 * Represents an OVH application.
 *
 * @author Adrien Rabian
 * @version 0.1
 */
public class Application {
    /**
     * Application's name
     */
    private final String name;
    /**
     * Application key (AK)
     */
    private final String pubKey;
    /**
     * Application secret (AS)
     */
    private final String secKey;

    /**
     * Creates an application.
     *
     * @param name Application's name
     * @param pubKey Application key (AK)
     * @param secKey Application secret (AS)
     */
    public Application(String name, String pubKey, String secKey) {
        this.name = name;
        this.pubKey = pubKey;
        this.secKey = secKey;
    }

    /**
     * Returns the app's name.
     *
     * @return Application's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the app's public key.
     *
     * @return Application key (AK)
     */
    public String getPubKey() {
        return pubKey;
    }

    /**
     * Returns the app's secret key.
     *
     * @return Application secret (AS)
     */
    public String getSecKey() {
        return secKey;
    }
}
