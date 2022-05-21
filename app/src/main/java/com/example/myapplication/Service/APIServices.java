package com.example.myapplication.Service;

public class APIServices {
    private static String baseurl="http://192.168.1.10/cuahangsach/public/";
    public static String urlhinh="http://192.168.1.10/cuahangsach/public/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(baseurl).create(DataService.class);
    }
}
