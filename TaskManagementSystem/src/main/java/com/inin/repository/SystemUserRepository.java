package com.inin.repository;

import com.inin.dao.UserDao;
import com.inin.domain.SystemUser;
import com.inin.serialize.Serialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Deepak on 2/4/16.
 * This is responsible for repository functionality of System users.
 */

@Repository
@Scope("singleton")
public class SystemUserRepository {

    @Autowired
    private Serialize serialize;

    @Autowired
    private UserDao userDao;

    private static Map<Integer, SystemUser> systemUserMap;

    public SystemUserRepository(){
        systemUserMap = new HashMap<>();
    }


    /**
     * This is responsible for add the user in repository.
     * */
    public int add(SystemUser systemUser) {
       // systemUserMap.put(systemUser.getId(), systemUser);

        return userDao.insert(systemUser);

    }


    /**
     * This is responsible to get all the user in repository.
     * */
    public List<SystemUser> getAllUsers(){
        /*return systemUserMap.values().stream()
                .collect(Collectors.toList());*/

        return userDao.getAllUsers();
    }


    /**
     * This is responsible to get the user from repository on the basis of id provided.
     * */
    public SystemUser getUser(int userId){
        /*Optional<SystemUser> userOptional = systemUserMap.values().stream()
                .filter(systemUser -> systemUser.getId() == userId)
                .findAny();

        if(userOptional.isPresent()){
            return userOptional.get();
        }

        return null;*/

        return userDao.getUser(userId);
    }


    /**
     * This is responsible for update user information in repository, on the basis of user id provided
     * */
    public SystemUser updateUser(int userId, SystemUser systemUser){
       /* SystemUser systemUser1 = getUser(userId);

        if(systemUser1 != null){
            systemUser1.setName(systemUser.getName());
            systemUser1.setUsername(systemUser.getUsername());
            systemUser1.setPassword(systemUser.getPassword());
        }

        return systemUser1;*/

        int update = userDao.updateUser(userId, systemUser);
        if(update == 1)
            return systemUser;
        else
            return null;
    }
}
