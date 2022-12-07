package ua.com.alevel;

import ua.com.alevel.test.CountDown;
public class Count{
    public static void main(String[] args) {
    for (int i =0; i<10; i++){
        System.out.println(i);}
 // ua.com.alevel.test.CountDown cnt = new ua.com.alevel.test.CountDown();
        CountDown cnt = new CountDown();
        cnt.count();

    }
}



