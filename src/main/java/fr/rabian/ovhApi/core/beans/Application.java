package fr.rabian.ovhApi.core.beans;

/**
 * Represents an OVH application.
 *
 * @author Adrien Rabian
 * @version 0.1
 */
public class Application {

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
     * @param pubKey Application key (AK)
     * @param secKey Application secret (AS)
     * @param endpoint Application's endpoint
     */
    public Application(String pubKey, String secKey, Endpoint endpoint) {
        this.pubKey = pubKey;
        this.secKey = secKey;
        this.ep = endpoint;
        this.am = new AppManager(this);
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
    public Endpoint getEndpoint() {
        return ep;
    }

    /**
     * Returns the app's AppManager.
     *
     * @return Manager for this application
     */
    public AppManager getAppManager() {
        return am;
    }
}
