package com.inin.taskmanagement.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created by root on 5/4/16.
 */
public class BaseDomain {
    /**
     * Create date
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd H:m:s")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime createdDate;
    /**
     * Modified date
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd H:m:s")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime modifiedDate;

    /**
     * Return the created date
     * @return created date
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Return the modified date
     * @return modified date
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
