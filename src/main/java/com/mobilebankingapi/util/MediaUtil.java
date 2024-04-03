package com.mobilebankingapi.util;

public class MediaUtil {
    public static String getExtension (String name){
        int lastDotIndex = name.lastIndexOf(".");
        return name.substring(lastDotIndex + 1);
    }
}
