package com.inin.taskmanager.domain.base;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by virendra on 1/4/16.
 * BaseDomain class.
 * Abstract class containing common properties of all domains.
 */
public class BaseDomain implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * createdDate stores the date of creation for the domain which
     * extends the BaseDomain
     */
    protected LocalDateTime createDate;
    /**
     * modifiedDate stores the date of modification for the domain which
     * extends the BaseDomain
     */
    protected LocalDateTime modifiedDate;

    /**
     * gets the
     * @return
     */
    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getModifiedDate() {
        return this.modifiedDate;
    }
}
