package com.inin.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 6/4/16.
 */

/**
 *
 */
public class User {
    /**
     * id of the user
     */
    int userId;
    /**
     * user name
     */
    String name;
    /**
     * user created date
     */

    LocalDate createdDate;

    public User() {
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public int getUserId() {
        return userId;
    }

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.createdDate = LocalDate.now();
    }

    /**
     * list of task
     */
    List<Task> doerTasks;

    public String getName() {
        return name;
    }

    public List<Task> getDoerTasks() {
        return doerTasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", doerTasks=" + doerTasks +
                '}';
    }
}
