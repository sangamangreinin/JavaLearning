package com.inin.taskmanagement.service;

import com.inin.taskmanagement.constant.Gender;
import com.inin.taskmanagement.dao.UserDao;
import com.inin.taskmanagement.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

/**
 * Created by root on 5/4/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService = new UserService();

    @Test
    public void testCreateUser(){
        User user = new User("Test user", "Test Address", "787884875478", "abc@gmail.com", Gender.MALE, LocalDate.of(1988,1,5));
        when(userDao.insertUser(user)).thenReturn(2l);
        userService.createUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserWithEmptyName(){
        User user = new User("", "Test Address", "787884875478", "abc@gmail.com", Gender.MALE, LocalDate.of(1988,1,5));
        userService.createUser(user);
    }

}
