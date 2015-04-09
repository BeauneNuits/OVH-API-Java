package fr.rabian.ovhApi;

import java.util.List;

/**
 * Created by Adrien on 31/03/2015.
 */
public class Customer {

    private final String consumerKey;
    private List<ScopeElement> scope;
    private String validationUrl;
    private String state;



    protected Customer(String consumerKey, List<ScopeElement> scope, String validationUrl, String state) {
        this.consumerKey = consumerKey;
        this.scope = scope;
        this.validationUrl = validationUrl;
        this.state = state;
    }

    protected Customer(String consumerKey) {
        this(consumerKey, null, null, null);
    }

    protected Customer() {
        this(null, null, null, null);
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public List<ScopeElement> getScope() {
        return scope;
    }

    public String getValidationUrl() {
        return validationUrl;
    }

    public String getState() {
        return state;
    }

    protected void setScope(List<ScopeElement> scope) {
        this.scope = scope;
    }
}
