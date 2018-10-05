package sebastian.devmonkey.capstoneproject.fragments.StoryReadingBottomNav;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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



//        SharedPreferences pref = getActivity().getSharedPreferences("bookmark",Context.MODE_PRIVATE);
//        final String status = pref.getString("bookmarked",null);
//
//        if(status != null){
//        }
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
       // Toast.makeText(getActivity(),title_holder,Toast.LENGTH_LONG).show();



        txtTitle.setText(title);

        int id = Integer.parseInt(id_temp);

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
                txtAuthor.setText("Susan Carter");
            } else if(id == 3){
                is = getActivity().getResources().openRawResource(R.raw.air_ballons);
                txtAuthor.setText("Susan Carter");
            } else if(id == 4){
                is = getActivity().getResources().openRawResource(R.raw.airplanes);
                txtAuthor.setText("Susan Carter");
            } else if(id == 5){
                is = getActivity().getResources().openRawResource(R.raw.alligators);
                txtAuthor.setText("Susan Carter");
            } else if(id == 6){
                is = getActivity().getResources().openRawResource(R.raw.american_crawl);
                txtAuthor.setText("Daniel Abraham");
            } else if(id == 7){
                is = getActivity().getResources().openRawResource(R.raw.americas_first_scuptor);
                txtAuthor.setText("Susan Carter");
            } else if(id == 8){
                is = getActivity().getResources().openRawResource(R.raw.an_adventure);
                txtAuthor.setText("Susan Carter");
            } else if(id == 9){
                is = getActivity().getResources().openRawResource(R.raw.bears);
                txtAuthor.setText("Susan Carter");
            } else if(id == 10){
                is = getActivity().getResources().openRawResource(R.raw.beds);
                txtAuthor.setText("Susan Carter");
            } else if(id == 11){
                is = getActivity().getResources().openRawResource(R.raw.bella_hides);
                txtAuthor.setText("Susan Carter");
            } else if(id == 12){
                is = getActivity().getResources().openRawResource(R.raw.big_city_noise);
                txtAuthor.setText("Susan Carter");
            } else if(id == 13){
                is = getActivity().getResources().openRawResource(R.raw.birds);
                txtAuthor.setText("Susan Carter");
            } else if(id == 14){
                is = getActivity().getResources().openRawResource(R.raw.birds_nest);
                txtAuthor.setText("Susan Carter");
            } else if(id == 15) {
                is = getActivity().getResources().openRawResource(R.raw.bullied);
                txtAuthor.setText("Susan Carter");
            } else if(id == 16) {
                is = getActivity().getResources().openRawResource(R.raw.butterfly);
                txtAuthor.setText("Susan Carter");
            } else if(id == 17) {
                is = getActivity().getResources().openRawResource(R.raw.by_the_water);
                txtAuthor.setText("Susan Carter");
            } else if(id == 18) {
                is = getActivity().getResources().openRawResource(R.raw.campfire);
                txtAuthor.setText("Susan Carter");
            } else if(id == 19) {
                is = getActivity().getResources().openRawResource(R.raw.canned_goods);
                txtAuthor.setText("Susan Carter");
            } else if(id == 20) {
                is = getActivity().getResources().openRawResource(R.raw.dandelions);
                txtAuthor.setText("Susan Carter");
            } else if(id == 21) {
                is = getActivity().getResources().openRawResource(R.raw.different_type_of_people);
                txtAuthor.setText("Susan Carter");
            } else if(id == 22) {
                is = getActivity().getResources().openRawResource(R.raw.dogs);
                txtAuthor.setText("Susan Carter");
            } else if(id == 23) {
                is = getActivity().getResources().openRawResource(R.raw.empress_of_the_blues);
                txtAuthor.setText("Susan Carter");
            } else if(id == 24) {
                is = getActivity().getResources().openRawResource(R.raw.fish);
                txtAuthor.setText("Susan Carter");
            } else if(id == 25) {
                is = getActivity().getResources().openRawResource(R.raw.flags);
                txtAuthor.setText("Susan Carter");
            } else if(id == 26) {
                is = getActivity().getResources().openRawResource(R.raw.fried);
                txtAuthor.setText("Susan Carter");
            } else if(id == 27) {
                is = getActivity().getResources().openRawResource(R.raw.grass);
                txtAuthor.setText("Susan Carter");
            } else if(id == 28) {
                is = getActivity().getResources().openRawResource(R.raw.green_grass);
                txtAuthor.setText("Susan Carter");
            } else if(id == 29) {
                is = getActivity().getResources().openRawResource(R.raw.griffin);
                txtAuthor.setText("Susan Carter");
            } else if(id == 30) {
                is = getActivity().getResources().openRawResource(R.raw.griffins_talent);
                txtAuthor.setText("Susan Carter");
            } else if(id == 31) {
                is = getActivity().getResources().openRawResource(R.raw.helicopters);
                txtAuthor.setText("Susan Carter");
            } else if(id == 32) {
                is = getActivity().getResources().openRawResource(R.raw.houses);
                txtAuthor.setText("Susan Carter");
            } else if(id == 33) {
                is = getActivity().getResources().openRawResource(R.raw.how_are_you);
                txtAuthor.setText("Susan Carter");
            } else if(id == 34) {
                is = getActivity().getResources().openRawResource(R.raw.how_seeds_grow);
                txtAuthor.setText("Susan Carter");
            } else if(id == 35) {
                is = getActivity().getResources().openRawResource(R.raw.how_warthog_lives);
                txtAuthor.setText("Susan Carter");
            } else if(id == 36) {
                is = getActivity().getResources().openRawResource(R.raw.how_worms_get_inside_apple);
                txtAuthor.setText("Susan Carter");
            } else if(id == 37) {
                is = getActivity().getResources().openRawResource(R.raw.humans);
                txtAuthor.setText("Susan Carter");
            } else if(id == 38) {
                is = getActivity().getResources().openRawResource(R.raw.i_fly);
                txtAuthor.setText("Susan Carter");
            } else if(id == 39) {
                is = getActivity().getResources().openRawResource(R.raw.ice_fishing);
                txtAuthor.setText("Susan Carter");
            } else if(id == 40) {
                is = getActivity().getResources().openRawResource(R.raw.julian);
                txtAuthor.setText("Susan Carter");
            } else if(id == 41) {
                is = getActivity().getResources().openRawResource(R.raw.land_of_africa);
                txtAuthor.setText("Susan Carter");
            } else if(id == 42) {
                is = getActivity().getResources().openRawResource(R.raw.leonardo);
                txtAuthor.setText("Susan Carter");
            } else if(id == 43) {
                is = getActivity().getResources().openRawResource(R.raw.lord_of_olympus);
                txtAuthor.setText("Susan Carter");
            } else if(id == 44) {
                is = getActivity().getResources().openRawResource(R.raw.money);
                txtAuthor.setText("Susan Carter");
            } else if(id == 45) {
                is = getActivity().getResources().openRawResource(R.raw.my_family);
                txtAuthor.setText("Susan Carter");
            } else if(id == 46) {
                is = getActivity().getResources().openRawResource(R.raw.my_friend);
                txtAuthor.setText("Susan Carter");
            } else if(id == 47) {
                is = getActivity().getResources().openRawResource(R.raw.my_house);
                txtAuthor.setText("Susan Carter");
            } else if(id == 48) {
                is = getActivity().getResources().openRawResource(R.raw.new_shoes_for_mandy);
                txtAuthor.setText("Susan Carter");
            } else if(id == 49) {
                is = getActivity().getResources().openRawResource(R.raw.one_hundred_dollars);
                txtAuthor.setText("Susan Carter");
            } //else if(id == 50) {
//                is = getActivity().getResources().openRawResource(R.raw.paper);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 51) {
//                is = getActivity().getResources().openRawResource(R.raw.parts_of_piano);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 52) {
//                is = getActivity().getResources().openRawResource(R.raw.paul_cooks);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 53) {
//                is = getActivity().getResources().openRawResource(R.raw.rainy_day);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 54) {
//                is = getActivity().getResources().openRawResource(R.raw.running);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 55) {
//                is = getActivity().getResources().openRawResource(R.raw.seeing_stars);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 56) {
//                is = getActivity().getResources().openRawResource(R.raw.sky);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 57) {
//                is = getActivity().getResources().openRawResource(R.raw.soap);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 58) {
//                is = getActivity().getResources().openRawResource(R.raw.soda_pop);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 59) {
//                is = getActivity().getResources().openRawResource(R.raw.story_of_lord_sandwich);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 60) {
//                is = getActivity().getResources().openRawResource(R.raw.talia);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 61) {
//                is = getActivity().getResources().openRawResource(R.raw.taste);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 62) {
//                is = getActivity().getResources().openRawResource(R.raw.tea);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 63) {
//                is = getActivity().getResources().openRawResource(R.raw.television);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 64) {
//                is = getActivity().getResources().openRawResource(R.raw.tennis);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 65) {
//                is = getActivity().getResources().openRawResource(R.raw.the_20);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 66) {
//                is = getActivity().getResources().openRawResource(R.raw.the_blow_dryer);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 67) {
//                is = getActivity().getResources().openRawResource(R.raw.the_cat_the_rooster_and_the_young_mouse);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 68) {
//                is = getActivity().getResources().openRawResource(R.raw.the_dog_and_the_manger);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 69) {
//                is = getActivity().getResources().openRawResource(R.raw.the_donkey_in_the_lions_skin);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 70) {
//                is = getActivity().getResources().openRawResource(R.raw.the_drive);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 71) {
//                is = getActivity().getResources().openRawResource(R.raw.the_fog_and_the_fox);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 72) {
//                is = getActivity().getResources().openRawResource(R.raw.the_fox_and_the_crow);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 73) {
//                is = getActivity().getResources().openRawResource(R.raw.the_fox_and_the_grapes);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 74) {
//                is = getActivity().getResources().openRawResource(R.raw.the_hawaian_people_song);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 75) {
//                is = getActivity().getResources().openRawResource(R.raw.the_heart);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 76) {
//                is = getActivity().getResources().openRawResource(R.raw.the_interview);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 77) {
//                is = getActivity().getResources().openRawResource(R.raw.the_lion_and_the_mouse);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 78) {
//                is = getActivity().getResources().openRawResource(R.raw.the_mice_in_council);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 79) {
//                is = getActivity().getResources().openRawResource(R.raw.the_music);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 80) {
//                is = getActivity().getResources().openRawResource(R.raw.the_rabbit_and_the_turtle);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 81) {
//                is = getActivity().getResources().openRawResource(R.raw.the_red_carpet_treatment);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 82) {
//                is = getActivity().getResources().openRawResource(R.raw.the_rent_man);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 83) {
//                is = getActivity().getResources().openRawResource(R.raw.the_rooster_and_the_ax);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 84) {
//                is = getActivity().getResources().openRawResource(R.raw.the_rooster_and_the_jewel);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 85) {
//                is = getActivity().getResources().openRawResource(R.raw.the_singing_bird);
//            } else if(id == 86) {
//                is = getActivity().getResources().openRawResource(R.raw.the_sky);
//            } else if(id == 87) {
//                is = getActivity().getResources().openRawResource(R.raw.the_tree_and_the_ax);
//            } else if(id == 88) {
//                is = getActivity().getResources().openRawResource(R.raw.the_united_states);
//            } else if(id == 89) {
//                is = getActivity().getResources().openRawResource(R.raw.the_wind_and_the_sun);
//            } else if(id == 90) {
//                is = getActivity().getResources().openRawResource(R.raw.tie_your_shoes);
//            } else if(id == 91) {
//                is = getActivity().getResources().openRawResource(R.raw.time_to);
//            } else if(id == 92) {
//                is = getActivity().getResources().openRawResource(R.raw.trees);
//            } else if(id == 93) {
//                is = getActivity().getResources().openRawResource(R.raw.what_number);
//            } else if(id == 94) {
//                is = getActivity().getResources().openRawResource(R.raw.what_time_is_it);
//            } else if(id == 95) {
//                is = getActivity().getResources().openRawResource(R.raw.where_are_my_glasses);
//            } else if(id == 96) {
//                is = getActivity().getResources().openRawResource(R.raw.yellowstone_national_park);
//            } else if(id == 97) {
//                is = getActivity().getResources().openRawResource(R.raw.zach);
//            }

        }else if(level.equals("intermediate")){
            if(id == 0){
                //array is always starts at 0;
                is = getActivity().getResources().openRawResource(R.raw.a_cold_day);
                txtAuthor.setText("Susan Carter");
            } else if(id == 1){
                is = getActivity().getResources().openRawResource(R.raw.a_mystery);
                txtAuthor.setText("Susan Carter");
            } else if(id == 2){
                is = getActivity().getResources().openRawResource(R.raw.abraham_lincoln);
                txtAuthor.setText("Susan Carter");
            } else if(id == 3){
                is = getActivity().getResources().openRawResource(R.raw.amazon_rainforest);
                txtAuthor.setText("Susan Carter");
            } else if(id == 4){
                is = getActivity().getResources().openRawResource(R.raw.american_indian_names);
                txtAuthor.setText("Susan Carter");
            } else if(id == 5){
                is = getActivity().getResources().openRawResource(R.raw.americas_first_scuptor);
                txtAuthor.setText("Susan Carter");
            } else if(id == 6){
                is = getActivity().getResources().openRawResource(R.raw.americas_first_woman);
                txtAuthor.setText("Susan Carter");
            } else if(id == 7){
                is = getActivity().getResources().openRawResource(R.raw.arctic_fox);
                txtAuthor.setText("Susan Carter");
            } else if(id == 8){
                is = getActivity().getResources().openRawResource(R.raw.bail);
                txtAuthor.setText("Susan Carter");
            } else if(id == 9){
                is = getActivity().getResources().openRawResource(R.raw.banks);
                txtAuthor.setText("Susan Carter");
            } else if(id == 10){
                is = getActivity().getResources().openRawResource(R.raw.benjamin_franklin);
                txtAuthor.setText("Susan Carter");
            } else if(id == 11){
                is = getActivity().getResources().openRawResource(R.raw.birdsongs);
                txtAuthor.setText("Susan Carter");
            } else if(id == 12){
                is = getActivity().getResources().openRawResource(R.raw.black_american_author);
                txtAuthor.setText("Susan Carter");
            } else if(id == 13){
                is = getActivity().getResources().openRawResource(R.raw.black_bears);
                txtAuthor.setText("Susan Carter");
            } else if(id == 14){
                is = getActivity().getResources().openRawResource(R.raw.blizzard_un_birmingha);
                txtAuthor.setText("Susan Carter");
            } else if(id == 15) {
                is = getActivity().getResources().openRawResource(R.raw.blue_whale);
                txtAuthor.setText("Susan Carter");
            } else if(id == 16) {
                is = getActivity().getResources().openRawResource(R.raw.butterflies);
                txtAuthor.setText("Susan Carter");
            } else if(id == 17) {
                is = getActivity().getResources().openRawResource(R.raw.cars);
                txtAuthor.setText("Susan Carter");
            } else if(id == 18) {
                is = getActivity().getResources().openRawResource(R.raw.christopher_columbus);
                txtAuthor.setText("Susan Carter");
            } else if(id == 19) {
                is = getActivity().getResources().openRawResource(R.raw.city_noise);
                txtAuthor.setText("Susan Carter");
            } else if(id == 20) {
                is = getActivity().getResources().openRawResource(R.raw.clocks);
                txtAuthor.setText("Susan Carter");
            } else if(id == 21) {
                is = getActivity().getResources().openRawResource(R.raw.coins);
                txtAuthor.setText("Susan Carter");
            } else if(id == 22) {
                is = getActivity().getResources().openRawResource(R.raw.common_loon);
                txtAuthor.setText("Susan Carter");
            } else if(id == 23) {
                is = getActivity().getResources().openRawResource(R.raw.corn);
                txtAuthor.setText("Susan Carter");
            } else if(id == 24) {
                is = getActivity().getResources().openRawResource(R.raw.counting);
                txtAuthor.setText("Susan Carter");
            } else if(id == 25) {
                is = getActivity().getResources().openRawResource(R.raw.cricket);
                txtAuthor.setText("Susan Carter");
            } else if(id == 26) {
                is = getActivity().getResources().openRawResource(R.raw.discovery_of_ice_cream);
                txtAuthor.setText("Susan Carter");
            } else if(id == 27) {
                is = getActivity().getResources().openRawResource(R.raw.doctor_in_chicago);
                txtAuthor.setText("Susan Carter");
            } else if(id == 28) {
                is = getActivity().getResources().openRawResource(R.raw.dunkin_donuts);
                txtAuthor.setText("Susan Carter");
            } else if(id == 29) {
                is = getActivity().getResources().openRawResource(R.raw.easter_island);
                txtAuthor.setText("Susan Carter");
            } else if(id == 30) {
                is = getActivity().getResources().openRawResource(R.raw.garlics);
                txtAuthor.setText("Susan Carter");
            } else if(id == 31) {
                is = getActivity().getResources().openRawResource(R.raw.halloween);
                txtAuthor.setText("Susan Carter");
            } else if(id == 32) {
                is = getActivity().getResources().openRawResource(R.raw.helicopters);
                txtAuthor.setText("Susan Carter");
            } else if(id == 33) {
                is = getActivity().getResources().openRawResource(R.raw.hibernation_and_estivation);
                txtAuthor.setText("Susan Carter");
            } else if(id == 34) {
                is = getActivity().getResources().openRawResource(R.raw.houses);
                txtAuthor.setText("Susan Carter");
            } else if(id == 35) {
                is = getActivity().getResources().openRawResource(R.raw.how_to_be_a_pig);
                txtAuthor.setText("Susan Carter");
            } else if(id == 36) {
                is = getActivity().getResources().openRawResource(R.raw.john_cooks);
                txtAuthor.setText("Susan Carter");
            } else if(id == 37) {
                is = getActivity().getResources().openRawResource(R.raw.komodo_dragon);
                txtAuthor.setText("Susan Carter");
            } else if(id == 38) {
                is = getActivity().getResources().openRawResource(R.raw.largest_creature_on_earth);
                txtAuthor.setText("Susan Carter");
            } else if(id == 39) {
                is = getActivity().getResources().openRawResource(R.raw.late);
                txtAuthor.setText("Susan Carter");
            } else if(id == 40) {
                is = getActivity().getResources().openRawResource(R.raw.lobster);
                txtAuthor.setText("Susan Carter");
            } else if(id == 41) {
                is = getActivity().getResources().openRawResource(R.raw.locks_and_keys);
                txtAuthor.setText("Susan Carter");
            } else if(id == 42) {
                is = getActivity().getResources().openRawResource(R.raw.marco_polo);
                txtAuthor.setText("Susan Carter");
            } else if(id == 43) {
                is = getActivity().getResources().openRawResource(R.raw.mars);
                txtAuthor.setText("Susan Carter");
            } else if(id == 44) {
                is = getActivity().getResources().openRawResource(R.raw.marys_family);
                txtAuthor.setText("Susan Carter");
            } else if(id == 45) {
                is = getActivity().getResources().openRawResource(R.raw.moorish_dance);
                txtAuthor.setText("Susan Carter");
            } else if(id == 46) {
                is = getActivity().getResources().openRawResource(R.raw.movie_ratings);
                txtAuthor.setText("Susan Carter");
            } else if(id == 47) {
                is = getActivity().getResources().openRawResource(R.raw.mummy);
                txtAuthor.setText("Susan Carter");
            } else if(id == 48) {
                is = getActivity().getResources().openRawResource(R.raw.niagara_falls);
                txtAuthor.setText("Susan Carter");
            } else if(id == 49) {
                is = getActivity().getResources().openRawResource(R.raw.nuts);
                txtAuthor.setText("Susan Carter");
            } //else if(id == 50) {
//                is = getActivity().getResources().openRawResource(R.raw.olympic_game);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 51) {
//                is = getActivity().getResources().openRawResource(R.raw.panda);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 52) {
//                is = getActivity().getResources().openRawResource(R.raw.penquins);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 53) {
//                is = getActivity().getResources().openRawResource(R.raw.photography);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 54) {
//                is = getActivity().getResources().openRawResource(R.raw.rainbows);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 55) {
//                is = getActivity().getResources().openRawResource(R.raw.roller_skates);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 56) {
//                is = getActivity().getResources().openRawResource(R.raw.saltwater);
//            } else if(id == 57) {
//                is = getActivity().getResources().openRawResource(R.raw.seamounts);
//            } else if(id == 58) {
//                is = getActivity().getResources().openRawResource(R.raw.sequoya);
//            } else if(id == 59) {
//                is = getActivity().getResources().openRawResource(R.raw.shirley_chsholm);
//            } else if(id == 60) {
//                is = getActivity().getResources().openRawResource(R.raw.silk_road);
//            } else if(id == 61) {
//                is = getActivity().getResources().openRawResource(R.raw.snowball_in_space);
//            } else if(id == 62) {
//                is = getActivity().getResources().openRawResource(R.raw.soccer);
//            } else if(id == 63) {
//                is = getActivity().getResources().openRawResource(R.raw.spider);
//            } else if(id == 64) {
//                is = getActivity().getResources().openRawResource(R.raw.stage_coaches);
//            } else if(id == 65) {
//                is = getActivity().getResources().openRawResource(R.raw.terrain);
//            } else if(id == 66) {
//                is = getActivity().getResources().openRawResource(R.raw.the_brenners);
//            } else if(id == 67) {
//                is = getActivity().getResources().openRawResource(R.raw.the_bus_driver);
//            } else if(id == 68) {
//                is = getActivity().getResources().openRawResource(R.raw.the_cactus);
//            } else if(id == 69) {
//                is = getActivity().getResources().openRawResource(R.raw.the_color_green);
//            } else if(id == 70) {
//                is = getActivity().getResources().openRawResource(R.raw.the_flu);
//            } else if(id == 71) {
//                is = getActivity().getResources().openRawResource(R.raw.the_new_school);
//            } else if(id == 72) {
//                is = getActivity().getResources().openRawResource(R.raw.the_park);
//            } else if(id == 73) {
//                is = getActivity().getResources().openRawResource(R.raw.the_story_of_marian);
//            } else if(id == 74) {
//                is = getActivity().getResources().openRawResource(R.raw.the_sun);
//            } else if(id == 75) {
//                is = getActivity().getResources().openRawResource(R.raw.the_us_census);
//            } else if(id == 76) {
//                is = getActivity().getResources().openRawResource(R.raw.the_white_house);
//            } else if(id == 77) {
//                is = getActivity().getResources().openRawResource(R.raw.three_politic_warr);
//            } else if(id == 78) {
//                is = getActivity().getResources().openRawResource(R.raw.time_with_granpa);
//            } else if(id == 79) {
//                is = getActivity().getResources().openRawResource(R.raw.transportation);
//            } else if(id == 80) {
//                is = getActivity().getResources().openRawResource(R.raw.troglodytes);
//            } else if(id == 81) {
//                is = getActivity().getResources().openRawResource(R.raw.two_best_known);
//            } else if(id == 82) {
//                is = getActivity().getResources().openRawResource(R.raw.umbrellas);
//            } else if(id == 83) {
//                is = getActivity().getResources().openRawResource(R.raw.use_of_peanuts);
//            } else if(id == 84) {
//                is = getActivity().getResources().openRawResource(R.raw.use_of_tounge_in_animal);
//            } else if(id == 85) {
//                is = getActivity().getResources().openRawResource(R.raw.vet_emergency);
//            } else if(id == 86) {
//                is = getActivity().getResources().openRawResource(R.raw.water);
//            } else if(id == 87) {
//                is = getActivity().getResources().openRawResource(R.raw.what_is_a_wasp);
//            } else if(id == 88) {
//                is = getActivity().getResources().openRawResource(R.raw.why_barn_is_always);
//            } else if(id == 89) {
//                is = getActivity().getResources().openRawResource(R.raw.worth_working_for);
//            }


        }else if(level.equals("hard")){
            if(id == 0){
                //array is always starts at 0;
                is = getActivity().getResources().openRawResource(R.raw.a_christmas_in_march);
                txtAuthor.setText("Susan Carter");
            } else if(id == 1){
                is = getActivity().getResources().openRawResource(R.raw.a_daring_escape);
                txtAuthor.setText("Susan Carter");
            } else if(id == 2){
                is = getActivity().getResources().openRawResource(R.raw.accused);
                txtAuthor.setText("Susan Carter");
            } else if(id == 3){
                is = getActivity().getResources().openRawResource(R.raw.advance_critical_reading);
                txtAuthor.setText("Susan Carter");
            } else if(id == 4){
                is = getActivity().getResources().openRawResource(R.raw.ana_finds_an);
                txtAuthor.setText("Susan Carter");
            } else if(id == 5){
                is = getActivity().getResources().openRawResource(R.raw.asian_carp);
                txtAuthor.setText("Susan Carter");
            } else if(id == 6){
                is = getActivity().getResources().openRawResource(R.raw.bathtubs);
                txtAuthor.setText("Susan Carter");
            } else if(id == 7){
                is = getActivity().getResources().openRawResource(R.raw.bb);
                txtAuthor.setText("Susan Carter");
            } else if(id == 8){
                is = getActivity().getResources().openRawResource(R.raw.black_friday);
                txtAuthor.setText("Susan Carter");
            } else if(id == 9){
                is = getActivity().getResources().openRawResource(R.raw.can_windmills_stalk);
                txtAuthor.setText("Susan Carter");
            } else if(id == 10){
                is = getActivity().getResources().openRawResource(R.raw.canopy_of_nature);
                txtAuthor.setText("Susan Carter");
            } else if(id == 11){
                is = getActivity().getResources().openRawResource(R.raw.carnivorous);
                txtAuthor.setText("Susan Carter");
            } else if(id == 12){
                is = getActivity().getResources().openRawResource(R.raw.cells);
                txtAuthor.setText("Susan Carter");
            } else if(id == 13){
                is = getActivity().getResources().openRawResource(R.raw.chocolate);
                txtAuthor.setText("Susan Carter");
            } else if(id == 14) {
                is = getActivity().getResources().openRawResource(R.raw.city_girl);
                txtAuthor.setText("Susan Carter");
            } else if(id == 15) {
                is = getActivity().getResources().openRawResource(R.raw.clean_water_act);
                txtAuthor.setText("Susan Carter");
            } else if(id == 16) {
                is = getActivity().getResources().openRawResource(R.raw.collection_of_folklore);
                txtAuthor.setText("Susan Carter");
            } else if(id == 17) {
                is = getActivity().getResources().openRawResource(R.raw.dreams);
                txtAuthor.setText("Susan Carter");
            } else if(id == 18) {
                is = getActivity().getResources().openRawResource(R.raw.eating_out);
                txtAuthor.setText("Susan Carter");
            } else if(id == 19) {
                is = getActivity().getResources().openRawResource(R.raw.el_dia_de_san_juan);
                txtAuthor.setText("Susan Carter");
            } else if(id == 20) {
                is = getActivity().getResources().openRawResource(R.raw.europe);
                txtAuthor.setText("Susan Carter");
            } else if(id == 21) {
                is = getActivity().getResources().openRawResource(R.raw.fingerprints);
                txtAuthor.setText("Susan Carter");
            } else if(id == 22) {
                is = getActivity().getResources().openRawResource(R.raw.florida_manatee);
                txtAuthor.setText("Susan Carter");
            } else if(id == 23) {
                is = getActivity().getResources().openRawResource(R.raw.flower_power);
                txtAuthor.setText("Susan Carter");
            } else if(id == 24) {
                is = getActivity().getResources().openRawResource(R.raw.fortune_tellers);
                txtAuthor.setText("Susan Carter");
            } else if(id == 25) {
                is = getActivity().getResources().openRawResource(R.raw.france);
                txtAuthor.setText("Susan Carter");
            } else if(id == 26) {
                is = getActivity().getResources().openRawResource(R.raw.galaxies);
                txtAuthor.setText("Susan Carter");
            } else if(id == 27) {
                is = getActivity().getResources().openRawResource(R.raw.gilrays_flower_pot);
                txtAuthor.setText("Susan Carter");
            } else if(id == 28) {
                is = getActivity().getResources().openRawResource(R.raw.goodluck_for_the_good);
                txtAuthor.setText("Susan Carter");
            } else if(id == 29) {
                is = getActivity().getResources().openRawResource(R.raw.guermos_surprise);
                txtAuthor.setText("Susan Carter");
            } else if(id == 30) {
                is = getActivity().getResources().openRawResource(R.raw.house_around_the_wordd);
                txtAuthor.setText("Susan Carter");
            } else if(id == 31) {
                is = getActivity().getResources().openRawResource(R.raw.interstate_highways);
                txtAuthor.setText("Susan Carter");
            } else if(id == 32) {
                is = getActivity().getResources().openRawResource(R.raw.juan_ponce);
                txtAuthor.setText("Susan Carter");
            } else if(id == 33) {
                is = getActivity().getResources().openRawResource(R.raw.just_one_touch);
                txtAuthor.setText("Susan Carter");
            } else if(id == 34) {
                is = getActivity().getResources().openRawResource(R.raw.kok);
                txtAuthor.setText("Susan Carter");
            } else if(id == 35) {
                is = getActivity().getResources().openRawResource(R.raw.lightning_and_thunder);
                txtAuthor.setText("Susan Carter");
            } else if(id == 36) {
                is = getActivity().getResources().openRawResource(R.raw.love_train);
                txtAuthor.setText("Susan Carter");
            } else if(id == 37) {
                is = getActivity().getResources().openRawResource(R.raw.marias_beatiful);
                txtAuthor.setText("Susan Carter");
            } else if(id == 38) {
                is = getActivity().getResources().openRawResource(R.raw.meteors);
                txtAuthor.setText("Susan Carter");
            } else if(id == 39) {
                is = getActivity().getResources().openRawResource(R.raw.mexico);
                txtAuthor.setText("Susan Carter");
            } else if(id == 40) {
                is = getActivity().getResources().openRawResource(R.raw.moongooes);
                txtAuthor.setText("Susan Carter");
            } else if(id == 41) {
                is = getActivity().getResources().openRawResource(R.raw.mosquitoes);
                txtAuthor.setText("Susan Carter");
            } else if(id == 42) {
                is = getActivity().getResources().openRawResource(R.raw.mothers_day);
                txtAuthor.setText("Susan Carter");
            } else if(id == 43) {
                is = getActivity().getResources().openRawResource(R.raw.oil_spill);
                txtAuthor.setText("Susan Carter");
            } else if(id == 44) {
                is = getActivity().getResources().openRawResource(R.raw.on_the_right_track);
                txtAuthor.setText("Susan Carter");
            } else if(id == 45) {
                is = getActivity().getResources().openRawResource(R.raw.oversmart_is_badluck);
                txtAuthor.setText("Susan Carter");
            } else if(id == 46) {
                is = getActivity().getResources().openRawResource(R.raw.planet_mars);
                txtAuthor.setText("Susan Carter");
            } else if(id == 47) {
                is = getActivity().getResources().openRawResource(R.raw.pollutions);
                txtAuthor.setText("Susan Carter");
            } else if(id == 48) {
                is = getActivity().getResources().openRawResource(R.raw.potato_chips);
                txtAuthor.setText("Susan Carter");
            } else if(id == 49) {
                is = getActivity().getResources().openRawResource(R.raw.raisins);
                txtAuthor.setText("Susan Carter");
            } //else if(id == 50) {
//                is = getActivity().getResources().openRawResource(R.raw.round_and_round);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 51) {
//                is = getActivity().getResources().openRawResource(R.raw.seatbelts);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 52) {
//                is = getActivity().getResources().openRawResource(R.raw.seeing_clearly);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 53) {
//                is = getActivity().getResources().openRawResource(R.raw.sharks);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 54) {
//                is = getActivity().getResources().openRawResource(R.raw.sleep);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 55) {
//                is = getActivity().getResources().openRawResource(R.raw.suffrage);
//                txtAuthor.setText("Susan Carter");
//            } else if(id == 56) {
//                is = getActivity().getResources().openRawResource(R.raw.take_care_of_your_dog);
//            } else if(id == 57) {
//                is = getActivity().getResources().openRawResource(R.raw.taro_and_the_tofu);
//            } else if(id == 58) {
//                is = getActivity().getResources().openRawResource(R.raw.tattoo);
//            } else if(id == 59) {
//                is = getActivity().getResources().openRawResource(R.raw.the_case_of_the_unknown);
//            } else if(id == 60) {
//                is = getActivity().getResources().openRawResource(R.raw.the_cat_that_walked);
//            } else if(id == 61) {
//                is = getActivity().getResources().openRawResource(R.raw.the_clever_wizardx);
//            } else if(id == 62) {
//                is = getActivity().getResources().openRawResource(R.raw.the_coliseum);
//            } else if(id == 63) {
//                is = getActivity().getResources().openRawResource(R.raw.the_cowardly_lion);
//            } else if(id == 64) {
//                is = getActivity().getResources().openRawResource(R.raw.the_first_words);
//            } else if(id == 65) {
//                is = getActivity().getResources().openRawResource(R.raw.the_great_buddha);
//            } else if(id == 66) {
//                is = getActivity().getResources().openRawResource(R.raw.the_mini_problem);
//            } else if(id == 67) {
//                is = getActivity().getResources().openRawResource(R.raw.the_secret_soldier);
//            } else if(id == 68) {
//                is = getActivity().getResources().openRawResource(R.raw.the_son);
//            } else if(id == 69) {
//                is = getActivity().getResources().openRawResource(R.raw.the_storm);
//            } else if(id == 70) {
//                is = getActivity().getResources().openRawResource(R.raw.the_story_of_keesh);
//            } else if(id == 71) {
//                is = getActivity().getResources().openRawResource(R.raw.the_transfer);
//            } else if(id == 72) {
//                is = getActivity().getResources().openRawResource(R.raw.tracks);
//            } else if(id == 73) {
//                is = getActivity().getResources().openRawResource(R.raw.tyrannusirus_trex);
//            } else if(id == 74) {
//                is = getActivity().getResources().openRawResource(R.raw.venus);
//            } else if(id == 75) {
//                is = getActivity().getResources().openRawResource(R.raw.wampum);
//            } else if(id == 76) {
//                is = getActivity().getResources().openRawResource(R.raw.wanga);
//            } else if(id == 77) {
//                is = getActivity().getResources().openRawResource(R.raw.what_a_pig_ture);
//            } else if (id == 78) {
//                is = getActivity().getResources().openRawResource(R.raw.wild);
//            }
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

    @Override
    public void onStop() {
        super.onStop();

        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
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

        if (db.hasObject(id_temp)) {

            menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmarkedicon));
        }


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
                   // menuItem.setTitle("Stop");
                    String toSpeak = txtContent.getText().toString();
                    textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
                    menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(),R.drawable.speech));
                }else{
                    menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(),R.drawable.defaultspeaker));
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

        SharedPreferences pref = getActivity().getSharedPreferences("bookmark",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!db.hasObject(id_temp)) {
            db.insertDataBookmarks(title, id_temp, level);
            check = true;
            menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(),R.drawable.bookmarkedicon));

        } else {
            db.deleteDataBookmarks(id_temp);
            check = false;

          //  Toast.makeText(getActivity(), "Removed to bookmarks", Toast.LENGTH_SHORT).show();

            editor.apply();
            menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(),R.drawable.bookmarkicon));
        }

    }


}
