package com.inin.domain;

import java.time.LocalDateTime;

/**
 * Created by evansbelly on 6/4/16.
 */
public class Comment {

    private int commentId;
    private String comment;
    private int commentBy;
    private LocalDateTime created = modified = LocalDateTime.now();
    private LocalDateTime modified;

    public Comment(int id, String comment, int commentBy, LocalDateTime modified, LocalDateTime created) {
        this.commentId = id;
        this.comment = comment;
        this.commentBy = commentBy;
        this.created = created;
        this.modified = modified;
    }

    public String getComment() {
        return comment;
    }

    public int getCommentBy() {
        return commentBy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

}
