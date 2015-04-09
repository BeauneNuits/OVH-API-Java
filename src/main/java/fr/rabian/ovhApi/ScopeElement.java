package fr.rabian.ovhApi;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adrien on 04/04/2015.
 */
public class ScopeElement implements Cloneable {

    public static final Set<String> methods;

    static {
        methods = new HashSet<>();
        methods.add("GET");
        methods.add("PUT");
        methods.add("POST");
        methods.add("DELETE");
    }

    private String method;
    private String path;

    public ScopeElement(String method, String path) throws IllegalArgumentException {
        this.setMethod(method);
        this.setPath(path);
    }

    public ScopeElement(ScopeElement s) {
        this(s.getMethod(), s.getPath());
    }

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
