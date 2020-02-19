package com.group7.os.sleepingTA;

import java.util.concurrent.Semaphore;

/**
 * A student assistant is helping students which are in queue, and helps them as long as there
 * students in the queue. If the queue is empty, he falls asleep and a student have to wake him up to get help.
 */
public class StudentAssistant extends Thread {

    /**
     * Queue of students that needs help
     */
    private Queue<Student> queue;

    private boolean isAsleep = false;

    private Student helpingStudent = null;

    public StudentAssistant(Queue<Student> queue) {
        this.queue = queue;
    }

    /**
     * Puts the thread to wait
     */
    private synchronized void takeNap() {
        try {
            this.isAsleep = true;
            while (isAsleep) {
                this.log("Student assistant is sleeping...");
                wait();
            }
        } catch (InterruptedException e) {
        }
    }

    /**
     * Check if student can enter office, if ha has no student and he is asleep.
     *
     * @param student The student want to enter the office
     * @return true if can enter, else false
     */
    public synchronized boolean canEnterOffice(Student student) {
        if (this.helpingStudent != null && !this.isAsleep) return false;
        this.isAsleep = false;
        this.helpingStudent = student;
        notify();
        return true;
    }

    /**
     * Gives help to a student for a random time
     *
     * @param student the student to give help to.
     */
    private void helpStudent(Student student) {
        try {
            this.log("Helping student " + student.getId());
            sleep((int) Math.random() * 7000 + 1000);
            student.giveHelp();
        } catch (InterruptedException e) {
        }finally {
        }
    }

    /**
     * Returns the next person in the queue, or null if there are none
     * @return next student in queue else null
     */
    private Student getNextInQueue() {
        Student student = null;
        if (this.queue.getQueueSize() > 0) {
            student = this.queue.pop();
        }
        return student;
    }

    /**
     * If there we are not currently helping a student, try get a new one from the queue
     */
    private void checkHallway(){
        this.helpingStudent = this.getNextInQueue();
    }

    @Override
    public void run() {
        while (true) {
            while (this.helpingStudent != null) {
                this.helpStudent(this.helpingStudent);
                this.checkHallway();
            }
            this.log("There are no one in the queue...");
            this.takeNap();

        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}
