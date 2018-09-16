package sebastian.devmonkey.capstoneproject.other;

public class QuestionAndAnswer {

    //easy
    private String[] question1 = {
            "What is the weather like today?",
            "Sam is hot, but Sam wants to be..",
            "How can Sam get cool?",
            "Why does Sam talk to Andrea?",
            "Is the pool open?"
    };



    private String[] question2 = {
            "What is at the door of the house?",
            "What happens at the end of the story?",
            "Is the dog dry or wet?",
            "How does the wet dog look?",
            "What day of the week is it in the story?"

    };


    private String[][] choices = {
            {"It is cold", "It is cool", "It is warm", "It is hot"},
            {"Cold", "Cool", "Warm", "Hot"},
            {"He can go to the library", "He can go to the pool", "He can go to school", "He can go to work"},
            {"Because she is his friend", "Because she knows his sister", "Because Sam needs to know what time it is", "Because Sam wants to know if the pool is open"},
            {"Yes, the pool is open", "No, the pool is not open", "None of the choices", "Both A and B"}
    };


    private String[][] choices2 = {
            {"A Man", "A Cat", "A Dog", "A Snake"},
            {"Anna keeps the dog", "Anna takes the dog to the hospital", "Anna’s mom feeds the dog", "Anna dries the dog with a towel"},
            {"The dog is dry", "The dog is wet", "Maybe", "None of the above"},
            {"Strong", "Cute", "Small", "Both B and C", "None of the above"},
            {"Monday", "Tuesday", "Wednesday", "Thursday"}
    };


    private String[] answer1 = {
            "It is hot",
            "Cool",
            "He can go to the pool",
            "Because Sam wants to know if the pool is open",
            "Yes, the pool is open"
    };


    private String[] answer2 = {
            "A Dog",
            "Anna keeps the dog",
            "The dog is wet",
            "Both B and C",
            "Thursday"
    };

    public String getQuestion(int a, int id) {

        if (id == 0) {
            return question1[a];
        } else if (id == 1) {
            return question2[a];
        }

        return null;
    }

    public String getCorrectAnswer(int a, int id) {

        if (id == 0){
            return answer1[a];
        } else if (id == 1) {
            return answer2[a];
        }
        return null;
    }




    public String getChoice1(int a, int id) {

        if (id == 0) {
            return choices[a][0];
        } else if (id == 1) {
            return choices2[a][0];
        }

        return null;
    }


    public String getChoice2(int a, int id) {
        if (id == 0) {
            return choices[a][1];
        } else if (id == 1) {
            return choices2[a][1];
        }
        return null;
    }

    public String getChoice3(int a, int id) {
        if (id == 0) {
            return choices[a][2];
        } else if (id == 1) {
            return choices2[a][2];
        }
        return null;
    }


    public String getChoice4(int a, int id) {
        if (id == 0) {
            return choices[a][3];
        } else if (id == 1) {
            return choices2[a][3];
        }
        return null;
    }





    // intermediate

    private String[] intermQuestion1 = {
            "When does Griffin take violin lessons?",
            "When does Mr. Thomas tell Griffin to practice?",
            "When does Griffin practice the violin?",
            "How does Griffin feel about the violin?",
            "What does Mr. Thomas teach Griffin?"
    };

    private String[] intermAnswer1 = {
            "On Tuesdays after school",
            "Every day",
            "Most days",
            "He loves it",
            "To play the violin"
    };

    public String IntermGetQuestion1(int a) {
        return intermQuestion1[a];
    }

    public String getIntermCorrectAnswer1(int a) {
        String answer = intermAnswer1[a];
        return answer;
    }



    private String[][] IntermChoices = {
            {"On Tuesdays after soccer", "On Thursdays after soccer", "On Tuesdays after school", "On Thursdays after school"},
            {"Every day", "Most days", "Only on Tuesday", "When he wants to"},
            {"Every day", "Most days", "Never", "Only on Tuesday"},
            {"He loves it", "He thinks it is hard", "He hates to practice", "He does not like the sound"},
            {"To play soccer", "To fix old clocks", "To play the violin", "To build birdhouses"}
    };



    public String IntermGetChoice1(int a) {
        String choice0 = IntermChoices[a][0];
        return choice0;
    }


    public String IntermGetChoice2(int a) {
        String choice1 = IntermChoices[a][1];
        return choice1;
    }

    public String IntermGetChoice3(int a) {
        String choice2 = IntermChoices[a][2];
        return choice2;
    }


    public String IntermGetChoice4(int a) {
        String choice3 = IntermChoices[a][3];
        return choice3;
    }



}
