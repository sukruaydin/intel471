package com.intel471.utils;

public class BrowserUtils {

    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
