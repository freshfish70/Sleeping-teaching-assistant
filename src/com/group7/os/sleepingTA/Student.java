package com.group7.os.sleepingTA;

import java.util.Random;

public class Student extends Thread {

    //Wake SA if is sleeping

    //Go back to work if queue is full

    //Wait in queue if the que is not full

    //Go back to work when has gotten help

    private StudentAssistant studentAssistant;

    private StudentQueue queue;

    private boolean isWaitingForHelp = false;

    public Student(StudentAssistant studentAssistant, StudentQueue queue) {
        this.studentAssistant = studentAssistant;
        this.queue = queue;

    }

    private int random() {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 6000 + 1000;
        int randomInt = (int) randomDouble;
        return randomInt;
    }

    private synchronized void work() {
        try {
            int sleeptime = random();
            System.out.println("Student " + this.getId() + "works for: " + sleeptime);
            sleep(sleeptime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void giveHelp() {
        System.out.println("Student " + this.getId() + " is helped");
        this.isWaitingForHelp = false;
        notifyAll();
    }

    private synchronized void waitingForHelp() {
        try {
            System.out.println("Student " + this.getId() + " is waiting for help...");
            while (this.isWaitingForHelp) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Student " + this.getId() + " stopped waiting for help...");

    }

    private synchronized void process() {
        while (true) {
            this.studentAssistant.wakeIfAsleep((int)this.getId());

            if (!this.queue.isFull()) {
                this.queue.push(this);
                this.isWaitingForHelp = true;
                this.waitingForHelp();
            }
            work();
        }
    }

    @Override
    public void run() {
        this.process();
    }
}
