package com.inin.tms.serialization;

import com.inin.tms.domain.Task;
import com.inin.tms.util.Util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TaskSerialization {

    /**
     * Serializing the task object
     * @param tasks Task Object to serialize
     */
    public static void serialize(Map<Integer, Task> tasks)
    {
        ObjectOutputStream oos = null;
           try {
               String fileName =  "tasks.ser";
               File file = Util.createFile(fileName, "src/main/resources/data/");
               if(file.exists()) {
                   oos = new ObjectOutputStream(new FileOutputStream(file));
               }
               else {
                   // if blnAppend is true in new FileOutputStream(fileName, blnAppend),
                   // then output will be appended to the existing content of the file. If false, file will be overwritten.
                   oos = new ObjectOutputStream(new FileOutputStream(file)) {
                       protected void writeStreamHeader() throws IOException {
                           reset();
                       }
                   };
               }
               oos.writeObject(tasks);
               if (oos != null)
                   oos.close();
           }catch(IOException ie){
               ie.printStackTrace();
           }
    }

    /**
     * deserializing the task object
     * @return all task in collection
     */
    public static Map<Integer, Task> deserialize(){
        Map<Integer, Task> taskData = new HashMap<Integer, Task>();
        try{
            String fileName = "tasks.ser";
            File file = Util.createFile(fileName, "src/main/resources/data/");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            taskData = (Map<Integer, Task>) ois.readObject();
        }
        catch (EOFException eof) {
            //eof.printStackTrace();
        }
        catch(IOException ie) {
            ie.printStackTrace();
        }
        catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }
        return taskData;
    }
}
