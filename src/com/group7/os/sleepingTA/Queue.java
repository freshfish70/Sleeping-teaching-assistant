package com.group7.os.sleepingTA;

public interface Queue<T> {

    boolean push(T element);

    T pop();

    int getQueueSize();

}
