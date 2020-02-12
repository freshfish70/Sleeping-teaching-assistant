package com.group7.os.sleepingTA;


import java.util.ArrayList;
import java.util.List;

public class StudentQueue<Student> implements Queue<Student> {

    private List<Student> queue;

    private int maxQueueSize;

    public StudentQueue(int maxQueueSize) {
        this.queue = new ArrayList<>();
        if (maxQueueSize < 1) maxQueueSize = 1;
        this.maxQueueSize = maxQueueSize;
    }

    /**
     * Pushes a student to the end of the queue.
     * @param element student to add to queue
     */
    @Override
    public synchronized void push(Student element) {
        if (this.queue.size() <= this.maxQueueSize) {
            this.queue.add(element);
        }
    }

    /**
     * Removes the first element in the queue of the queue, and returns it.
     * @return
     */
    @Override
    public synchronized Student pop() {
        return queue.remove(0);
    }

    /**
     * Returns the size of the queue
     * @return size of the queue
     */
    @Override
    public synchronized int getQueueSize() {
        return this.queue.size();
    }

    /**
     * Returns true of queue is full else false
     * @return true if full else false
     */
    public synchronized boolean isFull(){
        return this.queue.size() >= this.maxQueueSize;
    }

}

