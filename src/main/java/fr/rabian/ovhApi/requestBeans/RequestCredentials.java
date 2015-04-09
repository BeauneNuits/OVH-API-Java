package fr.rabian.ovhApi.requestBeans;

import fr.rabian.ovhApi.ScopeElement;

import java.util.List;

/**
 * Created by Adrien on 09/04/2015.
 */
public class RequestCredentials {
    private List<ScopeElement> accessRules;
    private String redirection;

    public RequestCredentials(List<ScopeElement> accessRules, String redirection) {
        this.accessRules = accessRules;
        this.redirection = redirection;
    }
}
