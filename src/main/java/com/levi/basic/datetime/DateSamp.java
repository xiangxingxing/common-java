package com.levi.basic.datetime;

import javax.swing.*;
import java.time.Instant;

public class DateSamp {
    public static void main(String[] args) {
        //samp1();
        //samp2();
    }

    public static void samp1(){
        System.out.println(Instant.now());
    }

    public static void samp2(){
        var listener = new TimePrinter();//监听者

        var timer = new Timer(1000, listener);
        timer.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
