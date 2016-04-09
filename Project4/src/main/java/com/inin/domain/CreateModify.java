package com.inin.domain;

import java.time.LocalDateTime;

/** super class for Task and User domain model objects
 * Created by root on 6/4/16.
 */
public class CreateModify {

    protected LocalDateTime created = LocalDateTime.now();

    protected LocalDateTime modified = LocalDateTime.now();

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }
}
