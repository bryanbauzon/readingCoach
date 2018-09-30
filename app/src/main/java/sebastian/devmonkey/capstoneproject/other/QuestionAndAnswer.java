package sebastian.devmonkey.capstoneproject.other;

public class QuestionAndAnswer {

    private String[] question1 = {
            "The race in the story was the",
            "The story an then the people were sure DePalma would win because he had been..",
            "When DePalma's car stopped, he was",
            "DePalma's car stopped because",
            "When the car was whizzed by DePalma as he was pushing his car, he"
    };

    private String[] question2 = {
            "What is the weather like today?",
            "Sam is hot, but Sam wants to be..",
            "How can Sam get cool?",
            "Why does Sam talk to Andrea?",
            "Is the pool open?"
    };

    

    private String[][] choices = {
            {"Monaco Grand Prix", "Canadian Grand Prix", "Indianapolis 500", "Grand National Championship"},
            {"leading for most of the race", "was considered the best driver in the world", "had the best car in the world", "All of the above"},
            {"just starting the race", "just crossing the finish line", "going into the last lap", "None of the above"},
            {"it got too hot", "ran out of gas", "lost a tire", "the story does not say"},
            {"waved a flag", "moved off the speedway", "stood still", "None of the above"}
    };


    private String[][] choices2 = {
            {"It is cold", "It is cool", "It is warm", "It is hot"},
            {"Cold", "Cool", "Warm", "Hot"},
            {"He can go to the library", "He can go to the pool", "He can go to school", "He can go to work"},
            {"Because she is his friend", "Because she knows his sister", "Because Sam needs to know what time it is", "Because Sam wants to know if the pool is open"},
            {"Yes, the pool is open", "No, the pool is not open", "None of the choices", "Both A and B"}
    };



    private String[] answer1 = {
            "Indianapolis 500",
            "leading for most of the race",
            "going into the last lap",
            "the story does not say",
            "None of the above"
    };


    private String[] answer2 = {
            "It is hot",
            "Cool",
            "He can go to the pool",
            "Because Sam wants to know if the pool is open",
            "Yes, the pool is open"
    };


    public String getQuestion(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return question1[a];
            } else if (id == 1) {
                return question2[a];
            }

        }

        return null;
    }

    public String getCorrectAnswer(int a, int id, String level) {

        if (level.equals("easy")){
            if (id == 0){
                return answer1[a];
            } else if (id == 1) {
                return answer2[a];
            }
//            } else if( id == 2) {
//                return answer3[a];
//            }
//            } else if (id == 3) {
//                return answer4[a];
//            }  else if (id == 4) {
//                return answer5[a];
//            } else if (id == 5) {
//                return answer6[a];
//            } else if (id == 6) {
//                return answer7[a];
//            } else if (id == 7) {
//                return answer8[a];
//            } else if (id == 8) {
//                return null;
//            } else if (id == 9) {
//                return answer10[a];
//            } else if (id == 10) {
//                return answer11[a];
//            } else if (id == 11) {
//                return answer12[a];
//            } else if (id == 12) {
//                return answer13[a];
//            } else if (id == 13) {
//                return answer14[a];
//            }
        }

        return null;
    }


    public String getChoice1(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return choices[a][0];
            } else if (id == 1) {
                return choices2[a][0];
            }
//            } else if (id == 2) {
//                return choices3[a][0];
//            } else if (id == 3) {
//                return choices4[a][0];
//            } else if (id == 4) {
//                return choices5[a][0];
//            } else if (id == 5) {
//                return choices6[a][0];
//            } else if (id == 6) {
//                return choices7[a][0];
//            } else if (id == 7) {
//                return choices8[a][0];
//            } else if (id == 8) {
//                return null;
//            } else if (id == 9) {
//                return choices10[a][0];
//            } else if (id == 10) {
//                return choices11[a][0];
//            } else if (id == 11) {
//                return choices12[a][0];
//            } else if (id == 12) {
//                return choices13[a][0];
//            } else if (id == 13) {
//                return choices14[a][0];
//            }
        }
        return null;
    }


    public String getChoice2(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return choices[a][1];
            } else if (id == 1) {
                return choices2[a][1];
            }
//            } else if (id == 2) {
//                return choices3[a][1];
//            } else if (id == 3) {
//                return choices4[a][1];
//            } else if (id == 4) {
//                return choices5[a][1];
//            } else if (id == 5) {
//                return choices6[a][1];
//            } else if (id == 6) {
//                return choices7[a][1];
//            } else if (id == 7) {
//                return choices8[a][1];
//            } else if (id == 8) {
//                return null;
//            } else if (id == 9) {
//                return choices10[a][1];
//            } else if (id == 10) {
//                return choices11[a][1];
//            } else if (id == 11) {
//                return choices12[a][1];
//            } else if (id == 12) {
//                return choices13[a][1];
//            } else if (id == 13) {
//                return choices14[a][1];
//            }
        }
        return null;
    }

    public String getChoice3(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return choices[a][2];
            } else if (id == 1) {
                return choices2[a][2];
            }
//            } else if (id == 2) {
//                return choices3[a][2];
//            }
//            } else if (id == 3) {
//                return choices4[a][2];
//            } else if (id == 4) {
//                return choices5[a][2];
//            } else if (id == 5) {
//                return choices6[a][2];
//            } else if (id == 6) {
//                return choices7[a][2];
//            } else if (id == 7) {
//                return choices8[a][2];
//            } else if (id == 8) {
//                return null;
//            } else if (id == 9) {
//                return choices10[a][2];
//            } else if (id == 10) {
//                return choices11[a][2];
//            } else if (id == 11) {
//                return choices12[a][2];
//            } else if (id == 12) {
//                return choices13[a][2];
//            } else if (id == 13) {
//                return choices14[a][2];
//            }
        }
        return null;
    }


    public String getChoice4(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return choices[a][3];
            } else if (id == 1) {
                return choices2[a][3];
            }
//            } else if (id == 2) {
//                return choices3[a][3];
//            }
//            } else if (id == 3) {
//                return choices4[a][3];
//            } else if (id == 4) {
//                return choices5[a][3];
//            } else if (id == 5) {
//                return choices6[a][3];
//            } else if (id == 6) {
//                return choices7[a][3];
//            } else if (id == 7) {
//                return choices8[a][3];
//            } else if (id == 8) {
//                return null;
//            } else if (id == 9) {
//                return choices10[a][3];
//            } else if (id == 10) {
//                return choices11[a][3];
//            } else if (id == 11) {
//                return choices12[a][3];
//            } else if (id == 12) {
//                return choices13[a][3];
//            } else if (id == 13) {
//                return choices14[a][3];
//            }
        }
        return null;
    }

}
