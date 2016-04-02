package com.inin.taskmanager.utils;

import com.inin.taskmanager.constants.TaskStatus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by virendra on 1/4/16.
 * Util class.
 * Utility class. This class contains the common general purpose operations.
 */
public class Util {

    /**
     * constant property containing id properties
     */
    private static final String PROP_FILE = "src/main/resources/id.properties";

    /**
     * creates the task status object from the String supplied
     *
     * @param status String status
     * @return TaskStatus object
     */
    public static TaskStatus getTaskStatus(String status) {
        TaskStatus statusObj;

        switch (status.toUpperCase()) {
            case "DRAFT":
            default:
                statusObj = TaskStatus.DRAFT;
                break;
            case "ASSIGNED":
                statusObj = TaskStatus.ASSIGNED;
                break;
            case "POSTPONED":
                statusObj = TaskStatus.POSTPONED;
                break;
            case "COMPLETE":
                statusObj = TaskStatus.COMPLETE;
                break;
        }
        return statusObj;
    }

    /**
     * reads the property from the properties file
     *
     * @param key String key
     * @return String value present in the properties file
     * @throws IOException if system could not load the properties file
     */
    public static String readProperty(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(PROP_FILE);
        prop.load(file);
        return prop.getProperty(key);

    }

    /**
     * writes the property in the properties file
     *
     * @param key   key which needs to be updated in the properties file
     * @param value values to be set for the property in the properties file
     * @throws IOException if system could not load the properties file
     */
    public static void writeProperty(String key, String value) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(PROP_FILE));
        FileOutputStream fOut = new FileOutputStream(PROP_FILE);
        prop.setProperty(key, value);
        prop.store(fOut, null);
    }

    /**
     * fetched the master userid from the properties file
     *
     * @return returns the unique user id
     */
    public static String getMasterUserId() {
        String uId;
        try {
            int id = Integer.parseInt(readProperty("userid"));
            uId = String.format("uid-%03d", id);
            writeProperty("userid", new Integer(++id).toString());
        } catch (IOException e) {
            uId = String.format("uid-%03d", 1);
        }
        return uId;
    }

    /**
     * fetched the master task id from the properties file
     *
     * @return returns the unique task id
     */

    public static String getMasterTaskId() {

        String uId;
        try {
            int id = Integer.parseInt(readProperty("taskid"));
            uId = String.format("T-%03d", id);
            writeProperty("taskid", new Integer(++id).toString());
        } catch (IOException e) {
            uId = String.format("T-%03d", 1);
        }
        return uId;

    }

    /**
     * fetched the master comment id from the properties file
     *
     * @return returns the unique comment id
     */
    public static String getMasterCommentId() {
        String uId;
        try {
            int id = Integer.parseInt(readProperty("commentid"));
            uId = String.format("c-%03d", id);
            writeProperty("commentid", new Integer(++id).toString());
        } catch (IOException e) {
            uId = String.format("c-%03d", 1);
        }
        return uId;
    }
}
