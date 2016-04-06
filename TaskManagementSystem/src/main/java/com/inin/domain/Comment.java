package com.inin.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created by Deepak on 2/4/16.
 * This domain model class of system, which represents the Comments given on particular task in system.
 */
public class Comment {
    /**
     * Comment made on particular task.
     * */
    private String message;

    /**
     * This is commenter who is commenting on paritcular task.
     * */
    private SystemUser commenter;

    /**
     * This is date and time of comment made on task.
     * */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created;


    public Comment(){
        this.created = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public SystemUser getCommenter() {
        return commenter;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "message='" + message + '\'' +
                ", commenter=" + commenter +
                ", created=" + created +
                '}';
    }
}
