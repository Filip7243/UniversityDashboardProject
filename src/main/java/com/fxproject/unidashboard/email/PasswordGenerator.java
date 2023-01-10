package com.fxproject.unidashboard.email;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PasswordGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordGenerator() {
    }

    public static String generatePassword() {
        return new BigInteger(130, RANDOM).toString(32);
    }

}
