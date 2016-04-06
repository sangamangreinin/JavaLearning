package com.inin.components;

import com.inin.domain.SystemUser;
import org.springframework.stereotype.Component;

/**
 * Created by Deepak on 2/4/16.
 * This represents the component class for system user.
 */

@Component
public class UserComponent extends BaseComponent {

    /**
     * This is resoponsible to validate user information with required fields
     * */
    public void validateUser(SystemUser user){
        validateNull(user.getName(), "name");
        validateNull(user.getEmail(), "email");
        validateNull(user.getUsername(), "username");
        validateNull(user.getPassword(), "password");
    }


    /**
     * This is responsible to check equality of user information passed
     * */
    public boolean checkUser(SystemUser systemUser1, SystemUser systemUser2){
        return systemUser1.equals(systemUser2);
    }
}
