package com.inin.taskmanager.exception;

/**
 * Created by virendradubey on 5/4/16.
 */
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(final String m){
        super(m);
    }
}
