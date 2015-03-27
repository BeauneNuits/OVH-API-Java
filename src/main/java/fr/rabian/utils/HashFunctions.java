package fr.rabian.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class presents functions to handle cryptographic hashes.
 *
 * @author Adrien Rabian
 */
public class HashFunctions {
    /**
     * Get the hash of a string with a specified algorithm.
     *
     * @param algo  Algorith to use.
     * @param input String to hash
     * @return Hash
     * @throws java.security.NoSuchAlgorithmException Thrown if the specified algorithm is not supported.
     */
    public static String hashMD(String algo, String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algo);
        md.update(input.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}