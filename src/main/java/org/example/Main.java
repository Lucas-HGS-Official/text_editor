package org.example;

//package com.sun.jna.examples;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Structure;

import java.io.IOException;
import java.lang.classfile.Interfaces;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println("\033[4;44;31mHello and welcome!\033[0m");

//        for (int i = 1; i <= 5; i++) {
//            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//            IO.println("i = " + i);
//        }
        while (true) {
            int key = System.in.read();
            System.out.println((char) key + " (" + key + ")");
        }
    }
}


interface LibC extends Library {

    int SYSTEM_OUT_FD = 0;
    int ISIG = 1, ICANON = 2, ECHO = 10, TCSAFLUSH = 2,
            IXON = 2000, ICRNL = 400, IEXTEN = 100000, OPOST = 1, VMIN = 6, VTIME = 5, TIOCGWINSZ = 0x5413;

    LibC INSTANCE = Native.load("c", LibC.class);

    @Structure.FieldOrder(value = {"c_iflag", "c_oflag", "c_cflag", "c_lflag", "c_cc"})
    class Termios extends Structure {
        public int c_iflag, c_oflag, c_cflag, c_lflag;
        public byte[]  c_cc = new byte[19];

        public Termios() {
        }

        public static Termios of (Termios t) {
            Termios copy = new Termios();
            copy.c_cc = t.c_cc.clone();
            copy.c_oflag = t.c_oflag;
            copy.c_iflag = t.c_iflag;
            copy.c_cflag = t.c_cflag;
            copy.c_lflag = t.c_lflag;

            return copy;
        }
    }

    int tcgetattr(int fd, Termios termios);

    int tcsetattr(int fd, int optional_actions, Termios termios);
}