package org.example;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    private String username;
    private String password;
    String name;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public boolean authenticate(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    public void updatePassword(String newPassword) {
        password = newPassword;
    }

    public void updateProfile(String newName) {
        name = newName;
    }
}

class OnlineExam {
    private User currentUser;
    private int score;
    private int totalQuestions;

    public OnlineExam(User user) {
        this.currentUser = user;
        this.score = 0;
        this.totalQuestions = 5; // Example: 5 questions in the exam.
    }

    public void startExam() {
        System.out.println("Welcome, " + currentUser.name + " !");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int timeLeft = 300; // 5 minutes (300 seconds) for the exam

            @Override
            public void run() {
                if (timeLeft <= 0) {
                    timer.cancel();
                    submitExam();
                }
                System.out.println("Time Left: " + timeLeft + " seconds");
                timeLeft--;
            }
        };
        timer.schedule(task, 1000, 1000); // Start the timer

        // Simulate exam questions
        for (int questionNum = 1; questionNum <= totalQuestions; questionNum++) {
            System.out.println("Question " + questionNum + ": What is 2 + 2 ?");
            int userAnswer = getUserAnswer();
            if (userAnswer == 4) {
                score++;
            }
        }
        submitExam();
    }

    private int getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your answer: ");
        return scanner.nextInt();
    }

    private void submitExam() {
        System.out.println("Exam submitted.");
        System.out.println("Your Score: " + score + " out of " + totalQuestions);
        System.exit(0);
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a user
        User user = new User("oasisinfobyte123", "password123", "oasis infobyte");

        // Simulate login
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String enteredUsername = scanner.next();
        System.out.print("Enter your password: ");
        String enteredPassword = scanner.next();

        if (user.authenticate(enteredPassword)) {
            System.out.println("Login successful.");
            OnlineExam exam = new OnlineExam(user);
            exam.startExam();
        } else {
            System.out.println("Login failed. Incorrect password.");
        }
    }
}
