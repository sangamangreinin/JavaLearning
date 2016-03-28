package com.inin.dispensary.model;

import java.util.List;

/**
 * Created by sangam on 28/3/16.
 */
public class Patient extends Authentication {
    private List<MedicalAction> medicalActions;

    /**
     *
     * @param id
     * @param name
     * @param address
     */
    public Patient(String id, String name, String address) {
        super(id, name, address);
    }

    /**
     *
     * @param id
     * @param name
     * @param address
     * @return
     */
    public static Patient create(String id, String name, String address) {
        return new Patient(id, name, address);
    }

    /**
     *
     * @param id
     * @param name
     * @param address
     * @param summary
     * @param medicines
     * @return
     */
    public static Patient create(String id, String name, String address, String summary, List<Medicine>medicines) {
        Patient patient = Patient.create(id, name, address);
        MedicalAction medicalAction = MedicalAction.create(summary, medicines);
        patient.addMedicalAction(medicalAction);
        return patient;
    }

    /**
     *
     * @param medicalAction
     */
    public void addMedicalAction(MedicalAction medicalAction) {
        getMedicalActions().add(medicalAction);
    }

    /**
     *
     * @return
     */
    public List<MedicalAction> getMedicalActions() {
        return medicalActions;
    }

    /**
     *
     * @param patient
     * @param name
     * @param address
     * @return
     */
    public static Patient update(Patient patient, String name, String address) {
        patient.setAddress(address);
        patient.setName(name);
        return patient;
    }
}
