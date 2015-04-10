package fr.rabian.ovhApi.beans;

/**
 * This class represents an endpoint for the OVH API.
 * The OVH API system is used (of course) in OVH Europe, but also in OVH Canada, So You Start (EU and CA), Kimsufi (EU and CA), and Runabove.
 * Depending on which system you plan to use the wrapper, you should pick your endpoint accordingly.
 * The current (10/04/2015) known endpoints are already defined as class constants, but you can also create your own to suit your needs.
 *
 * @author Adrien Rabian
 * @version 0.1
 */
public class Endpoint {

    public static final Endpoint ovh_eu = new Endpoint("OVH Europe", "https://eu.api.ovh.com/1.0");
    public static final Endpoint ovh_ca = new Endpoint("OVH North-America", "https://ca.api.ovh.com/1.0");
    public static final Endpoint sys_eu = new Endpoint("So you Start Europe", "https://eu.api.soyoustart.com/1.0");
    public static final Endpoint sys_ca = new Endpoint("So you Start North America", "https://eu.api.soyoustart.com/1.0");
    public static final Endpoint ks_eu = new Endpoint("Kimsufi Europe", "https://eu.api.kimsufi.com/1.0");
    public static final Endpoint ks_ca = new Endpoint("Kimsufi North America", "https://ca.api.kimsufi.com/1.0");
    public static final Endpoint ra_ca = new Endpoint("Runabove", "https://api.runabove.com/1.0");

    private String name;
    private String URL;

    public Endpoint(String name, String URL) {
        this.name = name;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }
}
