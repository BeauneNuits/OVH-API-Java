package fr.rabian.ovhApi.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an element of a client's API scope.
 *
 * @author Adrien Rabian
 * @version 0.1
 */
public class ScopeElement implements Cloneable {

    /**
     * Set of admitted HTTP methods
     */
    public static final Set<String> methods;

    static {
        methods = new HashSet<>();
        methods.add("GET");
        methods.add("PUT");
        methods.add("POST");
        methods.add("DELETE");
    }

    /**
     * Authorized HTTP method
     */
    private String method;

    /**
     * Authorized path, starting with /
     */
    private String path;

    /**
     * Creates a scope element.
     *
     * @param method Authorized HTTP method
     * @param path Authorized path
     * @throws IllegalArgumentException If the method/path isn't correct.
     */
    public ScopeElement(String method, String path) throws IllegalArgumentException {
        this.setMethod(method);
        this.setPath(path);
    }

    /**
     * Clones a scope element.
     *
     * @param s Element to clone
     */
    public ScopeElement(ScopeElement s) {
        this(s.getMethod(), s.getPath());
    }

    /**
     * Clones the current element.
     *
     * @return The clone.
     */
    @Override
    public Object clone() {
        ScopeElement clone = null;
        try {
            clone = (ScopeElement) super.clone();
        } catch(CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return clone;
    }

    /**
     * Returns the path.
     *
     * @return Authorized path
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) throws IllegalArgumentException {
        if (path == null || path.equals("")) {
            throw new IllegalArgumentException("Error : path cannot be empty/null.");
        }
        char firstChar = path.charAt(0);
        char lastChar = path.charAt(path.length() - 1);
        int occurDoubleSlash = path.indexOf("//");
        boolean lastCharOK = lastChar == '*' || Character.isAlphabetic(lastChar);
        boolean check = firstChar == '/' && occurDoubleSlash == -1 && lastCharOK;
        if (check) {
            this.path = path;
        } else {
            throw new IllegalArgumentException("Error : path should be correctly filled.");
        }
    }

    /**
     * Returns the HTTP method.
     *
     * @return Authorized HTTP method
     */
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) throws IllegalArgumentException {
        if (methods.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Error : please refer to HTTP methods.");
        }
    }
}
