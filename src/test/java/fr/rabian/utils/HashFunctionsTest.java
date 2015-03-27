package fr.rabian.utils;

import static org.junit.Assert.*;

public class HashFunctionsTest {

    @org.junit.Test
    public void testHashMD() throws Exception {
        assertTrue("SHA-1", HashFunctions.hashMD("SHA-1", "Adrien").equals("8430b1320a816b6014141350c7f051f1f587c1c9"));
        assertTrue("MD5", HashFunctions.hashMD("MD5", "Adrien").equals("2522f7c9c098302a41f4e8b2bd821d94"));
    }
}