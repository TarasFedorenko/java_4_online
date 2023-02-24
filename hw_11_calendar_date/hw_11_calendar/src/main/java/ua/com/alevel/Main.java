package ua.com.alevel;

import ua.com.alevel.controller.ControllerCalendar;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ControllerCalendar controllerCalendar = new ControllerCalendar();
        controllerCalendar.run();
    }
}