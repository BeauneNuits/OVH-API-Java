package fr.rabian.ovhApi;

import java.util.List;

/**
 * Created by Adrien on 31/03/2015.
 */
public class Customer {

    private final String cKey;
    private List<ScopeElement> scope;
    private String validationUrl;
    private String state;



    protected Customer(String cKey, List<ScopeElement> scope, String validationUrl, String state) {
        this.cKey = cKey;
        this.scope = scope;
        this.validationUrl = validationUrl;
        this.state = state;
    }

    protected Customer(String cKey) {
        this(cKey, null, null, null);
    }

    protected Customer() {
        this(null, null, null, null);
    }

    public String getcKey() {
        return cKey;
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
