package com.inin.service;

import com.inin.components.UserComponent;
import com.inin.domain.SystemUser;
import com.inin.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak on 2/4/16.
 * This service class represents the User service of System.
 */

@Service
@Scope("singleton")
public class SystemUserService extends BaseService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private UserComponent userComponent;

    private List<SystemUser> tempList = new ArrayList<>();


    /**
     * This is service method to create the user in system
     * @param systemUser is system user object which is stored in system.
     * */
    public SystemUser createUser(SystemUser systemUser){
        userComponent.validateUser(systemUser);
        systemUserRepository.add(systemUser);
        return systemUser;
    }


    /**
     * This is service method to get the all users in system.
     * */
    public List<SystemUser> get(){
        return systemUserRepository.getAllUsers();
    }


    /**
     * This is service method to get the specific user in system on the basis of id provided in url path.
     * */
    public SystemUser get(int userId){
        return systemUserRepository.getUser(userId);
    }


    /**
     * This is service update method to update the user info on the basis of id provided in url path.
     * One can update only the name, username and password but not email.
     * */
    public SystemUser update(int userId, SystemUser systemUser){
        userComponent.validateUser(systemUser);
        return systemUserRepository.updateUser(userId, systemUser);
    }
}
