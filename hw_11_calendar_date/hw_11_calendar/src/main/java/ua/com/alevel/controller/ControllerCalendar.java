package ua.com.alevel.controller;

import ua.com.alevel.utils.CustomCalendar;
import ua.com.alevel.service.ServiceCalendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerCalendar {
    ServiceCalendar service = new ServiceCalendar();
    public void run() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("MAIN MENU!!!");
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            calendarMenu(bf, select);
        }
    }

    public void menu() {
        System.out.println("_____________________________________________");
        System.out.println("If you want to know what time is it now press - 1");
        System.out.println("If you want to know how much millisecond passed from 1st January 00 press - 2 ");
        System.out.println("If you want to change date and ADD some time interval press - 3");
        System.out.println("If you want to change date and REDUCE some time interval press - 4");
        System.out.println("If you want to COMPARE two calendars by time interval press -5");
        System.out.println("If you want to QUIT press - 0 ");
        System.out.println("_____________________________________________");
        System.out.println();
    }

    private void calendarMenu(BufferedReader bf, String select) throws IOException {
        switch (select) {
            case "1":
                nowTime();
                break;
            case "2":
                nowTimeInMillis();
                break;
            case "3":
                addSelect(bf);
                break;
            case "4":
                reduceSelect(bf);
                break;
            case "5":
                compareSelect(bf);
                break;
            case "0":
                quit();
                break;
        }
        menu();
    }

    public void nowTime() {
        System.out.println("Current time is -> " + service.serviceNow());
    }

    public void nowTimeInMillis() {
        System.out.println(service.serviceGetTimeMillis() + " ms passed from 1st January of 00 year ");
    }

    public void addMenu() {
        System.out.println("_____________________________________________");
        System.out.println("If you want to add two date press - 1 ");
        System.out.println("If you want to add two date by format press -2 ");
        System.out.println("If you want to add years to your date press -3 ");
        System.out.println("If you want to add months to your date press -4 ");
        System.out.println("If you want to add days to your date press -5 ");
        System.out.println("If you want to add hours to your date press -6 ");
        System.out.println("If you want to add minutes to your date press -7 ");
        System.out.println("If you want to add seconds to your date press -8 ");
        System.out.println("If you want to add milliseconds to your date press -9 ");
        System.out.println("Back to MAIN MENU press -0 ");
    }

    public void addSelect(BufferedReader bf) throws IOException {
        addMenu();
        switch (bf.readLine()) {
            case "1":
                addDate(bf);
                break;
            case "2":
                addFormat(bf);
                break;
            case "3":
                addYears(bf);
                break;
            case "4":
                addMonth(bf);
                break;
            case "5":
                addDays(bf);
                break;
            case "6":
                addHours(bf);
                break;
            case "7":
                addMinutes(bf);
                break;
            case "8":
                addSeconds(bf);
                break;
            case "9":
                addMilliseconds(bf);
                break;
            case "0":
                run();
                break;
        }
    }

    public void addDate(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in format 00-00-00 00:00:00 000");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that you want to add to first in format 00-00-00 00:00:00 000");
        String secondDate = bf.readLine();
        System.out.println(service.serviceAddCalendar(new CustomCalendar(firstDate), new CustomCalendar(secondDate)));
    }

    public void addFormat(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in one of proposed format ");
        System.out.println("This one 00-00-00 00:00:00 000");
        System.out.println("This one 00-00-00 00:00:00 ");
        System.out.println("This one 00-00-00 00:00 ");
        System.out.println("or this  00-00-00  ");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date in same format ");
        String secondDate = bf.readLine();
        System.out.println(service.serviceAddByFormat(new CustomCalendar(firstDate), secondDate));
    }

    public void addYears(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much year you want to add");
        Integer years = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceAddYears(new CustomCalendar(startDate), years));
    }

    public void addMonth(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much months you want to add");
        Integer months = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceAddMonth(new CustomCalendar(startDate), months));
    }

    public void addDays(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much days you want to add");
        Integer days = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceAddDays(new CustomCalendar(startDate), days));
    }

    public void addHours(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much hours you want to add");
        Integer hours = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceAddHours(new CustomCalendar(startDate), hours));
    }

    public void addMinutes(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much minutes you want to add");
        Integer minutes = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceAddMinute(new CustomCalendar(startDate), minutes));
    }

    public void addSeconds(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much seconds you want to add");
        Integer seconds = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceAddSeconds(new CustomCalendar(startDate), seconds));
    }

    public void addMilliseconds(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much milliseconds you want to add");
        Long milliseconds = Long.parseLong(bf.readLine());
        System.out.println(service.serviceAddMilliseconds(new CustomCalendar(startDate), milliseconds));
    }

    public void reduceMenu() {
        System.out.println("_____________________________________________");
        System.out.println("If you want to reduce full date press - 1 ");
        System.out.println("If you want to reduce date by format press -2 ");
        System.out.println("If you want to reduce years to your date press -3 ");
        System.out.println("If you want to reduce months to your date press -4 ");
        System.out.println("If you want to reduce days to your date press -5 ");
        System.out.println("If you want to reduce hours to your date press -6 ");
        System.out.println("If you want to reduce minutes to your date press -7 ");
        System.out.println("If you want to reduce seconds to your date press -8 ");
        System.out.println("If you want to reduce milliseconds to your date press -9 ");
        System.out.println("Back to MAIN MENU press -0 ");
        System.out.println("_____________________________________________");
    }

    public void reduceSelect(BufferedReader bf) throws IOException {
        reduceMenu();
        switch (bf.readLine()) {
            case "1":
                reduceDate(bf);
                break;
            case "2":
                reduceFormat(bf);
                break;
            case "3":
                reduceYears(bf);
                break;
            case "4":
                reduceMonth(bf);
                break;
            case "5":
                reduceDays(bf);
                break;
            case "6":
                reduceHours(bf);
                break;
            case "7":
                reduceMinutes(bf);
                break;
            case "8":
                reduceSeconds(bf);
                break;
            case "9":
                reduceMilliseconds(bf);
                break;
            case "0":
                run();
                break;
        }
    }

    public void reduceDate(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in format 00-00-00 00:00:00 000");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that you want to reduce to first in format 00-00-00 00:00:00 000");
        String secondDate = bf.readLine();
        System.out.println(service.serviceReduceCalendar(new CustomCalendar(firstDate), new CustomCalendar(secondDate)));
    }

    public void reduceFormat(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in one of proposed format ");
        System.out.println("This one 00-00-00 00:00:00 000");
        System.out.println("This one 00-00-00 00:00:00 ");
        System.out.println("This one 00-00-00 00:00 ");
        System.out.println("or this  00-00-00  ");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that yo want to reduce in same format ");
        String secondDate = bf.readLine();
        System.out.println(service.serviceReduceByFormat(new CustomCalendar(firstDate), secondDate));
    }

    public void reduceYears(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much year you want to reduce");
        Integer years = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceReduceYears(new CustomCalendar(startDate), years));
    }

    public void reduceMonth(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much months you want to reduce");
        Integer months = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceReduceMonths(new CustomCalendar(startDate), months));
    }

    public void reduceDays(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much days you want to reduce");
        Integer days = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceReduceDays(new CustomCalendar(startDate), days));
    }

    public void reduceHours(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much hours you want to reduce");
        Integer hours = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceReduceHours(new CustomCalendar(startDate), hours));
    }

    public void reduceMinutes(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much minutes you want to reduce");
        Integer minutes = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceReduceMinutes(new CustomCalendar(startDate), minutes));
    }

    public void reduceSeconds(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much seconds you want to reduce");
        Integer seconds = Integer.parseInt(bf.readLine());
        System.out.println(service.serviceReduceSeconds(new CustomCalendar(startDate), seconds));
    }

    public void reduceMilliseconds(BufferedReader bf) throws IOException {
        System.out.println("Please enter start date ");
        String startDate = bf.readLine();
        System.out.println("Please enter how much milliseconds you want to reduce");
        Long milliseconds = Long.parseLong(bf.readLine());
        System.out.println(service.serviceReduceMilliseconds(new CustomCalendar(startDate), milliseconds));
    }

    public void compareMenu() {
        System.out.println("_____________________________________________");
        System.out.println("If you want to compare years of two date press -1 ");
        System.out.println("If you want to compare months of two date press -2 ");
        System.out.println("If you want to compare days of two date press -3 ");
        System.out.println("If you want to compare hours of two date press -4 ");
        System.out.println("If you want to compare minutes of two date press -5 ");
        System.out.println("If you want to compare seconds of two date press -6 ");
        System.out.println("If you want to compare milliseconds of two date press -7 ");
        System.out.println("Back to MAIN MENU press -0 ");
        System.out.println("_____________________________________________");
    }

    public void compareSelect(BufferedReader bf) throws IOException {
        compareMenu();
        switch (bf.readLine()) {
            case "1":
                compareYears(bf);
                break;
            case "2":
                compareMonth(bf);
                break;
            case "3":
                compareDays(bf);
                break;
            case "4":
                compareHours(bf);
                break;
            case "5":
                compareMinutes(bf);
                break;
            case "6":
                compareSeconds(bf);
                break;
            case "7":
                compareMilliseconds(bf);
                break;
            case "0":
                run();
                break;
        }
    }

    public void compareYears(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in format 00-00-00 00:00:00 000");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that you want to compare with first in format 00-00-00 00:00:00 000");
        String secondDate = bf.readLine();
        System.out.println(service.serviceCompareInYears(new CustomCalendar(firstDate), new CustomCalendar(secondDate)));
    }

    public void compareMonth(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in format 00-00-00 00:00:00 000");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that you want to compare with first in format 00-00-00 00:00:00 000");
        String secondDate = bf.readLine();
        System.out.println(service.serviceCompareInMonths(new CustomCalendar(firstDate), new CustomCalendar(secondDate)));
    }

    public void compareDays(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in format 00-00-00 00:00:00 000");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that you want to compare with first in format 00-00-00 00:00:00 000");
        String secondDate = bf.readLine();
        System.out.println(service.serviceCompareInDays(new CustomCalendar(firstDate), new CustomCalendar(secondDate)));
    }

    public void compareHours(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in format 00-00-00 00:00:00 000");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that you want to compare with first in format 00-00-00 00:00:00 000");
        String secondDate = bf.readLine();
        System.out.println(service.serviceCompareInHours(new CustomCalendar(firstDate), new CustomCalendar(secondDate)));
    }

    public void compareMinutes(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in format 00-00-00 00:00:00 000");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that you want to compare with first in format 00-00-00 00:00:00 000");
        String secondDate = bf.readLine();
        System.out.println(service.serviceCompareInMinutes(new CustomCalendar(firstDate), new CustomCalendar(secondDate)));
    }

    public void compareSeconds(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in format 00-00-00 00:00:00 000");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that you want to compare with first in format 00-00-00 00:00:00 000");
        String secondDate = bf.readLine();
        System.out.println(service.serviceCompareInSeconds(new CustomCalendar(firstDate), new CustomCalendar(secondDate)));
    }

    public void compareMilliseconds(BufferedReader bf) throws IOException {
        System.out.println("Please enter first date in format 00-00-00 00:00:00 000");
        String firstDate = bf.readLine();
        System.out.println("Please enter second date that you want to compare with first in format 00-00-00 00:00:00 000");
        String secondDate = bf.readLine();
        System.out.println(service.serviceCompareInMilliseconds(new CustomCalendar(firstDate), new CustomCalendar(secondDate)));
    }

    private void quit() {
        System.exit(0);
    }
}