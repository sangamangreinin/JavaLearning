package com.inin.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by evansbelly on 7/4/16.
 */
public class Util {

    public static LocalDateTime localDateFromSqlTimestamp(Timestamp timestamp) {
        return LocalDateTime
                .ofInstant(timestamp.toInstant(), ZoneOffset.systemDefault());
    }
}
