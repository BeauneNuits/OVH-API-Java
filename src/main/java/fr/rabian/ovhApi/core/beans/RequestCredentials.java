package fr.rabian.ovhApi.core.beans;

import java.util.List;

/**
 * This class represents a crednetials request.
 * It is only designed for internal use (to simplify JSON serialization), and should not be used otherwise.
 *
 * @author Adrien Rabian
 * @version 0.1
 */
public class RequestCredentials {

    /**
     * List of ScopeElement representing the consumer's authorization's scope
     */
    private List<ScopeElement> accessRules;

    /**
     * Provided redirection URL after authentication
     */
    private String redirection;

    /**
     * Creates a credentials request.
     *
     * @param accessRules Scope
     * @param redirection Redirection URL
     */
    public RequestCredentials(List<ScopeElement> accessRules, String redirection) {
        this.accessRules = accessRules;
        this.redirection = redirection;
    }
}
