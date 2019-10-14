package com.example.util.ThreadTest;

public class Test2 implements Runnable {
    public Test2() {

    }

    @Override
    public void run() {
        System.out.println("11");
        Thread.yield();
        System.out.println("22");
        Thread.yield();
        System.out.println("33");
        Thread.yield();
    }
}
