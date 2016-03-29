package com.inin;

import com.inin.domain.model.Chemist;
import com.inin.domain.model.Doctor;
import com.inin.domain.model.Patient;
import com.inin.utils.ApplicationSession;
import com.inin.utils.Util;

import java.util.List;
import java.util.Scanner;

/**
 * Created by virendradubey on 29/3/16.
 */
public class ApplicationRunner {

    private static ApplicationRunner _instance;
    private ApplicationSession session;
    private Scanner scan;
    List<Patient> patients;
    List<Doctor> doctors;
    List<Chemist> chemists;

    private ApplicationRunner(){
        scan = new Scanner(System.in);
        session = ApplicationSession.getInstance();
    }

    public static ApplicationRunner getInstance(){
        if (! (_instance instanceof ApplicationRunner)){
            _instance = new ApplicationRunner();
        }

        return _instance;
    }

    /**
     * launches the application for the user
     */
    public void launch() {
        initApp();
        if (session.isSessionValid()){
            System.out.println("\t===== Welcome =====\n");
            Util.displayMenu(Util.TOP_MENU);
        }else {
            Util.displayMenu(Util.LOGIN);
            this.loginUser();
        }

    }

    private void initApp() {
        //load all files

    }

    private void loginUser() {
        System.out.println("Enter username: ");
        String username = scan.nextLine();
        System.out.println("Enter password: ");
        String password = scan.nextLine();

        System.out.println("Username: "+username+"| password: "+ password);

        System.exit(0);

    }


    public boolean hasSession() {
        return session.isSessionValid();
    }

}
