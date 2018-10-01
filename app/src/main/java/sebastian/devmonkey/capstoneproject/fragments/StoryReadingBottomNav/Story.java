package sebastian.devmonkey.capstoneproject.fragments.StoryReadingBottomNav;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Story.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Story#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Story extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Menu menu;
    TextView txtContent, txtTitle, txtAuthor;
    TextToSpeech textToSpeech;
    int ctr;

    String id_temp, title, level;
    InputStream is;
    GlobalVariable gv;
    DatabaseHelper db;
    private boolean check = false;

    private OnFragmentInteractionListener mListener;

    public Story() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Story.
     */
    // TODO: Rename and change types and number of parameters
    public static Story newInstance(String param1, String param2) {
        Story fragment = new Story();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, container, false);

        Intent intent = getActivity().getIntent();
        txtContent = view.findViewById(R.id.txtContent);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtAuthor = view.findViewById(R.id.txtAuthor);

        db = new DatabaseHelper(getActivity());
        gv = new GlobalVariable();

        //color scheme
        if (GlobalVariable.color == 1){
            view.setBackgroundColor(Color.parseColor("#000000"));
            txtTitle.setTextColor(Color.WHITE);
            txtContent.setTextColor(Color.WHITE);
        } else {
            view.setBackgroundColor(Color.WHITE);
            txtTitle.setTextColor(Color.BLACK);
            txtContent.setTextColor(Color.BLACK);
        }

        //font
        txtTitle.setTypeface(GlobalVariable.font);
        txtContent.setTypeface(GlobalVariable.font);

        //fontsize
        txtTitle.setTextSize(GlobalVariable.fontSize);
        txtContent.setTextSize(GlobalVariable.fontSize);

        //line spacing
        gv.setMargins(txtTitle, GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);
        gv.setMargins(txtContent ,GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);

        txtContent.setLineSpacing(GlobalVariable.lineSpacing, 1);



        //this variable is used for conditional statement and serves as the id
        level = intent.getStringExtra("level");
        id_temp = intent.getStringExtra("id");
        title = intent.getStringExtra("title");

        txtTitle.setText(title);

        int id = Integer.parseInt(id_temp);
        Toast.makeText(getActivity(),id_temp, Toast.LENGTH_SHORT).show();
        //text to speech
        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.US);
                    textToSpeech.setPitch(0.8f);
                }
            }
        });

        txtContent.setMovementMethod(new ScrollingMovementMethod());
        String data = "";
        StringBuffer stringBuffer = new StringBuffer();
        if(level.equals("easy")){
            //testing for id
            if(id == 0){
                //array is always starts at 0;
                is = getActivity().getResources().openRawResource(R.raw.miles_race);
                txtAuthor.setText("Gerry Sarko");
            } else if(id == 1){
                is = getActivity().getResources().openRawResource(R.raw.a_call_to_the_pool);
                txtAuthor.setText("Susan Carter");
            } else if(id == 2){
                is = getActivity().getResources().openRawResource(R.raw.a_happy_visitor);
            } else if(id == 3){
                is = getActivity().getResources().openRawResource(R.raw.air_ballons);
            } else if(id == 4){
                is = getActivity().getResources().openRawResource(R.raw.airplanes);
            } else if(id == 5){
                is = getActivity().getResources().openRawResource(R.raw.alligators);
            } else if(id == 6){
                is = getActivity().getResources().openRawResource(R.raw.american_crawl);
                txtAuthor.setText("Daniel Abraham");
            } else if(id == 7){
                is = getActivity().getResources().openRawResource(R.raw.americas_first_scuptor);
            } else if(id == 8){
                is = getActivity().getResources().openRawResource(R.raw.an_adventure);
                txtAuthor.setText("Susan Carter");
            } else if(id == 9){
                is = getActivity().getResources().openRawResource(R.raw.bears);
            } else if(id == 10){
                is = getActivity().getResources().openRawResource(R.raw.beds);
            } else if(id == 11){
                is = getActivity().getResources().openRawResource(R.raw.bella_hides);
            } else if(id == 12){
                is = getActivity().getResources().openRawResource(R.raw.big_city_noise);
            } else if(id == 13){
                is = getActivity().getResources().openRawResource(R.raw.birds);
            } else if(id == 14){
                is = getActivity().getResources().openRawResource(R.raw.birds_nest);
            } else if(id == 15) {
                is = getActivity().getResources().openRawResource(R.raw.bullied);
            } else if(id == 16) {
                is = getActivity().getResources().openRawResource(R.raw.butterfly);
            } else if(id == 17) {
                is = getActivity().getResources().openRawResource(R.raw.by_the_water);
            } else if(id == 18) {
                is = getActivity().getResources().openRawResource(R.raw.campfire);
            } else if(id == 19) {
                is = getActivity().getResources().openRawResource(R.raw.canned_goods);
            } else if(id == 20) {
                is = getActivity().getResources().openRawResource(R.raw.dandelions);
            } else if(id == 21) {
                is = getActivity().getResources().openRawResource(R.raw.different_type_of_people);
            } else if(id == 22) {
                is = getActivity().getResources().openRawResource(R.raw.dogs);
            } else if(id == 23) {
                is = getActivity().getResources().openRawResource(R.raw.empress_of_the_blues);
            } else if(id == 24) {
                is = getActivity().getResources().openRawResource(R.raw.fish);
            } else if(id == 25) {
                is = getActivity().getResources().openRawResource(R.raw.flags);
            } else if(id == 26) {
                is = getActivity().getResources().openRawResource(R.raw.fried);
            } else if(id == 27) {
                is = getActivity().getResources().openRawResource(R.raw.grass);
            } else if(id == 28) {
                is = getActivity().getResources().openRawResource(R.raw.green_grass);
            } else if(id == 29) {
                is = getActivity().getResources().openRawResource(R.raw.griffin);
            } else if(id == 30) {
                is = getActivity().getResources().openRawResource(R.raw.griffins_talent);
            } else if(id == 31) {
                is = getActivity().getResources().openRawResource(R.raw.helicopters);
            } else if(id == 32) {
                is = getActivity().getResources().openRawResource(R.raw.houses);
            } else if(id == 33) {
                is = getActivity().getResources().openRawResource(R.raw.how_are_you);
            } else if(id == 34) {
                is = getActivity().getResources().openRawResource(R.raw.how_seeds_grow);
            } else if(id == 35) {
                is = getActivity().getResources().openRawResource(R.raw.how_warthog_lives);
            } else if(id == 36) {
                is = getActivity().getResources().openRawResource(R.raw.how_worms_get_inside_apple);
            } else if(id == 37) {
                is = getActivity().getResources().openRawResource(R.raw.humans);
            } else if(id == 38) {
                is = getActivity().getResources().openRawResource(R.raw.i_fly);
            } else if(id == 39) {
                is = getActivity().getResources().openRawResource(R.raw.ice_fishing);
            } else if(id == 40) {
                is = getActivity().getResources().openRawResource(R.raw.julian);
            } else if(id == 41) {
                is = getActivity().getResources().openRawResource(R.raw.land_of_africa);
            } else if(id == 42) {
                is = getActivity().getResources().openRawResource(R.raw.leonardo);
            } else if(id == 43) {
                is = getActivity().getResources().openRawResource(R.raw.lord_of_olympus);
            } else if(id == 44) {
                is = getActivity().getResources().openRawResource(R.raw.money);
            } else if(id == 45) {
                is = getActivity().getResources().openRawResource(R.raw.my_family);
            } else if(id == 46) {
                is = getActivity().getResources().openRawResource(R.raw.my_friend);
            } else if(id == 47) {
                is = getActivity().getResources().openRawResource(R.raw.my_house);
            } else if(id == 48) {
                is = getActivity().getResources().openRawResource(R.raw.new_shoes_for_mandy);
            } else if(id == 49) {
                is = getActivity().getResources().openRawResource(R.raw.one_hundred_dollars);
            } else if(id == 50) {
                is = getActivity().getResources().openRawResource(R.raw.paper);
            } else if(id == 51) {
                is = getActivity().getResources().openRawResource(R.raw.parts_of_piano);
            } else if(id == 52) {
                is = getActivity().getResources().openRawResource(R.raw.paul_cooks);
            } else if(id == 53) {
                is = getActivity().getResources().openRawResource(R.raw.rainy_day);
            } else if(id == 54) {
                is = getActivity().getResources().openRawResource(R.raw.running);
            } else if(id == 55) {
                is = getActivity().getResources().openRawResource(R.raw.seeing_stars);
            } else if(id == 56) {
                is = getActivity().getResources().openRawResource(R.raw.sky);
            } else if(id == 57) {
                is = getActivity().getResources().openRawResource(R.raw.soap);
            } else if(id == 58) {
                is = getActivity().getResources().openRawResource(R.raw.soda_pop);
            } else if(id == 59) {
                is = getActivity().getResources().openRawResource(R.raw.story_of_lord_sandwich);
            } else if(id == 60) {
                is = getActivity().getResources().openRawResource(R.raw.talia);
            } else if(id == 61) {
                is = getActivity().getResources().openRawResource(R.raw.taste);
            } else if(id == 62) {
                is = getActivity().getResources().openRawResource(R.raw.tea);
            } else if(id == 63) {
                is = getActivity().getResources().openRawResource(R.raw.television);
            } else if(id == 64) {
                is = getActivity().getResources().openRawResource(R.raw.tennis);
            } else if(id == 65) {
                is = getActivity().getResources().openRawResource(R.raw.the_20);
            } else if(id == 66) {
                is = getActivity().getResources().openRawResource(R.raw.the_blow_dryer);
            } else if(id == 67) {
                is = getActivity().getResources().openRawResource(R.raw.the_cat_the_rooster_and_the_young_mouse);
            } else if(id == 68) {
                is = getActivity().getResources().openRawResource(R.raw.the_dog_and_the_manger);
            } else if(id == 69) {
                is = getActivity().getResources().openRawResource(R.raw.the_donkey_in_the_lions_skin);
            } else if(id == 70) {
                is = getActivity().getResources().openRawResource(R.raw.the_drive);
            } else if(id == 71) {
                is = getActivity().getResources().openRawResource(R.raw.the_fog_and_the_fox);
            } else if(id == 72) {
                is = getActivity().getResources().openRawResource(R.raw.the_fox_and_the_crow);
            } else if(id == 73) {
                is = getActivity().getResources().openRawResource(R.raw.the_fox_and_the_grapes);
            } else if(id == 74) {
                is = getActivity().getResources().openRawResource(R.raw.the_hawaian_people_song);
            } else if(id == 75) {
                is = getActivity().getResources().openRawResource(R.raw.the_heart);
            } else if(id == 76) {
                is = getActivity().getResources().openRawResource(R.raw.the_interview);
            } else if(id == 77) {
                is = getActivity().getResources().openRawResource(R.raw.the_lion_and_the_mouse);
            } else if(id == 78) {
                is = getActivity().getResources().openRawResource(R.raw.the_mice_in_council);
            } else if(id == 79) {
                is = getActivity().getResources().openRawResource(R.raw.the_music);
            } else if(id == 80) {
                is = getActivity().getResources().openRawResource(R.raw.the_rabbit_and_the_turtle);
            } else if(id == 81) {
                is = getActivity().getResources().openRawResource(R.raw.the_red_carpet_treatment);
            } else if(id == 82) {
                is = getActivity().getResources().openRawResource(R.raw.the_rent_man);
            } else if(id == 83) {
                is = getActivity().getResources().openRawResource(R.raw.the_rooster_and_the_ax);
            } else if(id == 84) {
                is = getActivity().getResources().openRawResource(R.raw.the_rooster_and_the_jewel);
            } else if(id == 85) {
                is = getActivity().getResources().openRawResource(R.raw.the_singing_bird);
            } else if(id == 86) {
                is = getActivity().getResources().openRawResource(R.raw.the_sky);
            } else if(id == 87) {
                is = getActivity().getResources().openRawResource(R.raw.the_tree_and_the_ax);
            } else if(id == 88) {
                is = getActivity().getResources().openRawResource(R.raw.the_united_states);
            } else if(id == 89) {
                is = getActivity().getResources().openRawResource(R.raw.the_wind_and_the_sun);
            } else if(id == 90) {
                is = getActivity().getResources().openRawResource(R.raw.tie_your_shoes);
            } else if(id == 91) {
                is = getActivity().getResources().openRawResource(R.raw.time_to);
            } else if(id == 92) {
                is = getActivity().getResources().openRawResource(R.raw.trees);
            } else if(id == 93) {
                is = getActivity().getResources().openRawResource(R.raw.what_number);
            } else if(id == 94) {
                is = getActivity().getResources().openRawResource(R.raw.what_time_is_it);
            } else if(id == 95) {
                is = getActivity().getResources().openRawResource(R.raw.where_are_my_glasses);
            } else if(id == 96) {
                is = getActivity().getResources().openRawResource(R.raw.yellowstone_national_park);
            } else if(id == 97) {
                is = getActivity().getResources().openRawResource(R.raw.zach);
            }

        }else if(level.equals("intermediate")){
            if(id == 0){
                //array is always starts at 0;
                is = getActivity().getResources().openRawResource(R.raw.a_cold_day);
            } else if(id == 1){
                is = getActivity().getResources().openRawResource(R.raw.a_mystery);
            } else if(id == 2){
                is = getActivity().getResources().openRawResource(R.raw.abraham_lincoln);
            } else if(id == 3){
                is = getActivity().getResources().openRawResource(R.raw.amazon_rainforest);
            } else if(id == 4){
                is = getActivity().getResources().openRawResource(R.raw.american_indian_names);
            } else if(id == 5){
                is = getActivity().getResources().openRawResource(R.raw.americas_first_scuptor);
            } else if(id == 6){
                is = getActivity().getResources().openRawResource(R.raw.americas_first_woman);
            } else if(id == 7){
                is = getActivity().getResources().openRawResource(R.raw.arctic_fox);
            } else if(id == 8){
                is = getActivity().getResources().openRawResource(R.raw.bail);
            } else if(id == 9){
                is = getActivity().getResources().openRawResource(R.raw.banks);
            } else if(id == 10){
                is = getActivity().getResources().openRawResource(R.raw.benjamin_franklin);
            } else if(id == 11){
                is = getActivity().getResources().openRawResource(R.raw.birdsongs);
            } else if(id == 12){
                is = getActivity().getResources().openRawResource(R.raw.black_american_author);
            } else if(id == 13){
                is = getActivity().getResources().openRawResource(R.raw.black_bears);
            } else if(id == 14){
                is = getActivity().getResources().openRawResource(R.raw.blizzard_un_birmingha);
            } else if(id == 15) {
                is = getActivity().getResources().openRawResource(R.raw.blue_whale);
            } else if(id == 16) {
                is = getActivity().getResources().openRawResource(R.raw.butterflies);
            } else if(id == 17) {
                is = getActivity().getResources().openRawResource(R.raw.cars);
            } else if(id == 18) {
                is = getActivity().getResources().openRawResource(R.raw.christopher_columbus);
            } else if(id == 19) {
                is = getActivity().getResources().openRawResource(R.raw.city_noise);
            } else if(id == 20) {
                is = getActivity().getResources().openRawResource(R.raw.clocks);
            } else if(id == 21) {
                is = getActivity().getResources().openRawResource(R.raw.coins);
            } else if(id == 22) {
                is = getActivity().getResources().openRawResource(R.raw.common_loon);
            } else if(id == 23) {
                is = getActivity().getResources().openRawResource(R.raw.corn);
            } else if(id == 24) {
                is = getActivity().getResources().openRawResource(R.raw.counting);
            } else if(id == 25) {
                is = getActivity().getResources().openRawResource(R.raw.cricket);
            } else if(id == 26) {
                is = getActivity().getResources().openRawResource(R.raw.discovery_of_ice_cream);
            } else if(id == 27) {
                is = getActivity().getResources().openRawResource(R.raw.doctor_in_chicago);
            } else if(id == 28) {
                is = getActivity().getResources().openRawResource(R.raw.dunkin_donuts);
            } else if(id == 29) {
                is = getActivity().getResources().openRawResource(R.raw.easter_island);
            } else if(id == 30) {
                is = getActivity().getResources().openRawResource(R.raw.garlics);
            } else if(id == 31) {
                is = getActivity().getResources().openRawResource(R.raw.halloween);
            } else if(id == 32) {
                is = getActivity().getResources().openRawResource(R.raw.helicopters);
            } else if(id == 33) {
                is = getActivity().getResources().openRawResource(R.raw.hibernation_and_estivation);
            } else if(id == 34) {
                is = getActivity().getResources().openRawResource(R.raw.houses);
            } else if(id == 35) {
                is = getActivity().getResources().openRawResource(R.raw.how_to_be_a_pig);
            } else if(id == 36) {
                is = getActivity().getResources().openRawResource(R.raw.john_cooks);
            } else if(id == 37) {
                is = getActivity().getResources().openRawResource(R.raw.komodo_dragon);
            } else if(id == 38) {
                is = getActivity().getResources().openRawResource(R.raw.largest_creature_on_earth);
            } else if(id == 39) {
                is = getActivity().getResources().openRawResource(R.raw.late);
            } else if(id == 40) {
                is = getActivity().getResources().openRawResource(R.raw.lobster);
            } else if(id == 41) {
                is = getActivity().getResources().openRawResource(R.raw.locks_and_keys);
            } else if(id == 42) {
                is = getActivity().getResources().openRawResource(R.raw.marco_polo);
            } else if(id == 43) {
                is = getActivity().getResources().openRawResource(R.raw.mars);
            } else if(id == 44) {
                is = getActivity().getResources().openRawResource(R.raw.marys_family);
            } else if(id == 45) {
                is = getActivity().getResources().openRawResource(R.raw.moorish_dance);
            } else if(id == 46) {
                is = getActivity().getResources().openRawResource(R.raw.movie_ratings);
            } else if(id == 47) {
                is = getActivity().getResources().openRawResource(R.raw.mummy);
            } else if(id == 48) {
                is = getActivity().getResources().openRawResource(R.raw.niagara_falls);
            } else if(id == 49) {
                is = getActivity().getResources().openRawResource(R.raw.nuts);
            } else if(id == 50) {
                is = getActivity().getResources().openRawResource(R.raw.olympic_game);
            } else if(id == 51) {
                is = getActivity().getResources().openRawResource(R.raw.panda);
            } else if(id == 52) {
                is = getActivity().getResources().openRawResource(R.raw.penquins);
            } else if(id == 53) {
                is = getActivity().getResources().openRawResource(R.raw.photography);
            } else if(id == 54) {
                is = getActivity().getResources().openRawResource(R.raw.rainbows);
            } else if(id == 55) {
                is = getActivity().getResources().openRawResource(R.raw.roller_skates);
            } else if(id == 56) {
                is = getActivity().getResources().openRawResource(R.raw.saltwater);
            } else if(id == 57) {
                is = getActivity().getResources().openRawResource(R.raw.seamounts);
            } else if(id == 58) {
                is = getActivity().getResources().openRawResource(R.raw.sequoya);
            } else if(id == 59) {
                is = getActivity().getResources().openRawResource(R.raw.shirley_chsholm);
            } else if(id == 60) {
                is = getActivity().getResources().openRawResource(R.raw.silk_road);
            } else if(id == 61) {
                is = getActivity().getResources().openRawResource(R.raw.snowball_in_space);
            } else if(id == 62) {
                is = getActivity().getResources().openRawResource(R.raw.soccer);
            } else if(id == 63) {
                is = getActivity().getResources().openRawResource(R.raw.spider);
            } else if(id == 64) {
                is = getActivity().getResources().openRawResource(R.raw.stage_coaches);
            } else if(id == 65) {
                is = getActivity().getResources().openRawResource(R.raw.terrain);
            } else if(id == 66) {
                is = getActivity().getResources().openRawResource(R.raw.the_brenners);
            } else if(id == 67) {
                is = getActivity().getResources().openRawResource(R.raw.the_bus_driver);
            } else if(id == 68) {
                is = getActivity().getResources().openRawResource(R.raw.the_cactus);
            } else if(id == 69) {
                is = getActivity().getResources().openRawResource(R.raw.the_color_green);
            } else if(id == 70) {
                is = getActivity().getResources().openRawResource(R.raw.the_flu);
            } else if(id == 71) {
                is = getActivity().getResources().openRawResource(R.raw.the_new_school);
            } else if(id == 72) {
                is = getActivity().getResources().openRawResource(R.raw.the_park);
            } else if(id == 73) {
                is = getActivity().getResources().openRawResource(R.raw.the_story_of_marian);
            } else if(id == 74) {
                is = getActivity().getResources().openRawResource(R.raw.the_sun);
            } else if(id == 75) {
                is = getActivity().getResources().openRawResource(R.raw.the_us_census);
            } else if(id == 76) {
                is = getActivity().getResources().openRawResource(R.raw.the_white_house);
            } else if(id == 77) {
                is = getActivity().getResources().openRawResource(R.raw.three_politic_warr);
            } else if(id == 78) {
                is = getActivity().getResources().openRawResource(R.raw.time_with_granpa);
            } else if(id == 79) {
                is = getActivity().getResources().openRawResource(R.raw.transportation);
            } else if(id == 80) {
                is = getActivity().getResources().openRawResource(R.raw.troglodytes);
            } else if(id == 81) {
                is = getActivity().getResources().openRawResource(R.raw.two_best_known);
            } else if(id == 82) {
                is = getActivity().getResources().openRawResource(R.raw.umbrellas);
            } else if(id == 83) {
                is = getActivity().getResources().openRawResource(R.raw.use_of_peanuts);
            } else if(id == 84) {
                is = getActivity().getResources().openRawResource(R.raw.use_of_tounge_in_animal);
            } else if(id == 85) {
                is = getActivity().getResources().openRawResource(R.raw.vet_emergency);
            } else if(id == 86) {
                is = getActivity().getResources().openRawResource(R.raw.water);
            } else if(id == 87) {
                is = getActivity().getResources().openRawResource(R.raw.what_is_a_wasp);
            } else if(id == 88) {
                is = getActivity().getResources().openRawResource(R.raw.why_barn_is_always);
            } else if(id == 89) {
                is = getActivity().getResources().openRawResource(R.raw.worth_working_for);
            }


        }else if(level.equals("hard")){
            if(id == 0){
                //array is always starts at 0;
                is = getActivity().getResources().openRawResource(R.raw.a_christmas_in_march);
            } else if(id == 1){
                is = getActivity().getResources().openRawResource(R.raw.a_daring_escape);
            } else if(id == 2){
                is = getActivity().getResources().openRawResource(R.raw.accused);
            } else if(id == 3){
                is = getActivity().getResources().openRawResource(R.raw.advance_critical_reading);
            } else if(id == 4){
                is = getActivity().getResources().openRawResource(R.raw.ana_finds_an);
            } else if(id == 5){
                is = getActivity().getResources().openRawResource(R.raw.asian_carp);
            } else if(id == 6){
                is = getActivity().getResources().openRawResource(R.raw.bathtubs);
            } else if(id == 7){
                is = getActivity().getResources().openRawResource(R.raw.bb);
            } else if(id == 8){
                is = getActivity().getResources().openRawResource(R.raw.black_friday);
            } else if(id == 9){
                is = getActivity().getResources().openRawResource(R.raw.can_windmills_stalk);
            } else if(id == 10){
                is = getActivity().getResources().openRawResource(R.raw.canopy_of_nature);
            } else if(id == 11){
                is = getActivity().getResources().openRawResource(R.raw.carnivorous);
            } else if(id == 12){
                is = getActivity().getResources().openRawResource(R.raw.cells);
            } else if(id == 13){
                is = getActivity().getResources().openRawResource(R.raw.chocolate);
            } else if(id == 14) {
                is = getActivity().getResources().openRawResource(R.raw.city_girl);
            } else if(id == 15) {
                is = getActivity().getResources().openRawResource(R.raw.clean_water_act);
            } else if(id == 16) {
                is = getActivity().getResources().openRawResource(R.raw.collection_of_folklore);
            } else if(id == 17) {
                is = getActivity().getResources().openRawResource(R.raw.dreams);
            } else if(id == 18) {
                is = getActivity().getResources().openRawResource(R.raw.eating_out);
            } else if(id == 19) {
                is = getActivity().getResources().openRawResource(R.raw.el_dia_de_san_juan);
            } else if(id == 20) {
                is = getActivity().getResources().openRawResource(R.raw.europe);
            } else if(id == 21) {
                is = getActivity().getResources().openRawResource(R.raw.fingerprints);
            } else if(id == 22) {
                is = getActivity().getResources().openRawResource(R.raw.florida_manatee);
            } else if(id == 23) {
                is = getActivity().getResources().openRawResource(R.raw.flower_power);
            } else if(id == 24) {
                is = getActivity().getResources().openRawResource(R.raw.fortune_tellers);
            } else if(id == 25) {
                is = getActivity().getResources().openRawResource(R.raw.france);
            } else if(id == 26) {
                is = getActivity().getResources().openRawResource(R.raw.galaxies);
            } else if(id == 27) {
                is = getActivity().getResources().openRawResource(R.raw.gilrays_flower_pot);
            } else if(id == 28) {
                is = getActivity().getResources().openRawResource(R.raw.goodluck_for_the_good);
            } else if(id == 29) {
                is = getActivity().getResources().openRawResource(R.raw.guermos_surprise);
            } else if(id == 30) {
                is = getActivity().getResources().openRawResource(R.raw.house_around_the_wordd);
            } else if(id == 31) {
                is = getActivity().getResources().openRawResource(R.raw.interstate_highways);
            } else if(id == 32) {
                is = getActivity().getResources().openRawResource(R.raw.juan_ponce);
            } else if(id == 33) {
                is = getActivity().getResources().openRawResource(R.raw.just_one_touch);
            } else if(id == 34) {
                is = getActivity().getResources().openRawResource(R.raw.kok);
            } else if(id == 35) {
                is = getActivity().getResources().openRawResource(R.raw.lightning_and_thunder);
            } else if(id == 36) {
                is = getActivity().getResources().openRawResource(R.raw.love_train);
            } else if(id == 37) {
                is = getActivity().getResources().openRawResource(R.raw.marias_beatiful);
            } else if(id == 38) {
                is = getActivity().getResources().openRawResource(R.raw.meteors);
            } else if(id == 39) {
                is = getActivity().getResources().openRawResource(R.raw.mexico);
            } else if(id == 40) {
                is = getActivity().getResources().openRawResource(R.raw.moongooes);
            } else if(id == 41) {
                is = getActivity().getResources().openRawResource(R.raw.mosquitoes);
            } else if(id == 42) {
                is = getActivity().getResources().openRawResource(R.raw.mothers_day);
            } else if(id == 43) {
                is = getActivity().getResources().openRawResource(R.raw.oil_spill);
            } else if(id == 44) {
                is = getActivity().getResources().openRawResource(R.raw.on_the_right_track);
            } else if(id == 45) {
                is = getActivity().getResources().openRawResource(R.raw.oversmart_is_badluck);
            } else if(id == 46) {
                is = getActivity().getResources().openRawResource(R.raw.planet_mars);
            } else if(id == 47) {
                is = getActivity().getResources().openRawResource(R.raw.pollutions);
            } else if(id == 48) {
                is = getActivity().getResources().openRawResource(R.raw.potato_chips);
            } else if(id == 49) {
                is = getActivity().getResources().openRawResource(R.raw.raisins);
            } else if(id == 50) {
                is = getActivity().getResources().openRawResource(R.raw.round_and_round);
            } else if(id == 51) {
                is = getActivity().getResources().openRawResource(R.raw.seatbelts);
            } else if(id == 52) {
                is = getActivity().getResources().openRawResource(R.raw.seeing_clearly);
            } else if(id == 53) {
                is = getActivity().getResources().openRawResource(R.raw.sharks);
            } else if(id == 54) {
                is = getActivity().getResources().openRawResource(R.raw.sleep);
            } else if(id == 55) {
                is = getActivity().getResources().openRawResource(R.raw.suffrage);
            } else if(id == 56) {
                is = getActivity().getResources().openRawResource(R.raw.take_care_of_your_dog);
            } else if(id == 57) {
                is = getActivity().getResources().openRawResource(R.raw.taro_and_the_tofu);
            } else if(id == 58) {
                is = getActivity().getResources().openRawResource(R.raw.tattoo);
            } else if(id == 59) {
                is = getActivity().getResources().openRawResource(R.raw.the_case_of_the_unknown);
            } else if(id == 60) {
                is = getActivity().getResources().openRawResource(R.raw.the_cat_that_walked);
            } else if(id == 61) {
                is = getActivity().getResources().openRawResource(R.raw.the_clever_wizardx);
            } else if(id == 62) {
                is = getActivity().getResources().openRawResource(R.raw.the_coliseum);
            } else if(id == 63) {
                is = getActivity().getResources().openRawResource(R.raw.the_cowardly_lion);
            } else if(id == 64) {
                is = getActivity().getResources().openRawResource(R.raw.the_first_words);
            } else if(id == 65) {
                is = getActivity().getResources().openRawResource(R.raw.the_great_buddha);
            } else if(id == 66) {
                is = getActivity().getResources().openRawResource(R.raw.the_mini_problem);
            } else if(id == 67) {
                is = getActivity().getResources().openRawResource(R.raw.the_secret_soldier);
            } else if(id == 68) {
                is = getActivity().getResources().openRawResource(R.raw.the_son);
            } else if(id == 69) {
                is = getActivity().getResources().openRawResource(R.raw.the_storm);
            } else if(id == 70) {
                is = getActivity().getResources().openRawResource(R.raw.the_story_of_keesh);
            } else if(id == 71) {
                is = getActivity().getResources().openRawResource(R.raw.the_transfer);
            } else if(id == 72) {
                is = getActivity().getResources().openRawResource(R.raw.tracks);
            } else if(id == 73) {
                is = getActivity().getResources().openRawResource(R.raw.tyrannusirus_trex);
            } else if(id == 74) {
                is = getActivity().getResources().openRawResource(R.raw.venus);
            } else if(id == 75) {
                is = getActivity().getResources().openRawResource(R.raw.wampum);
            } else if(id == 76) {
                is = getActivity().getResources().openRawResource(R.raw.wanga);
            } else if(id == 77) {
                is = getActivity().getResources().openRawResource(R.raw.what_a_pig_ture);
            } else if (id == 78) {
                is = getActivity().getResources().openRawResource(R.raw.wild);
            }
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if(is != null){
            try {
                //assigning the value of reader to data and test it until the condition meets,
                while ((data = reader.readLine()) != null){
                    stringBuffer.append(data + "");
                }
                txtContent.setText(stringBuffer);
                is.close();
            }catch (Exception e){

            }
        }


        // Inflate the layout for this fragment
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.text_to_speech, menu);
        this.menu = menu;
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.t2s:
                ctr++;
                MenuItem menuItem = menu.findItem(R.id.t2s);

                if(ctr == 1){
                    menuItem.setTitle("Stop");
                    String toSpeak = txtContent.getText().toString();
                    textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);

                }else{
                    menuItem.setTitle("Speech");
                    textToSpeech.stop();

                    ctr = 0;
                }
                return true;

            case R.id.bm:
                addBookmark();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void addBookmark() {

        if (!db.hasObject(id_temp)) {
            db.insertDataBookmarks(title, id_temp, level);
            check = true;
            Toast.makeText(getActivity(), "Added to bookmarks", Toast.LENGTH_SHORT).show();
        } else {
            db.deleteDataBookmarks(id_temp);
            check = false;
            Toast.makeText(getActivity(), "Removed to bookmarks", Toast.LENGTH_SHORT).show();
        }

    }

}
