package ua.com.alevel;

public class Main {
    public static void main(String[] args) {

        Friends f1 = new Friends("Misha");
        Friends f2 = new Friends("Kolya", null);
        Friends f3 = new Friends("Olya", null);
        Friends f4 = new Friends("Jenya", null);
        Friends f5 = new Friends("Petya", null);
        Friends f6 = new Friends("Vasya", null);

        while(f6.message == null){
            f1.sendMessage(f2);
            f2.sendMessage(f3);
            f3.sendMessage(f4);
            f4.sendMessage(f5);
            f5.sendMessage(f6);}
    }}