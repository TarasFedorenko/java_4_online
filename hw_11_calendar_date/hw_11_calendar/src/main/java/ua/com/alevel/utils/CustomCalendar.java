package ua.com.alevel.utils;

import java.util.Arrays;
import java.util.Objects;

public class CustomCalendar {
    int millisecond;
    int second;
    int minute;
    int hour;
    int day;
    int month;
    int year;
    long secondInMillis = 1000L;
    long minuteInMillis = secondInMillis * 60;
    long hourInMillis = minuteInMillis * 60;
    long dayInMillis = hourInMillis * 24;
    int fourYear = 1461;
    int[] daysInMonth = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    int[] daysInMonthLeapYear = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366};

    public CustomCalendar() {
        this(System.currentTimeMillis() + 62_168_436_000_000L);
    }

    public CustomCalendar(String format) {
        set(format);
    }

    public CustomCalendar(long time) {
        converterToDate(time);
    }

    private boolean isLeapYear(int yearNumbers) {
        return yearNumbers % 4 == 0;
    }

    private void converterToDate(long timeInMillis) {
        int days = (int) (timeInMillis / dayInMillis);
        long leftover = timeInMillis - (days * dayInMillis);
        long yearsCount = days % fourYear;
        int adder = 0;
        if (yearsCount >= 1095) {
            adder = 3;
        } else if (yearsCount >= 730) {
            adder = 2;
        } else if (yearsCount >= 365) {
            adder = 1;
        }
        int yearsNumber = (days / fourYear) * 4 + adder;
        int monthNumber = 1;
        int dayNumber = 1;
        int countDays = (days) - ((((yearsNumber) * 365) + ((yearsNumber) / 4)));
        if (!isLeapYear(yearsNumber)) {
            for (int j = 1; j < daysInMonth.length; j++) {
                if (daysInMonth[j] > countDays) {
                    monthNumber = j;
                    dayNumber = (countDays + 1) - daysInMonth[j - 1];
                    break;
                }
            }
        } else {
            for (int j = 1; j < daysInMonthLeapYear.length; j++) {
                if (daysInMonthLeapYear[j] > countDays) {
                    monthNumber = j;
                    dayNumber = (countDays + 1) - daysInMonthLeapYear[j - 1];
                    break;
                }
            }
        }
        int hourNumber = (int) (leftover / hourInMillis);
        int minuteNumber = (int) (((leftover - (hourNumber * hourInMillis))) / minuteInMillis);
        int secondNumber = (int) ((((leftover - (hourNumber * hourInMillis))) % minuteInMillis) / secondInMillis);
        int milliNumber = (int) ((((leftover - (hourNumber * hourInMillis))) % minuteInMillis) % secondInMillis);

        set(yearsNumber, monthNumber, dayNumber, hourNumber, minuteNumber, secondNumber, milliNumber);
    }

    public long unconvertToMillis(String format) {
        if (format.matches("\\d{1,4}-\\d{1,2}-\\d{1,2}")) {
            int[] arrayNumbers = Arrays.stream(format.split("\\W")).mapToInt(Integer::parseInt).toArray();
            long countYears = (((long) (arrayNumbers[0] / 4) * fourYear) + ((arrayNumbers[0] % 4) * 365) + 1);
            long countMonth = 0;
            if (!isLeapYear(arrayNumbers[0])) {
                countMonth = daysInMonth[arrayNumbers[1] - 1];
            } else {
                countMonth = daysInMonthLeapYear[arrayNumbers[1] - 1];
            }
            long sumMillis = (countYears + countMonth + arrayNumbers[2]) * dayInMillis;
            return sumMillis;
        } else if (format.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}")) {
            int[] arrayNumbers = Arrays.stream(format.split("\\W")).mapToInt(Integer::parseInt).toArray();
            long countYears = (((long) (arrayNumbers[0] / 4) * fourYear) + ((arrayNumbers[0] % 4) * 365) + 1);
            long countMonth = 0;
            if (!isLeapYear(arrayNumbers[0])) {
                countMonth = daysInMonth[arrayNumbers[1] - 1];
            } else  {
                countMonth = daysInMonthLeapYear[arrayNumbers[1] - 1];
            }
            long sumMillis = (countYears + countMonth + arrayNumbers[2]) * dayInMillis + (arrayNumbers[3] * hourInMillis) + (arrayNumbers[4] * minuteInMillis);
            return sumMillis;
        } else if (format.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            int[] arrayNumbers = Arrays.stream(format.split("\\W")).mapToInt(Integer::parseInt).toArray();
            long countYears = (((long) (arrayNumbers[0] / 4) * fourYear) + ((arrayNumbers[0] % 4) * 365) + 1);
            long countMonth = 0;
            if (!isLeapYear(arrayNumbers[0])) {
                countMonth = daysInMonth[arrayNumbers[1] - 1];
            } else {
                countMonth = daysInMonthLeapYear[arrayNumbers[1] - 1];
            }
            long sumMillis = (countYears + countMonth + arrayNumbers[2]) * dayInMillis + (arrayNumbers[3] * hourInMillis) + (arrayNumbers[4] * minuteInMillis) + (arrayNumbers[5] * secondInMillis);
            return sumMillis;
        } else if (format.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2} \\d{1,3}")) {
            int[] arrayNumbers = Arrays.stream(format.split("\\W")).mapToInt(Integer::parseInt).toArray();
            long countYears = (((long) (arrayNumbers[0] / 4) * fourYear) + ((arrayNumbers[0] % 4) * 365));
            long countMonth = 0;
            if (!isLeapYear(arrayNumbers[0])) {
                countMonth = daysInMonth[arrayNumbers[1] - 1];
            } else {
                countMonth = daysInMonthLeapYear[arrayNumbers[1] - 1];
            }
            long sumMillis = (countYears + countMonth + (arrayNumbers[2] - 1)) * dayInMillis + (arrayNumbers[3] * hourInMillis) + (arrayNumbers[4] * minuteInMillis) + (arrayNumbers[5] * secondInMillis) + arrayNumbers[6];
            return sumMillis;
        } else {
            throw new RuntimeException("Wrong format of Date");
        }
    }

    public static CustomCalendar now() {
        return new CustomCalendar();
    }

    public long getTimeMillis() {
        return System.currentTimeMillis() + 62_168_608_800_000L;
    }

    private void set(int year, int month) {
        this.year = year;
        this.month = month;
    }

    private void set(int year, int month, int day, int hour, int minute) {
        set(year, month);
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    private void set(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        set(year, month, day, hour, minute);
        this.second = second;
        this.millisecond = millisecond;
    }

    private void set(String format) {
        if (format.matches("\\d{1,4}-\\d{1,2}-\\d{1,2}")) {
            getDateArray(format);
        } else if (format.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}")) {
            getDateArray(format);
        } else if (format.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            getDateArray(format);
        } else if (format.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2} \\d{1,3}")) {
            getDateArray(format);
        } else {
            throw new RuntimeException("Wrong format of Date");
        }
    }

    private void getDateArray(String str) {
        int[] arrayNumbers = Arrays.stream(str.split("\\W")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < arrayNumbers.length; i++) {
            if (arrayNumbers.length == 3) {
                setYear(arrayNumbers[0]);
                setMonth(arrayNumbers[1]);
                setDay(arrayNumbers[2]);
            } else if (arrayNumbers.length == 5) {
                setYear(arrayNumbers[0]);
                setMonth(arrayNumbers[1]);
                setDay(arrayNumbers[2]);
                setHour(arrayNumbers[3]);
                setMinute(arrayNumbers[4]);
            } else if (arrayNumbers.length == 6) {
                setYear(arrayNumbers[0]);
                setMonth(arrayNumbers[1]);
                setDay(arrayNumbers[2]);
                setHour(arrayNumbers[3]);
                setMinute(arrayNumbers[4]);
                setSecond(arrayNumbers[5]);
            } else if (arrayNumbers.length == 7) {
                setYear(arrayNumbers[0]);
                setMonth(arrayNumbers[1]);
                setDay(arrayNumbers[2]);
                setHour(arrayNumbers[3]);
                setMinute(arrayNumbers[4]);
                setSecond(arrayNumbers[5]);
                setMillisecond(arrayNumbers[6]);
            } else {
                throw new RuntimeException("Wrong argument exception");
            }
        }
    }

    public String calendarInFormat(CustomCalendar form) {
        return form.getYear() + "-" + form.getMonth() + "-" + form.getDay() + " " + form.getHour() + ":" + form.getMinute() + ":" + form.getSecond() + " " + form.getMillisecond();
    }

    public CustomCalendar addDate(CustomCalendar otherDate) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) + unconvertToMillis(calendarInFormat(otherDate)));
        return this;
    }

    public CustomCalendar addDate(String secondFormat) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) + unconvertToMillis(secondFormat));
        return this;
    }

    public CustomCalendar addYears(int years) {
        year += years;
        return this;
    }

    public CustomCalendar addMoths(int months) {
        if ((month + months) > 12) {
            year += ((month + months) / 12);
            month = (month + months) % 12;
        } else {
            month += months;
        }
        return this;
    }

    public CustomCalendar addDays(int days) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) + (days * dayInMillis));
        return this;
    }

    public CustomCalendar addHours(int hours) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) + (hours * hourInMillis));
        return this;
    }

    public CustomCalendar addMinutes(int minutes) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) + (minutes * minuteInMillis));
        return this;
    }

    public CustomCalendar addSeconds(int seconds) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) + (seconds * secondInMillis));
        return this;
    }

    public CustomCalendar addMilliseconds(long milliseconds) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) + (milliseconds));
        return this;
    }

    public CustomCalendar minusDate(CustomCalendar date) {
        converterToDate(Math.abs(unconvertToMillis(calendarInFormat(this)) - unconvertToMillis(calendarInFormat(date))));
        return this;
    }

    public CustomCalendar minusDate(String format) {
        converterToDate(Math.abs(unconvertToMillis(calendarInFormat(this)) - unconvertToMillis(format)));
        return this;
    }

    public CustomCalendar minusYears(int years) {
        year -= years;
        return this;
    }

    public CustomCalendar minusMoths(int months) {
        int yearsAndMonth = ((year * 12) + month) - months;
        year = yearsAndMonth / 12;
        month = yearsAndMonth % 12;
        return this;
    }

    public CustomCalendar minusDays(int days) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) - (days * dayInMillis));
        return this;

    }

    public CustomCalendar minusHours(int hours) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) - (hours * hourInMillis));
        return this;
    }

    public CustomCalendar minusMinutes(int minutes) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) - (minutes * minuteInMillis));
        return this;
    }

    public CustomCalendar minusSeconds(int seconds) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) - (seconds * secondInMillis));
        return this;
    }

    public CustomCalendar minusMilliseconds(long milliseconds) {
        converterToDate(unconvertToMillis(calendarInFormat(this)) - (milliseconds));
        return this;
    }

    public int compareInYears(CustomCalendar first, CustomCalendar second) {
        return Math.abs(first.getYear() - second.getYear());
    }

    public int compareInMoths(CustomCalendar first, CustomCalendar second) {
        return Math.abs(first.getMonth() - second.getMonth());
    }

    public int compareInDays(CustomCalendar first, CustomCalendar second) {
        return Math.abs(first.getDay() - second.getDay());
    }

    public int compareInHours(CustomCalendar first, CustomCalendar second) {
        return Math.abs(first.getHour() - second.getHour());
    }

    public int compareInMinutes(CustomCalendar first, CustomCalendar second) {
        return Math.abs(first.getMinute() - second.getMinute());
    }

    public int compareInSeconds(CustomCalendar first, CustomCalendar second) {
        return Math.abs(first.getSecond() - second.getSecond());
    }

    public int compareInMilliseconds(CustomCalendar first, CustomCalendar second) {
        return Math.abs(first.getMillisecond() - second.getMillisecond());
    }

    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("%d-%02d-%02d %02d:%02d:%02d %03d", year, month, day, hour, minute, second, millisecond);
    }
}