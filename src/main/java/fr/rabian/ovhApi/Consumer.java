package fr.rabian.ovhApi;

import java.util.List;

/**
 * Created by Adrien on 31/03/2015.
 */
public class Consumer {

    private final String consumerKey;
    private List<ScopeElement> scope;
    private String validationUrl;
    private String state;



    protected Consumer(String consumerKey, List<ScopeElement> scope, String validationUrl, String state) {
        this.consumerKey = consumerKey;
        this.scope = scope;
        this.validationUrl = validationUrl;
        this.state = state;
    }

    protected Consumer(String consumerKey) {
        this(consumerKey, null, null, null);
    }

    protected Consumer() {
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
