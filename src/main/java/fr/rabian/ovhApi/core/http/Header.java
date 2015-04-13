package fr.rabian.ovhApi.core.http;

/**
 * Represents an HTTP header for use in HTTP requests.
 */
public class Header {

    /**
     * The header's title
     */
    String key;

    /**
     * The header's value
     */
    String value;

    /**
     * Creates a header.
     *
     * @param key Title
     * @param value Value
     */
    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the title.
     *
     * @return Key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the value.
     *
     * @return Value
     */
    public String getValue() {
        return value;
    }
}
