package fr.rabian.ovhApi.core.http;

/**
 * Created by adrien on 27/03/15.
 */

public class TestHTTP {
    public static void main(String[] args) throws Exception {
        StringBuffer out = new StringBuffer();
        HttpRequests.sendGet("https://eu.api.ovh.com/1.0/auth/time", out);
        System.out.println("OVH : " + out.toString());
        System.out.println("System : " + (int) (System.currentTimeMillis() / 1000L));
    }
}
