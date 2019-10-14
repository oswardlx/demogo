package com.example.util.ThreadTest;

public class Test1 implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public Test1(){}
    public Test1(int countDown){
        this.countDown = countDown;
    }
    public String status(){
        return "#" + id + "(" +(countDown > 0 ? countDown : "Liftoff!") + "), ";
    }
    public static void main(String[] args){

    }

    @Override
    public void run() {
        while (countDown-->0){
            System.out.print(status());
            Thread.yield();
        }
    }
}
