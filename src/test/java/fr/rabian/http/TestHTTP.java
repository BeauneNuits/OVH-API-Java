package fr.rabian.http;

/**
 * Created by adrien on 27/03/15.
 */
public class TestHTTP {
    public static void main(String[] args) throws Exception {
        StringBuffer out = new StringBuffer();
        HttpRequests.sendPost("http://requestb.in/1aamhie1", out, "nom=Adrien&prenom=Rabian");
        System.out.println(out.toString());
    }
}
