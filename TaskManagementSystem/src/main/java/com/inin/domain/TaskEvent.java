package com.inin.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Deepak on 2/4/16.
 * This represents the Task events happen with particular task.
 */
public class TaskEvent {
    /** This is system user who is making the particular event on task. */
    SystemUser eventMaker;

    /** This is previous status of task. */
    Status previousStatus;

    /** This is description of event perform on task. */
    String description;

    /** This is date and time of event happen on task. */
    LocalDateTime eventDate;

    public TaskEvent(){
        this.eventDate = LocalDateTime.now();
    }

}
