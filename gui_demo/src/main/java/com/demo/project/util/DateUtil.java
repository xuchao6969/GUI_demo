package com.demo.project.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author xc
 * @date 2021-05-28
 */
public class DateUtil {

    /**
     * 日期格式校验 yyyy-MM-dd
     */
    public static Boolean checkDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean flag = true;
        try {
            //解析日期和时间
            LocalDate.parse(date, dtf);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 根据出生日期计算年龄
     */
    public static int getAge(String birth) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        int age = 0;
        try {
            birthday = df.parse(birth);
            Calendar born = Calendar.getInstance();

            Calendar now = Calendar.getInstance();

            if (birthday != null) {
                now.setTime(new Date());
                born.setTime(birthday);
                if (born.after(now)) {
                    throw new IllegalArgumentException("年龄不能超过当前日期");
                }

                age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
                if (now.get(Calendar.MONTH) < born.get(Calendar.MONTH)) {
                    age -= 1;
                } else {
                    if (now.get(Calendar.MONTH) == born.get(Calendar.MONTH)
                            && now.get(Calendar.DATE) < born.get(Calendar.DATE)) {
                        age -= 1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age;
    }


}
