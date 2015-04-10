package fr.rabian.ovhApi.utils;

import fr.rabian.ovhApi.beans.Endpoint;
import fr.rabian.ovhApi.http.HttpRequests;

/**
 * This class handles timestamps, from local and OVH sources.
 *
 * @author Adrien Rabian
 * @version 0.1
 */
public class Timestamps {
    public static final int SYSTEM = 1;
    public static final int OVH = 2;
    public static final int AUTO = 3;

    /**
     * Result of SystemTime - OVHTime
     */
    private int delta = 0;

    /**
     * System timestamp of last fetch from OVH.
     */
    private long lastFetch = -1;

    /**
     * Fetch URL
     */
    private String url;

    /**
     * Creates a Timestamps instance, fetching time from the specified OVH endpoint.
     *
     * @param e Endpoint
     */
    public Timestamps (Endpoint e) {
        this.url = e.getURL() + "/auth/time";
    }

    /**
     * Returns system time.
     *
     * @return System time
     */
    private static long getSystemTime() {
        return (System.currentTimeMillis() / 1000L);
    }

    /**
     * Returns system time corrected by OVH diff.
     *
     * @return Corrected system time
     */
    private long getCorrectedTime() {
        return getSystemTime() - delta;
    }

    /**
     * Updates the diff between system time and OVH time.
     *
     * @throws Exception From fetching
     */
    private void correctTime() throws Exception {
        long time = getOVHTime();
        lastFetch = getSystemTime();
        delta = (int) (getSystemTime() - time);
    }

    /**
     * Returns timestamp from OVH.
     *
     * @return Timestamp
     * @throws Exception From fetching
     */
    private long getOVHTime() throws Exception {
        StringBuffer out = new StringBuffer();
        HttpRequests.sendGet(this.url, out);
        return Long.parseLong(out.toString());
    }

    /**
     * Returns timestamp (source selected automatically).
     *
     * @return Timestamp
     * @throws Exception From fetching
     */
    public long getTime() throws Exception {
        return getTime(AUTO);
    }

    /**
     * Returns timestamp from the specified source.
     *
     * @param source Time source (OVH, SYSTEM, AUTO)
     * @return Timestamp
     * @throws Exception From fetching
     */
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