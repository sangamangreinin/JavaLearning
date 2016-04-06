package com.inin.repository;

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

    private static Map<Integer, SystemUser> systemUserMap;

    public SystemUserRepository(){
        systemUserMap = new HashMap<>();
    }


    /**
     * This is responsible for add the user in repository.
     * */
    public void add(SystemUser systemUser) {
        /*File file = new File(Constatnts.USER_SER_FILE_PATH.concat(Constatnts.USER_SER_FILE_NAME));
        if (serialize.serialisedObject(systemUser, file, true)){
            systemUserList.add(systemUser);
        }else {
            throw new RuntimeException("Failed to serialize the User data.");
        }*/

        systemUserMap.put(systemUser.getId(), systemUser);
    }


    /**
     * This is responsible to get all the user in repository.
     * */
    public List<SystemUser> getAllUsers(){
        return systemUserMap.values().stream()
                .collect(Collectors.toList());
    }


    /**
     * This is responsible to get the user from repository on the basis of id provided.
     * */
    public SystemUser getUser(int userId){
        Optional<SystemUser> userOptional = systemUserMap.values().stream()
                .filter(systemUser -> systemUser.getId() == userId)
                .findAny();

        if(userOptional.isPresent()){
            return userOptional.get();
        }

        return null;
    }


    /**
     * This is responsible for update user information in repository, on the basis of user id provided
     * */
    public SystemUser updateUser(int userId, SystemUser systemUser){
        SystemUser systemUser1 = getUser(userId);

        if(systemUser1 != null){
            systemUser1.setName(systemUser.getName());
            systemUser1.setUsername(systemUser.getUsername());
            systemUser1.setPassword(systemUser.getPassword());
        }

        return systemUser1;

    }
}
