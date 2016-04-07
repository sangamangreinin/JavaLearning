package com.inin.taskmanager.controller.dto;

/**
 * Created by virendra on 2/4/16.
 * Query Request class. This stores the searchComments request to searchComments tasks
 *
 */

public class TaskQueryRequest {

    /**
     * status property to searchComments tasks by parameter
     */
    private String status;

    /**
     * name of the user who created the task
     */
    private String creatorId;

    /**
     * commentFlag is used to indicate that only comment is requred to caller
     */
    private boolean commentFlag = false;

    /**
     * task id
     */
    private Long taskId;

    public TaskQueryRequest(){

    }
    public TaskQueryRequest(Builder builder) {
        this.status = builder.status;
        this.creatorId = builder.creatorId;
        this.commentFlag = builder.commentFlag;
        this.taskId = builder.taskId;

    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getStatus() {
        return status;
    }

    public Long getTaskId() {
        return taskId;
    }

    public static class Builder{
        private String status;
        private String creatorId;
        private boolean commentFlag = false;
        private Long taskId;

        public Builder withTaskId(Long taskId) {
            this.taskId = taskId;
            return this;
        }


        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withCreatorId(String creatorId) {
            this.creatorId = creatorId;
            return this;
        }
        
        public TaskQueryRequest build(){
            return new TaskQueryRequest(this);
        }

        public Builder withComment() {
            this.commentFlag = true;
            return this;
        }
    }
}
