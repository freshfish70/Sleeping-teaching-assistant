package com.group7.os.sleepingTA;

public class Main {

    public static void main(String[] args) {

//        Student a = new Student();
        StudentQueue<Student> q = new StudentQueue(3);
        StudentAssistant a = new StudentAssistant(q);
        a.start();

        for (int i = 0; i < 10; i++) {
            Student e = new Student(a, q);
            e.id(i);
            e.start();
        }


    }

}
