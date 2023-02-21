package com.ly.application.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtils extends LocalDateTimeUtil {

    /**
     * 当天最早时间
     *
     * @return
     */
    public static LocalDateTime getIndexDayMin() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 五天前
     *
     * @return
     */
    public static LocalDateTime getLastFiveDays() {

        return LocalDateTime.now().minusDays(5);
    }

    /**
     * 时间转LocalDateTime
     *
     * @param time
     * @param format
     * @return
     */
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        if (StrUtil.isBlank(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }
}
