package sebastian.devmonkey.capstoneproject.other;

import java.util.Random;

public class Arrays {

    private String[] storyTitles = {"A Cold Day", "A Christmas in March", "Story3"};

    private String[] storyIntermediateTitle = {"Intermediate1", "Intermediate2", "Intermediate3"};

    private String[] mQuestion = {
            "question1?",
            "question2?",
            "question3?",
            "question4?",
            "question5?",
            "question6?",
            "question7?",
            "question8?",
            "question9?",
            "question10?",
    };
    private String[][] mChoices = {
            {"a","b","c","d"},
            {"a","b","c","d"},
            {"a","b","c","d"},
            {"a","b","c","d"},
            {"a","b","c","d"},
            {"a","b","c","d"},
            {"a","b","c","d"},
            {"a","b","c","d"},
            {"a","b","c","d"},
            {"a","b","c","d"}
    };
    private String[] mCorrectAnswers = {
            "a",
            "b",
            "b",
            "d",
            "c",
            "a",
            "b",
            "b",
            "c",
            "a"
    };


    public String getQuestion(int a) {
         return mQuestion[a];
    }

    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }


    public String getChoice4(int a) {
        String choice3 = mChoices[a][3];
        return choice3;
    }


    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }


    public  String[] getStoryTitles() {
        return storyTitles.clone();
    }

    public  String[] getStoryIntermediateTitleTitles() {
        return storyIntermediateTitle.clone();
    }

    public int getLength() {
        return mQuestion.length;
    }


}
