package com.inin.taskmanagement.constant;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by root on 5/4/16.
 */
public enum Gender {
    MALE("male"),FEMALE("female"),UNKNOWN("unknown");

    private String name;

    Gender(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
