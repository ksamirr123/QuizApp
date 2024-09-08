package com.example.quizapp;

public class questionsanswer {
    //change1

    // Array of questions
    public static String[] question = {
            "What is 10 + 26?",
            "Who invented the telephone?",
            "What is 12 * 9?",
            "Who is the founder of SpaceX?",
            "Which of the following is an example of system software?"
    };

    // Array of answer choices for each question
    public static String[][] choices = {
            {"32", "42", "36", "38"},
            {"Graham Bell", "Einstein", "Edison", "None of the above"},
            {"96", "84", "102", "108"},
            {"Jeff Bezos", "Elon Musk", "Steve Jobs", "Bill Gates"},
            {"Windows", "Linux", "MacOS", "All of the above"}
    };

    // Array of correct answers for each question
    public static String[] correctAnswers = {
            "36",
            "Graham Bell",
            "108",
            "Elon Musk",
            "All of the above"
    };
}
