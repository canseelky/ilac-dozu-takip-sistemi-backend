package com.msku.drugdosemonitoringsystem.utils;

import java.util.UUID;

public class VerificationCodeGenarator {

    public static String generateVerificationCode() {
        return UUID.randomUUID().toString().replaceAll("[a-zA-Z^-]", "").substring(0,4);
    }

}
