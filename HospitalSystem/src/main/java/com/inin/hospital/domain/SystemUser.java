package com.inin.hospital.domain;

import com.inin.hospital.Helper.Gender;

import java.time.LocalDateTime;

/**
 * Created by Deepak on 28/3/16.
 * This class is represents the System user in system,
 * who having Login authority and there respective access to system.
 */
public class SystemUser extends User{
    private String username;
    private String password;

    /**
     * This is resposible for creating system user with following details.
     * @param username is username for login to system.
     * @param password is password for login into the system.
     * @param name is name of user in system
     * @param gender is gender of user in system
     * @param birthDate is date of birth of user in system
     * @param mobile is mobile number of user in system
     * @param email is email id of user in system
     * @param landline is land line number of user in system*/
    public SystemUser(String username, String password, String name, Enum<Gender> gender,
                      LocalDateTime birthDate, int mobile, String email, int landline, String address) {
        super(name, gender, birthDate, mobile, email, landline, address);
        this.username = username;
        this.password = password;
    }

    public SystemUser(String username, String password, String name, Enum<Gender> gender, LocalDateTime birthDate, int mobile, String email, String address) {
        this(username, password, name, gender, birthDate, mobile, email, 0, address);
    }

    public SystemUser(String username, String password, String name, Enum<Gender> gender, LocalDateTime birthDate, int mobile, int landline, String address) {
        this(username, password, name, gender, birthDate, mobile, null, landline, address);
    }
}
