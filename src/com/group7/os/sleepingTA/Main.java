package com.group7.os.sleepingTA;

import java.util.ArrayList;
import java.util.List;

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

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < STUDENTS_IN_CLASS; i++) {
            Student student = new Student(studentAssistant, queue);
            students.add(student);
            student.start();
        }

        for (Student s : students) {
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            studentAssistant.leave();
            studentAssistant.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ALL STUDENTS ARE GONE");
    }

}
