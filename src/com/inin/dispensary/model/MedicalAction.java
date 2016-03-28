package com.inin.dispensary.model;

import java.util.Date;
import java.util.List;

/**
 * store each medical action taken by doctor as medical history
 * Created by sangam on 28/3/16.
 */
public class MedicalAction {
    Date created;
    List<Medicine> medicines;
    String summary;

    /**
     * short summary of patient visit
     * @param summary short description of patient visit
     * @param medicines number of medicines allocated to patient
     */
    public MedicalAction(String summary, List<Medicine> medicines)
    {
        this.summary = summary;
        this.medicines = medicines;
    }

    /**
     *
     * @param summary short description of patient visit
     * @param medicines number of medicines allocated to patient
     * @return Medical action
     */
    public static MedicalAction create(String summary, List<Medicine> medicines)
    {
        return new MedicalAction(summary, medicines);
    }
}
