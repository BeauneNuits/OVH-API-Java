package fr.rabian.ovhApi.http;

/**
 * Created by Adrien on 09/04/2015.
 */
public class Header {
    String key;
    String value;

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
