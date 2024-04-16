package com.plutossy.utils;

import java.util.Date;

public class VerCodeGenerateUtil {
    public static int getMinute(Date fromDate, Date toDate) {
        return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60));
    }
}
