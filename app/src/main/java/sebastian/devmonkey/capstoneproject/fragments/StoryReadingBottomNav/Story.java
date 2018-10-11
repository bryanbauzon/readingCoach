package sebastian.devmonkey.capstoneproject.fragments.StoryReadingBottomNav;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import sebastian.devmonkey.capstoneproject.fragments.BookmarksFragment;
import sebastian.devmonkey.capstoneproject.fragments.SettingsFragment;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;
import sebastian.devmonkey.capstoneproject.stories.Easy;
import sebastian.devmonkey.capstoneproject.stories.Hard;
import sebastian.devmonkey.capstoneproject.stories.Intermediate;

import static android.content.Context.MODE_PRIVATE;

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
    SettingsFragment settingsFragment;
    Easy easy;
    Hard hard;
    Intermediate intermediate;



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
        easy = new Easy();
        hard = new Hard();
        intermediate = new Intermediate();

        settingsFragment = new SettingsFragment();
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

        if (GlobalVariable.color == 1){
            view.setBackgroundColor(Color.parseColor("#3f3f3f"));
            txtTitle.setTextColor(Color.WHITE);
            txtContent.setTextColor(Color.WHITE);
            txtAuthor.setTextColor(Color.WHITE);
        } else if(GlobalVariable.color == 2){
            view.setBackgroundColor(Color.parseColor("#d5d5d5"));
            txtContent.setTextColor(Color.parseColor("#383838"));
            txtTitle.setTextColor(Color.parseColor("#383838"));

        }else {
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

            for(int x = 0; x <= easy.story.length; x++){
                if(id == x){
                    txtContent.setText(easy.story[x]);
                    ///Log.println(Integer.toString(x));
                    System.out.println(x);
                }
            }
            txtAuthor.setText("Susan Carter");


        }else if(level.equals("intermediate")){
            for(int x = 0; x <= intermediate.story.length;x++){
                if(id==x){
                    txtContent.setText(intermediate.story[x]);
                }
                txtAuthor.setText("Susan Carter");

            }



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

       /* BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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
        }*/


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

             //   default:


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
