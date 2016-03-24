package com.inin.bank.domain;

import java.io.*;

/**
 * Created by root on 24/3/16.
 */
public class FileIOUtil {

    /**
     * the destination file path where the serialized file will be stored
     *
     * @return String
     */
    public static String getDestinationPath() {
        return "/opt/work/inin/PracticeProjects/data/";
    }

    /**
     * serialize object
     *
     * @param object   object to serialize
     * @param fileName name of the file without extension
     */
    static void serialize(Object object, String fileName) {

        String destinationFile = FileIOUtil.getDestinationPath() + fileName + ".ser";
        try (FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deserialize object
     *
     * @param fileNameWithExtension name of the file with extension
     * @return Object the deserialized object
     */
    static Object deserialize(String fileNameWithExtension) {

        Object object = null;
        String destinationFile = FileIOUtil.getDestinationPath() + fileNameWithExtension;
        try (FileInputStream fileInputStream = new FileInputStream(destinationFile)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
