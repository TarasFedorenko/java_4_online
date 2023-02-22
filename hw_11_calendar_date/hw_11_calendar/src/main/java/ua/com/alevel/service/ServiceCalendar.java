package ua.com.alevel.service;

import ua.com.alevel.utils.CustomCalendar;

public class ServiceCalendar {

    CustomCalendar customCalendar = new CustomCalendar();

    public CustomCalendar serviceNow() {
        return CustomCalendar.now();
    }

    public long serviceGetTimeMillis() {
        return customCalendar.getTimeMillis();
    }

    public CustomCalendar serviceAddCalendar(CustomCalendar newCalendar, CustomCalendar otherCalendar) {
        return newCalendar.addDate(otherCalendar);
    }

    public CustomCalendar serviceAddByFormat(CustomCalendar firstFormat, String secondFormat) {
        return firstFormat.addDate(secondFormat);
    }

    public CustomCalendar serviceAddYears(CustomCalendar startCalendar, int years) {
        return startCalendar.addYears(years);
    }

    public CustomCalendar serviceAddMonth(CustomCalendar startCalendar, int months) {
        return startCalendar.addMoths(months);
    }

    public CustomCalendar serviceAddDays(CustomCalendar startCalendar, int days) {
        return startCalendar.addDays(days);
    }

    public CustomCalendar serviceAddHours(CustomCalendar startCalendar, int hours) {
        return startCalendar.addHours(hours);
    }

    public CustomCalendar serviceAddMinute(CustomCalendar startCalendar, int minutes) {
        return startCalendar.addMinutes(minutes);
    }

    public CustomCalendar serviceAddSeconds(CustomCalendar startCalendar, int seconds) {
        return startCalendar.addSeconds(seconds);
    }

    public CustomCalendar serviceAddMilliseconds(CustomCalendar startCalendar, long millis) {
        return startCalendar.addMilliseconds(millis);
    }

    public CustomCalendar serviceReduceCalendar(CustomCalendar newCalendar, CustomCalendar otherCalendar) {
        return newCalendar.minusDate(otherCalendar);
    }

    public CustomCalendar serviceReduceByFormat(CustomCalendar firstFormat, String secondFormat) {
        return firstFormat.minusDate(secondFormat);
    }

    public CustomCalendar serviceReduceYears(CustomCalendar startCalendar, int years) {
        return startCalendar.minusYears(years);
    }

    public CustomCalendar serviceReduceMonths(CustomCalendar startCalendar, int months) {
        return startCalendar.minusMoths(months);
    }

    public CustomCalendar serviceReduceDays(CustomCalendar startCalendar, int days) {
        return startCalendar.minusDays(days);
    }

    public CustomCalendar serviceReduceHours(CustomCalendar startCalendar, int hours) {
        return startCalendar.minusHours(hours);
    }

    public CustomCalendar serviceReduceMinutes(CustomCalendar startCalendar, int minutes) {
        return startCalendar.minusMinutes(minutes);
    }

    public CustomCalendar serviceReduceSeconds(CustomCalendar startCalendar, int seconds) {
        return startCalendar.minusSeconds(seconds);
    }

    public CustomCalendar serviceReduceMilliseconds(CustomCalendar startCalendar, long millis) {
        return startCalendar.minusMilliseconds(millis);
    }

    public int serviceCompareInYears(CustomCalendar first, CustomCalendar second) {
        return customCalendar.compareInYears(first, second);
    }

    public int serviceCompareInMonths(CustomCalendar first, CustomCalendar second) {
        return customCalendar.compareInMoths(first, second);
    }

    public int serviceCompareInDays(CustomCalendar first, CustomCalendar second) {
        return customCalendar.compareInDays(first, second);
    }

    public int serviceCompareInHours(CustomCalendar first, CustomCalendar second) {
        return customCalendar.compareInHours(first, second);
    }

    public int serviceCompareInMinutes(CustomCalendar first, CustomCalendar second) {
        return customCalendar.compareInMinutes(first, second);
    }

    public int serviceCompareInSeconds(CustomCalendar first, CustomCalendar second) {
        return customCalendar.compareInSeconds(first, second);
    }

    public int serviceCompareInMilliseconds(CustomCalendar first, CustomCalendar second) {
        return customCalendar.compareInMilliseconds(first, second);
    }
}