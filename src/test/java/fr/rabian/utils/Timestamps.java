package fr.rabian.utils;

import fr.rabian.http.HttpRequests;

/**
 * Created by adrien on 30/03/15.
 */
public abstract class Timestamps {
    public static final int SYSTEM = 1;
    public static final int OVH = 2;
    public static final int AUTO = 3;

    private static int delta = 0;
    private static long lastFetch = -1;

    private static long getSystemTime() {
        return (System.currentTimeMillis() / 1000L);
    }

    private static long getOVHTime() throws Exception {
        StringBuffer out = new StringBuffer();
        HttpRequests.sendGet("https://eu.api.ovh.com/1.0/auth/time", out);
        return Long.parseLong(out.toString());
    }

    public static long getTime() throws Exception {
        return getTime(AUTO);
    }

    public static long getTime(int source) throws Exception {
        long time = -1;

        if (lastFetch == -1 || getSystemTime() - lastFetch > 3600) {
            time = getOVHTime();
            lastFetch = getSystemTime();
            delta = (int)(getSystemTime() - time);
        }
    }
}
