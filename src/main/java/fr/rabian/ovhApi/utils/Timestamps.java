package fr.rabian.ovhApi.utils;

import fr.rabian.ovhApi.http.HttpRequests;

public abstract class Timestamps {
    public static final int SYSTEM = 1;
    public static final int OVH = 2;
    public static final int AUTO = 3;
    /**
     * Result of SystemTime - OVHTime
     */
    private static int delta = 0;
    private static long lastFetch = -1;

    private static long getSystemTime() {
        return (System.currentTimeMillis() / 1000L);
    }

    private static long getCorrectedTime() {
        return getSystemTime() - delta;
    }

    private static void correctTime() throws Exception {
        long time = getOVHTime();
        lastFetch = getSystemTime();
        delta = (int) (getSystemTime() - time);
    }

    private static long getOVHTime() throws Exception {
        StringBuffer out = new StringBuffer();
        HttpRequests.sendGet("https://eu.api.ovh.com/1.0/auth/time", out, null);
        return Long.parseLong(out.toString());
    }

    public static long getTime() throws Exception {
        return getTime(AUTO);
    }

    public static long getTime(int source) throws Exception {
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