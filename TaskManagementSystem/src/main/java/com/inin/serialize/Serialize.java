package com.inin.serialize;

import com.inin.controller.Constatnts;
import com.inin.utility.Utility;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Deepak on 2/4/16.
 */
@Service
public class Serialize {

    public Serialize(){
        Utility.createFile(Constatnts.USER_SER_FILE_PATH, Constatnts.USER_SER_FILE_NAME);
        Utility.createFile(Constatnts.TASK_SER_FILE_PATH, Constatnts.TASK_SER_FILE_NAME);
    }

    public boolean serialisedObject(Object object, File file, boolean append){
        ObjectOutputStream oos = null;
        try {
            if(append){
                // append to the file.
                oos = new ObjectOutputStream(new FileOutputStream(file, true)){
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }
            else {
                // update whole file.
                oos = new ObjectOutputStream(new FileOutputStream(file));
            }

            oos.writeObject(object);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
