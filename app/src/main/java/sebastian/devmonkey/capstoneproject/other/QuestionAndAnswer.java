package sebastian.devmonkey.capstoneproject.other;

public class QuestionAndAnswer {

    //easy
    private String[] question1 = {
            "If the weather is frigid, it is very ?",
            "In what city does this story take place?",
            "Dr. James doesn’t stay home because",
            "What could Dr. James have put on his neck to keep it warm?",
            "Given what is said in the story, what is probably true about Miami?"
    };



    private String[] question2 = {
            "After Clifton's letter is read to the family, the narrator’s mood shifts from",
            "In the middle of the story, we learn that the mother is an optimist. This means she",
            "Which of the following words best describes the work the sisters did to help keep the tree looking pretty?",
            "What other title would best fit this passage?",
            "How does Clifton seem to feel when he sees the Christmas tree?"

    };




    private String[] answer1 = {
            "Cold",
            "New York",
            "There are sick people waiting for him",
            "A scarf",
            "It's a warm place"
    };


    private String[] answer2 = {
            "Excited to disappointed",
            "Maintains a positive viewpoint",
            "Teamwork",
            "Granting Clifton&'s Wish",
            "Appreciative"
    };

    public String getQuestion1(int a) {
        return question1[a];
    }
    public String getQuestion2(int a) {
        return question2[a];
    }

    public String getCorrectAnswer1(int a) {
        String answer = answer1[a];
        return answer;
    }

    public String getCorrectAnswer2(int a) {
        String answer = answer2[a];
        return answer;
    }






    private String[][] choices = {
            {"Cold", "Rainy", "Windy", "Slippery"},
            {"Florida", "Miami", "Pennsylvania", "New York"},
            {"There are sick people waiting for him", "He listens to the weatherman", "He has to clear snow off his car.", "He must help the people in the accident"},
            {"Gloves", "A scarf", "Another hat", "A Jacket"},
            {"It's a warm place", "It's a rainy place", "It's a windy place", "People drive slowly there"}
    };


    private String[][] choices2 = {
            {"Arrogant to humble", "Sad to joyful", "Uncertain to clear", "Excited to disappointed"},
            {"Is not easily upset", "Upholds high standards", "Maintains a positive viewpoint", "considers all outcomes before making a decision"},
            {"Miracle", "Disaster", "Drudgery", "Teamwork"},
            {"Flying Home", "A Christmas War", "Granting Clifton&'s Wish", "The Disappointing Letter"},
            {"Appreciative", "Disappointed", "Estranged", "Remorseful"}
    };



    public String getChoice1(int a) {
        String choice0 = choices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = choices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = choices[a][2];
        return choice2;
    }


    public String getChoice4(int a) {
        String choice3 = choices[a][3];
        return choice3;
    }


    //

    public String getChoice5(int a) {
        String choice3 = choices2[a][0];
        return choice3;
    }

    public String getChoice6(int a) {
        String choice3 = choices2[a][1];
        return choice3;
    }

    public String getChoice7(int a) {
        String choice3 = choices2[a][2];
        return choice3;
    }

    public String getChoice8(int a) {
        String choice3 = choices2[a][3];
        return choice3;
    }
}
