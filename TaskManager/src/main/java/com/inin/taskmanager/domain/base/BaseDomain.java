package com.inin.taskmanager.domain.base;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by virendra on 1/4/16.
 * BaseDomain class.
 * Abstract class containing common properties of all domains.
 */
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * createdDate stores the date of creation for the domain which
     * extends the BaseDomain
     */
    @DateTimeFormat(pattern = "dd-MM-yyyy''HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime createdDate;
    /**
     * modifiedDate stores the date of modification for the domain which
     * extends the BaseDomain
     */

    @DateTimeFormat(pattern = "dd-MM-yyyy''HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime modifiedDate;

    public BaseDomain() {
    }

    /**
     * gets the create time of the object
     *
     * @return LocalDateTime object
     */
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    /**
     * gets the modified time of the object
     *
     * @return LocalDateTime object
     */
    public LocalDateTime getModifiedDate() {
        return this.modifiedDate;
    }
}
