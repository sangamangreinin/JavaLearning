package com.inin.serialization;

import com.inin.domian.Patient;
import com.inin.util.Util;

import java.io.*;
public class PatientSerialization {

    /**
     * Serializing the patient object
     * @param patientDetails Patient Object to serialize
     */
    public static void serialize(Patient patientDetails)
    {
        ObjectOutputStream oos = null;
           try {
               String fileName = "test.ser";
               File file = Util.createFile("src/main/resources/account", fileName);
               if(file.length()<=0) {
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
               oos.writeObject(patientDetails);
           }catch(IOException ie){
               ie.printStackTrace();
           }
    }

    /**
     *
     * @param patientId
     * @return
     */
    public static Patient deserialize(long patientId){
        Patient patient = null;
        try{
            String fileName = "test.ser";
            File file = Util.createFile("src/main/resources/account", fileName);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            patient = (Patient) ois.readObject();
        }
        catch (EOFException eof) {
            eof.printStackTrace();
        }
        catch(IOException ie) {
            ie.printStackTrace();
        }
        catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }
        return patient;
    }
}
