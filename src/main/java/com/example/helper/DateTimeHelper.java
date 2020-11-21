package com.example.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeHelper {

    public static LocalDateTime milisecondsToDateTime(long milisegundos) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milisegundos), ZoneId.systemDefault());
    }
}
