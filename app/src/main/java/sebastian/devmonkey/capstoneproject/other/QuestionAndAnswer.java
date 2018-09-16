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

    private String[] question3 = {
            "What kind of animal is an alligator?",
            "What does an alligator look like?",
            "What do alligators eat?",
            "According to the story, most alligators are",
            "The opposite of sharp is"

    };

    private String[] question4 = {
            "Where does Jaime live?",
            "What kind of animal is Go-Go?",
            "In the middle of the story, they walk across the field. This means they walk..",
            "What does Jaime do at the beginning of the story?",
            "What does Jaime want to do at the end of the story?"

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

    private String[][] choices3 = {
            {"An amphibian", "A mammal", "A reptile", "A fish"},
            {"It has long legs", "It has a long tail", "It has sharp teeth", "B and C"},
            {"Birds", "Plants", "Fish", "A and C"},
            {"Big and long", "Tall and skinny", "Small and weak", "Fat and funny"},
            {"Dull", "Big", "Soft", "Smark"}
    };

    private String[][] choices4 = {
            {"He lives in a house in the city", "He lives in an apartment in the country", "He lives in a house in the country", "He lives in an apartment in the city"},
            {"A Dog", "A Cat", "A Pig", "A Bird"},
            {"Birds", "Plants", "Fish", "A and C"},
            {"Big and long", "Tall and skinny", "Small and weak", "Fat and funny"},
            {"Dull", "Big", "Soft", "Smark"}
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

    private String[] answer3 = {
            "A reptile",
            "B and C",
            "A and C",
            "Big and long",
            "Dull"
    };

    public String getQuestion(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return question1[a];
            } else if (id == 1) {
                return question2[a];
            } else if (id == 2) {
                return question3[a];
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
            } else if( id == 2) {
                return answer3[a];
            }
        }

        return null;
    }


    public String getChoice1(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return choices[a][0];
            } else if (id == 1) {
                return choices2[a][0];
            } else if (id == 2) {
                return choices3[a][0];
            }
        }
        return null;
    }


    public String getChoice2(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return choices[a][1];
            } else if (id == 1) {
                return choices2[a][1];
            } else if (id == 2) {
                return choices3[a][1];
            }
        }
        return null;
    }

    public String getChoice3(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return choices[a][2];
            } else if (id == 1) {
                return choices2[a][2];
            } else if (id == 2) {
                return choices3[a][2];
            }
        }
        return null;
    }


    public String getChoice4(int a, int id, String level) {

        if (level.equals("easy")) {
            if (id == 0) {
                return choices[a][3];
            } else if (id == 1) {
                return choices2[a][3];
            } else if (id == 2) {
                return choices3[a][3];
            }
        }
        return null;
    }




}
