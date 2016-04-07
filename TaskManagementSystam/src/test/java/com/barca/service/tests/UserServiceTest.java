package com.barca.service.tests;

import com.barca.model.User;
import com.barca.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by root on 3/4/16.
 */
public class UserServiceTest {

    @Autowired
    private static UserService userService;


    @Before
    public void setUp() throws Exception {
        userService = new UserService();
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User(1, "yogesh", "email");
        userService.createUser(user);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserWithEmptpName() throws Exception {
        User user = new User(1, "", "email");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserWithEmptyEmail() throws Exception {
        User user = new User(1, "Yogesh", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserWithIdLessThen0() throws Exception {
        User user = new User(-11, "Yogesh", "");
    }
}