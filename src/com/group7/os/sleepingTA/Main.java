package com.group7.os.sleepingTA;

public class Main {

    /**
     * Hoe many students allowed to wait for help
     */
    private static final int MAX_QUEUE_SIZE = 3;
    /**
     * Number of students to create
     */
    private static final int STUDENTS_IN_CLASS = 10;

    public static void main(String[] args) {

        StudentQueue<Student> queue = new StudentQueue(MAX_QUEUE_SIZE);
        StudentAssistant studentAssistant = new StudentAssistant(queue);
        studentAssistant.start();

        for (int i = 0; i < STUDENTS_IN_CLASS; i++) {
            Student e = new Student(studentAssistant, queue);
            e.start();
        }

    }

}
