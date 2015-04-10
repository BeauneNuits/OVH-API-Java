package fr.rabian.ovhApi.beans;

import java.util.List;

/**
 * Represents a consumer used to access the API.
 *
 * @author Adrien Rabian
 * @version 0.1
 */
public class Consumer {

    /**
     * The consumer's key
     */
    private final String consumerKey;

    /**
     * The authorization's scope
     */
    private List<ScopeElement> scope;

    /**
     * URL provided by OVH for the customer to authenticate
     */
    private String validationUrl;

    /**
     * Current state of the consumer's token
     * As for v0.1, this attribute is not correctly implemented.
     */
    private String state;


    /**
     * Creates a consumer.
     *
     * @param consumerKey Consumer's token
     * @param scope Authorization's scope
     * @param validationUrl Validation URL
     * @param state Token's status
     */
    public Consumer(String consumerKey, List<ScopeElement> scope, String validationUrl, String state) {
        this.consumerKey = consumerKey;
        this.scope = scope;
        this.validationUrl = validationUrl;
        this.state = state;
    }

    /**
     * Creates a consumer.
     *
     * @param consumerKey Consumer's token
     */
    public Consumer(String consumerKey) {
        this(consumerKey, null, null, null);
    }

    /**
     * Creates a consumer.
     */
    public Consumer() {
        this(null, null, null, null);
    }

    /**
     * Returns the consumer's key.
     *
     * @return Token
     */
    public String getConsumerKey() {
        return consumerKey;
    }

    /**
     * Returns the authorization's scope.
     * @return Scope
     */
    public List<ScopeElement> getScope() {
        return scope;
    }

    /**
     * Returns the authentication URL.
     * @return Validation URL
     */
    public String getValidationUrl() {
        return validationUrl;
    }

    /**
     * Returns the current state of the consumer's token
     * As for v0.1, this attribute is not correctly implemented.
     *
     * @return Status
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the scope for future use.
     * CAUTION : this does <em>not</em> change the actual scope on OVH's side. You have to create a new token instead.
     *
     * @param scope Scope to set
     */
    public void setScope(List<ScopeElement> scope) {
        this.scope = scope;
    }
}
