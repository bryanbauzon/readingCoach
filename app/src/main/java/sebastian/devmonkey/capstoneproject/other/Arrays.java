package sebastian.devmonkey.capstoneproject.other;

public class Arrays {

    private String[] poemTitles = {
            "Sonnet 1",
            "Sonnet 2",
            "Sonnet 3",
            "Sonnet 4",
            "Sonnet 5",
            "Sonnet 6",
            "Sonnet 7",
            "Sonnet 8",
            "Sonnet 9",
            "Sonnet 10",
            "Sonnet 11",
            "Sonnet 12",
            "Sonnet 13",
            "Sonnet 14",
            "Sonnet 15",
            "Sonnet 16",
            "Sonnet 17",
            "Sonnet 18",
            "Sonnet 19",
            "Sonnet 20",
            "Sonnet 21",
            "Sonnet 22",
            "Sonnet 23",
            "Sonnet 24",
            "Sonnet 25",
            "Sonnet 26",
            "Sonnet 27",
            "Sonnet 28",
            "Sonnet 29",
            "Sonnet 30",
            "Sonnet 31",
            "Sonnet 32",
            "Sonnet 33",
            "Sonnet 34",
            "Sonnet 35",
            "Sonnet 36",
            "Sonnet 37",
            "Sonnet 38",
            "Sonnet 39",
            "Sonnet 40",
            "Sonnet 41",
            "Sonnet 42",
            "Sonnet 43",
            "Sonnet 44",
            "Sonnet 45",
            "Sonnet 46",
            "Sonnet 47",
            "Sonnet 48",
            "Sonnet 49",
            "Sonnet 50",
            "Sonnet 51",
            "Sonnet 52"
    };

    private String[] storyTitles = {"500 MILES RACE",
            "A CALL TO THE POOL",
            "A HAPPY VISITOR.",
            "AIR BALLOON",
            "AIRPLANES",
            "ALLIGATORS",
            "BEARS",
            "BEDS",
            "BELLA HIDES",
            "BIG CITY NOISE",
            "BIRDS",
            "BIRD'S NEST",
            "BULLIED",
            "BUTTERFLY",
            "BY THE WATER",
            "CAMPFIRE",
            "CANNED GOODS",
            "DANDELIONS",
            "DIFFERENT TYPES OF PEOPLE",
            "DOGS",
            "EMPRESS OF THE BLUES",
            "FISH",
            "FLAGS",
            "FRIED",
            "GRASS",
            "GREEN GRASS",
            "GRIFIN",
            "GRIFIN'S TALENT"

     };

    private String[] storyIntermediateTitle = {
            "A COLD DAY",
            "A MYSTERY",
            "ABRAHAM LINCOLN",
            "BAIL",
            "BENJAMIN FRANKLIN",
            "BIRDSONGS",
            "BLACK AMERICAN AUTHOR",
            "BLACK BEARS",
            "BLIZZARD UN BIRMINGHA",
            "BLUE WHALE",
            "CARS",
            "CITY NOISE",
            "CLOCKS",
            "COINS",
            "CORN",
            "COUNTING",
            "EASTER ISLAND",
            "HELICOPTERS",
            "HIBERNATION AND ESTIVATION",
            "HOUSES",
            "HOW TO BE A PIG"
    };
    private String[] storyHardTitle = {
            "Seeing Stars",
            "Soda Pop",
            "Taste",
            "Tea",
            "Tennis",
            "The 20",
            "The Blow Driver",
            "The Drive",
            "The Heart",
            "The Interview",
            "The Music",
            "The Singing Bird",
            "Time To",
            "Trees",
            "What Number",
            "Yellow Stone National Park",
            "Zach's Animals"
    };

    private String[] terminologiesWords = {
            "Spurious",
            "Gossip",
            "Fraternizing",
            "Vicariously",
            "Brazen",
            "Coveted",
            "Deficit",
            "Copious",
            "Augmentation",
            "Evoke"
    };

    private String[] terminologiesMeaning = {
            "not being what it purports to be; false or fake.",
            "casual or unconstrained conversation or reports about other people, typically involving details that are not confirmed as being true.",
            "associate or form a friendship with someone, especially when one is not supposed to.",
            "in a way that is experienced in the imagination through the actions of another person.",
            "endure an embarrassing or difficult situation by behaving with apparent confidence and lack of shame.",
            "yearn to possess or have (something).",
            "the amount by which something, especially a sum of money, is too small.",
            "abundant in supply or quantity.",
            "the action or process of making or becoming greater in size or amount.",
            "bring or recall to the conscious mind."
    };




    public String[] getStoryTitles() {
        return storyTitles.clone();
    }
    public String[] getStoryIntermediateTitleTitles() {
        return storyIntermediateTitle.clone();
    }
    public String[] getStoryHardTitles(){return  storyHardTitle.clone();}



    public String[] getTerminologiesWords() {
        return terminologiesWords.clone();
    }

    public String getTerminologiesWords1(int a) {
        return terminologiesWords[a];
    }
    public String getTerminologiesMeaning(int a) {
        return terminologiesMeaning[a];
    }

    public String[] getPoemTitles() {
        return poemTitles.clone();
    }




//    public int getLength() {
//        return mQuestion.length;
//    }


}
