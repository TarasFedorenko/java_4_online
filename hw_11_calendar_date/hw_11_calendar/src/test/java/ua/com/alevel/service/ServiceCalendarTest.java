package ua.com.alevel.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ua.com.alevel.utils.CustomCalendar;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceCalendarTest {

    private static final ServiceCalendar serviceCalendar = new ServiceCalendar();

    @Test
    public void serviceNow() {
        CustomCalendar custom = CustomCalendar.now();
        Assertions.assertEquals(custom.getYear(), 2023);
        Assertions.assertEquals(custom.getMonth(), 2);
    }

    @Test
    public void serviceAddCalendar() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar otherCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-01-01 02:02:02 022");
        serviceCalendar.serviceAddCalendar(newCalendar, otherCalendar);
        Assertions.assertEquals(newCalendar, testCalendar);
    }

    @Test
    public void serviceAddByFormat() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-01-01 02:02:02 022");
        String format = "1001-01-01 01:01:01 011";
        serviceCalendar.serviceAddByFormat(newCalendar, format);
        Assertions.assertEquals(newCalendar, testCalendar);
    }

    @Test
    public void serviceAddYears() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        serviceCalendar.serviceAddYears(newCalendar, 1);
        Assertions.assertEquals(1002, newCalendar.getYear());
    }

    @Test
    public void serviceAddMonth() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        serviceCalendar.serviceAddMonth(newCalendar, 1);
        Assertions.assertEquals(2, newCalendar.getMonth());
    }

    @Test
    public void serviceAddDays() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        serviceCalendar.serviceAddDays(newCalendar, 1);
        Assertions.assertEquals(2, newCalendar.getDay());
    }

    @Test
    public void serviceAddHours() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        serviceCalendar.serviceAddHours(newCalendar, 1);
        Assertions.assertEquals(2, newCalendar.getHour());
    }

    @Test
    public void serviceAddMinute() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        serviceCalendar.serviceAddMinute(newCalendar, 1);
        Assertions.assertEquals(2, newCalendar.getMinute());
    }

    @Test
    public void serviceAddSeconds() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        serviceCalendar.serviceAddSeconds(newCalendar, 1);
        Assertions.assertEquals(2, newCalendar.getSecond());
    }

    @Test
    public void serviceAddMilliseconds() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        serviceCalendar.serviceAddMilliseconds(newCalendar, 1);
        Assertions.assertEquals(12, newCalendar.getMillisecond());
    }

    @Test
    public void serviceReduceCalendar() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar otherCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-01-01 02:02:02 022");
        serviceCalendar.serviceReduceCalendar(testCalendar, otherCalendar);
        Assertions.assertEquals(newCalendar, testCalendar);
    }

    @Test
    public void serviceReduceByFormat() {
        CustomCalendar newCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-01-01 02:02:02 022");
        String format = "1001-01-01 01:01:01 011";
        serviceCalendar.serviceReduceByFormat(testCalendar, format);
        Assertions.assertEquals(newCalendar, testCalendar);
    }

    @Test
    public void serviceReduceYears() {
        CustomCalendar newCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        serviceCalendar.serviceReduceYears(newCalendar, 1);
        Assertions.assertEquals(2001, newCalendar.getYear());
    }

    @Test
    public void serviceReduceMonths() {
        CustomCalendar newCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        serviceCalendar.serviceReduceMonths(newCalendar, 1);
        Assertions.assertEquals(1, newCalendar.getMonth());
    }

    @Test
    public void serviceReduceDays() {
        CustomCalendar newCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        serviceCalendar.serviceReduceDays(newCalendar, 1);
        Assertions.assertEquals(1, newCalendar.getDay());
    }

    @Test
    public void serviceReduceHours() {
        CustomCalendar newCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        serviceCalendar.serviceReduceHours(newCalendar, 1);
        Assertions.assertEquals(1, newCalendar.getHour());
    }

    @Test
    public void serviceReduceMinutes() {
        CustomCalendar newCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        serviceCalendar.serviceReduceMinutes(newCalendar, 1);
        Assertions.assertEquals(1, newCalendar.getMinute());
    }

    @Test
    public void serviceReduceSeconds() {
        CustomCalendar newCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        serviceCalendar.serviceReduceSeconds(newCalendar, 1);
        Assertions.assertEquals(1, newCalendar.getSecond());
    }

    @Test
    public void serviceReduceMilliseconds() {
        CustomCalendar newCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        serviceCalendar.serviceReduceMilliseconds(newCalendar, 1);
        Assertions.assertEquals(21, newCalendar.getMillisecond());
    }

    @Test
    public void serviceCompareInYears() {
        CustomCalendar otherCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        int testValue = serviceCalendar.serviceCompareInYears(otherCalendar, testCalendar);
        Assertions.assertEquals(testValue, 1001);
    }

    @Test
    public void serviceCompareInMonths() {
        CustomCalendar otherCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        int testValue = serviceCalendar.serviceCompareInMonths(otherCalendar, testCalendar);
        Assertions.assertEquals(testValue, 1);
    }

    @Test
    public void serviceCompareInDays() {
        CustomCalendar otherCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        int testValue = serviceCalendar.serviceCompareInDays(otherCalendar, testCalendar);
        Assertions.assertEquals(testValue, 1);
    }

    @Test
    public void serviceCompareInHours() {
        CustomCalendar otherCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        int testValue = serviceCalendar.serviceCompareInHours(otherCalendar, testCalendar);
        Assertions.assertEquals(testValue, 1);
    }

    @Test
    public void serviceCompareInMinutes() {
        CustomCalendar otherCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        int testValue = serviceCalendar.serviceCompareInMinutes(otherCalendar, testCalendar);
        Assertions.assertEquals(testValue, 1);
    }

    @Test
    public void serviceCompareInSeconds() {
        CustomCalendar otherCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        int testValue = serviceCalendar.serviceCompareInSeconds(otherCalendar, testCalendar);
        Assertions.assertEquals(testValue, 1);
    }

    @Test
    public void serviceCompareInMilliseconds() {
        CustomCalendar otherCalendar = new CustomCalendar("1001-01-01 01:01:01 011");
        CustomCalendar testCalendar = new CustomCalendar("2002-02-02 02:02:02 022");
        int testValue = serviceCalendar.serviceCompareInMilliseconds(otherCalendar, testCalendar);
        Assertions.assertEquals(testValue, 11);
    }
}
