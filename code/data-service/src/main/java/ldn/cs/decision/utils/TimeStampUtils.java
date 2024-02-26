package ldn.cs.decision.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 子川
 * @create 2024/1/17
 */
public class TimeStampUtils {
    public static boolean isSameDay(long timestamp1,long timestamp2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        int day1 = Integer.parseInt(simpleDateFormat.format(timestamp1 * 1000));
        int day2 = Integer.parseInt(simpleDateFormat.format(timestamp2 * 1000));
        return day1 == day2;
    }

    public static boolean isSameMonth(long timestamp1,long timestamp2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        int month1 = Integer.parseInt(simpleDateFormat.format(timestamp1 * 1000));
        int month2 = Integer.parseInt(simpleDateFormat.format(timestamp2 * 1000));
        return month1 == month2;
    }

    public static boolean isSameQuarter(long timestamp1,long timestamp2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        int month1 = Integer.parseInt(simpleDateFormat.format(timestamp1 * 1000));
        int month2 = Integer.parseInt(simpleDateFormat.format(timestamp2 * 1000));
        return getQuarter(month1) == getQuarter(month2);
    }
    static int getQuarter(int month) {
        if(month >= 1 && month <= 3) {
            return 1;
        }else if(month >= 4 && month <= 6) {
            return 2;
        }else if(month >= 7 && month <= 9) {
            return 3;
        }else if(month >= 10 && month <= 12) {
            return 4;
        }
        return 0;
    }
}
