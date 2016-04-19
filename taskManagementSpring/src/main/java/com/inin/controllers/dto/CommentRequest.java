package com.inin.controllers.dto;

import java.time.LocalDateTime;

/**
 * Created by root on 19/4/16.
 */
public class CommentRequest {
    /**
     * comment description
     */
    public String description;
    /**
     * comment done by which user
     */
    public int commentedBy;
    /**
     * Date and time when comment was made on task
     */
    public LocalDateTime commentDateTime;
}
