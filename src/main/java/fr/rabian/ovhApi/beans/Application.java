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
     * Endpoint in which the application is declared
     */
    private final Endpoint ep;
    /**
     * Manager for this application
     */
    private final AppManager am;

    /**
     * Creates an application.
     *
     * @param name Application's name
     * @param pubKey Application key (AK)
     * @param secKey Application secret (AS)
     * @param endpoint Application's endpoint
     */
    public Application(String name, String pubKey, String secKey, Endpoint endpoint) {
        this.name = name;
        this.pubKey = pubKey;
        this.secKey = secKey;
        this.ep = endpoint;
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

    /**
     * Returns the app's endpoint.
     *
     * @return Endpoint in which the application is declared
     */
    public Endpoint getEp() {
        return ep;
    }

    /**
     * Returns the app's AppManager.
     *
     * @return Manager for this application
     */
    public AppManager getAm() {
        return am;
    }
}
