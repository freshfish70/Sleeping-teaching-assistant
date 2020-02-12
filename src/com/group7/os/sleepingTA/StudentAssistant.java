package com.group7.os.sleepingTA;

import java.util.concurrent.Semaphore;

public class StudentAssistant extends Thread {

    private Queue<Student> queue;

    private boolean isAsleep = true;

    private Semaphore available = new Semaphore(1);

    public StudentAssistant(Queue<Student> queue) {
        this.queue = queue;
    }

    private synchronized void takeNap() {
        try {
            this.isAsleep = true;
            while (isAsleep) {
                System.out.println("sleeping");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Wakeup
    public synchronized void wakeIfAsleep(int id) {
        if (this.isAsleep) {
            System.out.println("Student " + id + " woke SA");
            this.isAsleep = false;
            notify();
        }
    }

    private synchronized void helpStudent(Student student) {
        if (available.tryAcquire()) {
            try {
                System.out.println("Helping student" + student.getId());
                sleep((int) Math.random() * 5000 + 100);
                student.giveHelp();
            } catch (InterruptedException e) {
            } finally {
                available.release();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            if (this.queue.getQueueSize() > 0) {
                this.helpStudent(this.queue.pop());
            } else {
                this.takeNap();
            }
        }
    }
}
