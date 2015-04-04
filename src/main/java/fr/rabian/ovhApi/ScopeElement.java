package fr.rabian.ovhApi;

/**
 * Created by Adrien on 04/04/2015.
 */
public class ScopeElement implements Cloneable {

    public static final int GET = 1;
    public static final int POST = 2;
    public static final int PUT = 3;
    public static final int DELETE = 4;

    private int method;
    private String path;

    public ScopeElement(int method, String path) throws IllegalArgumentException {
        setMethod(method);
        setPath(path);
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
        char lastChar = path.charAt(path.length());
        int occurDoubleSlash = path.indexOf("//");
        boolean lastCharOK = lastChar == '*' || Character.isAlphabetic(lastChar);
        boolean check = firstChar == '/' && occurDoubleSlash == -1 && lastCharOK;
        if (check) {
            this.path = path;
        } else {
            throw new IllegalArgumentException("Error : path should be correctly filled.");
        }
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) throws IllegalArgumentException {
        if (method <= 4 && method >= 1) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Error : method should be >0 and <5. Please refer to class constants.");
        }
    }
}
