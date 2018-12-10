package com.peery.android.projectscorpion;

public class Common {
    private static final String BASE_URL = "http://ip.jsontest.com/";

    public static IpService getIpService(){
        return RetroClient.getClient(BASE_URL).create(IpService.class);
    }
}
