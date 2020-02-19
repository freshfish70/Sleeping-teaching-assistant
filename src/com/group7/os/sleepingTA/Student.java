package com.group7.os.sleepingTA;

public class Student extends Thread {

    private final int MINIMUM_WORK_DURATION = 1000;
    private final int MAXIMUM_WORK_DURATION = 3000;

    private StudentAssistant studentAssistant;

    private StudentQueue queue;

    private boolean isWaitingForHelp = false;

    public Student(StudentAssistant studentAssistant, StudentQueue queue) {
        this.studentAssistant = studentAssistant;
        this.queue = queue;
    }

    /**
     * Returns a random time between a minimum and maximum work duration; milliseconds
     *
     * @return value between minimum and maximum work duration; milliseconds
     */
    private int getWorkTime() {
        return (int) (Math.random() * ((MAXIMUM_WORK_DURATION - MINIMUM_WORK_DURATION) + 1)) + MINIMUM_WORK_DURATION;
    }

    /**
     * Sleeps for a random time, and check if needs help.
     */
    private void work() {
        try {
            do {
                int sleeptime = getWorkTime();
                log("Student " + this.getId() + " works for: " + sleeptime + "ms");
                sleep(sleeptime);
            } while (!needHelp());
            log("Student " + this.getId() + " is in need for help...");
        } catch (InterruptedException e) {
        }
    }

    /**
     * Change for needing help
     *
     * @return true of needing help else false
     */
    private boolean needHelp() {
        return Math.random() < .2;
    }

    /**
     * Called by the TA when he has given help to the student.
     */
    public synchronized void giveHelp() {
        log("Student " + this.getId() + " is helped");
        this.isWaitingForHelp = false;
        notifyAll();
    }

    private synchronized void waitingForHelp() {
        try {
            this.isWaitingForHelp = true;
            while (this.isWaitingForHelp) {
                System.out.println(this.getId() + " is waiting for help......................");
                wait();
            }
            log("Student " + this.getId() + " returning to work...");
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {
        while (true) {
            work();
            if (this.studentAssistant.canEnterOffice(this)) {
                System.out.println("TA was not busy, entered office");
                this.waitingForHelp();
            } else if (this.queue.push(this)) {
                log("Student " + this.getId() + " is in queue and waiting for help...");
                this.waitingForHelp();
            }
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}
