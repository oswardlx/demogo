package com.example.util.ThreadTest;

public class MyThread {
    public static void main(String[] args){
//        Test1 launch = new Test1();
//        launch.run();
//        Thread t = new Thread(new Test1());
//        t.start();
//        System.out.println("Waiting for LiftOff");
        for(int i = 0;  i < 5; i++){
            new Thread(new Test1()).start();
        }
//        System.out.println("Waiting for LiftOff2");

    }
}
