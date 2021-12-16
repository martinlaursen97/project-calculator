package com.eksamen.projectcalculator.domain.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calculator {

    public static int getWorkDaysBetweenDates(String start, String finish) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy MM dd");
            Date date1 = df.parse(start);
            Date date2 = df.parse(finish);
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date1);
            cal2.setTime(date2);

            int workdays = 0;
            while (cal1.before(cal2)) {
                if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                        && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                    workdays++;
                }
                cal1.add(Calendar.DATE, 1);
            }
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                workdays++;
            }
            return workdays;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
