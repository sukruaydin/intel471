package com.intel471.utils;

public class BrowserUtils {

    //waits for given duration time
    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
