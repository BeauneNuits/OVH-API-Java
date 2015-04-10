package fr.rabian.ovhApi.utils;

import fr.rabian.ovhApi.beans.Endpoint;
import fr.rabian.ovhApi.http.HttpRequests;

public class Timestamps {
    public static final int SYSTEM = 1;
    public static final int OVH = 2;
    public static final int AUTO = 3;
    /**
     * Result of SystemTime - OVHTime
     */
    private int delta = 0;
    private long lastFetch = -1;
    private String url;

    public Timestamps (Endpoint e) {
        this.url = e.getURL() + "/auth/time";
    }

    private static long getSystemTime() {
        return (System.currentTimeMillis() / 1000L);
    }

    private long getCorrectedTime() {
        return getSystemTime() - delta;
    }

    private void correctTime() throws Exception {
        long time = getOVHTime();
        lastFetch = getSystemTime();
        delta = (int) (getSystemTime() - time);
    }

    private long getOVHTime() throws Exception {
        StringBuffer out = new StringBuffer();
        HttpRequests.sendGet(this.url, out);
        return Long.parseLong(out.toString());
    }

    public long getTime() throws Exception {
        return getTime(AUTO);
    }

    public long getTime(int source) throws Exception {
        long time;

        if (source == AUTO || source == SYSTEM) {
            if (lastFetch == -1 || getSystemTime() - lastFetch > 3600) {
                correctTime();
            }
            time = getCorrectedTime();
        } else if (source == OVH) {
            time = getOVHTime();
        } else {
            throw new IllegalArgumentException("Accepted values are : AUTO, SYSTEM, OVH.");
        }
        return time;
    }
}