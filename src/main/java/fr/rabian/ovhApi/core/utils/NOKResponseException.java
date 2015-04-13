package fr.rabian.ovhApi.core.utils;

import java.io.IOException;

/**
 * @author Adrien Rabian
 */
public class NOKResponseException extends IOException {

    private int responseCode;

    public NOKResponseException(int responseCode) {
        super("HTTP response code " + responseCode + " encountered.");
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
