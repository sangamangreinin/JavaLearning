package com.inin;

/**
 * Created by virendradubey on 28/3/16.
 * Main class to start the application
 */
public class Main {

    /**
     * application starts with this method
     * @param args String array supplied as program arguments
     */
    public static void main(String[] args) {
        System.out.println("\t===== Welcome to Patient Management System =====\n");
        ApplicationRunner.getInstance().launch();
    }
}
