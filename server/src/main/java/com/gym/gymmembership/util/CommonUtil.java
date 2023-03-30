package com.gym.gymmembership.util;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CommonUtil {
    private CommonUtil() {
    }

    public static LocalDate convertToLocalDate(@NonNull LocalDateTime localDateTime){
        return LocalDate.from(localDateTime.atZone(ZoneId.of("Asia/Manila")));
    }

    public static LocalDate currentDate(){
        return LocalDate.now(ZoneId.of("Asia/Manila"));
    }
}
