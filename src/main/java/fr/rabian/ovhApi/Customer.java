package fr.rabian.ovhApi;

import java.util.ArrayList;

/**
 * Created by Adrien on 31/03/2015.
 */
public class Customer {

    private final String cKey;
    private ArrayList<ScopeElement> scope;

    protected Customer(String cKey, ArrayList<ScopeElement> scope) {
        this.cKey = cKey;
        this.scope = scope;
    }

    public String getcKey() {
        return cKey;
    }

    public ArrayList<ScopeElement> getScope() {
        return scope;
    }
}
