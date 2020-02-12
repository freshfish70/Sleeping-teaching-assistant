package com.group7.os.sleepingTA;

public interface Queue<T> {

    void push(T element);

    T pop();

    int getQueueSize();

}
