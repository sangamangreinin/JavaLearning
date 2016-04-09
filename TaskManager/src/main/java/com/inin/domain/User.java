package com.inin.domain;

import java.time.LocalDateTime;

/**
 * Created by evansbelly on 6/4/16.
 */
public class User {

    private int userId;
    private String name;
    private String email;
    private LocalDateTime modified;
    private LocalDateTime created = modified = LocalDateTime.now();

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }
}
