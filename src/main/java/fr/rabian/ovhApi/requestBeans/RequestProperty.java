package fr.rabian.ovhApi.requestBeans;

/**
 * Created by Adrien on 09/04/2015.
 */
public class RequestProperty {
    String key;
    String value;

    public RequestProperty(String key, String value) {
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
