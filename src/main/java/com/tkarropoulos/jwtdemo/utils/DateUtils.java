package com.tkarropoulos.jwtdemo.utils;

import java.time.Instant;

public class DateUtils {

    public static Long epochNow() {
        return Instant.now().toEpochMilli();
    }
}
