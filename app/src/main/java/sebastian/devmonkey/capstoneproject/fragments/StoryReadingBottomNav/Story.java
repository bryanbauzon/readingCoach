package sebastian.devmonkey.capstoneproject.fragments.StoryReadingBottomNav;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
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
    TextView txtContent, txtTitle;
    TextToSpeech textToSpeech;
    int ctr;

    String level;
    String id_temp, title;
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
                is = getActivity().getResources().openRawResource(R.raw.a_cold_day);
            } else if(id == 1){
                is = getActivity().getResources().openRawResource(R.raw.a_christmas_in_march);
            } else if(id == 2){
                is = getActivity().getResources().openRawResource(R.raw.a_call_to_the_pool);
            } else if(id == 3){
                is = getActivity().getResources().openRawResource(R.raw.a_happy_visitor);
            } else if(id == 4){
                is = getActivity().getResources().openRawResource(R.raw.alligators);
            } else if(id == 5){
                is = getActivity().getResources().openRawResource(R.raw.an_adventure);
            } else if(id == 6){
                is = getActivity().getResources().openRawResource(R.raw.bears);
            } else if(id == 7){
                is = getActivity().getResources().openRawResource(R.raw.beds);
            } else if(id == 8){
                is = getActivity().getResources().openRawResource(R.raw.bella_hides);
            } else if(id == 9){
                is = getActivity().getResources().openRawResource(R.raw.big_city_noise);
            } else if(id == 10){
                is = getActivity().getResources().openRawResource(R.raw.birds);
            } else if(id == 11){
                is = getActivity().getResources().openRawResource(R.raw.butterfly);
            } else if(id == 12){
                is = getActivity().getResources().openRawResource(R.raw.dogs);
            } else if(id == 13){
                is = getActivity().getResources().openRawResource(R.raw.empress_the_blues);
            } else if(id == 14){
                is = getActivity().getResources().openRawResource(R.raw.fish);
            } else if(id == 15){
                is = getActivity().getResources().openRawResource(R.raw.flags);
            } else if (id == 16) {
                is = getActivity().getResources().openRawResource(R.raw.green_grass);
            }

        }else if(level.equals("intermediate")){
            if(id == 0){
                is = getActivity().getResources().openRawResource(R.raw.grifins_talent);
            } else if(id == 1){
                is = getActivity().getResources().openRawResource(R.raw.helicopters);
            } else if(id == 2){
                is = getActivity().getResources().openRawResource(R.raw.houses);
            } else if(id == 3){
                is = getActivity().getResources().openRawResource(R.raw.humans);
            } else if(id == 4){
                is = getActivity().getResources().openRawResource(R.raw.i_fly);
            } else if(id == 5){
                is  = getActivity().getResources().openRawResource(R.raw.ice_fishing);
            } else if(id == 6){
                is = getActivity().getResources().openRawResource(R.raw.julians_work);
            } else if(id == 7){
                is = getActivity().getResources().openRawResource(R.raw.leornardo_da_vinci);
            } else if(id == 8){
                is = getActivity().getResources().openRawResource(R.raw.money);
            } else if(id == 9){
                is = getActivity().getResources().openRawResource(R.raw.my_family);
            } else if(id == 10){
                is = getActivity().getResources().openRawResource(R.raw.my_friend);
            } else if(id == 11){
                is = getActivity().getResources().openRawResource(R.raw.my_house);
            } else if(id == 12){
                is = getActivity().getResources().openRawResource(R.raw.new_shoes_for_mandy);
            } else if(id == 13){
                is = getActivity().getResources().openRawResource(R.raw.one_hundred_dollars);
            } else if(id == 14){
                is = getActivity().getResources().openRawResource(R.raw.paul_cooks);
            } else if(id == 15){
                is = getActivity().getResources().openRawResource(R.raw.rainy_day);
            } else if(id == 16){
                is = getActivity().getResources().openRawResource(R.raw.running);
            }
        }else if(level.equals("hard")){
            if(id == 0){
                is = getActivity().getResources().openRawResource(R.raw.seeing_stars);
            } else if(id == 1){
                is = getActivity().getResources().openRawResource(R.raw.soda_pop);
            } else if(id == 2){
                is = getActivity().getResources().openRawResource(R.raw.taste);
            } else if(id == 3){
                is = getActivity().getResources().openRawResource(R.raw.tea);
            } else if(id == 4){
                is = getActivity().getResources().openRawResource(R.raw.tennis);
            } else if(id == 5){
                is = getActivity().getResources().openRawResource(R.raw.the_blow_driver);
            } else if(id == 6){
                is = getActivity().getResources().openRawResource(R.raw.the_drive);
            } else if(id == 7){
                is = getActivity().getResources().openRawResource(R.raw.the_heart);
            } else if(id == 8){
                is = getActivity().getResources().openRawResource(R.raw.the_interview);
            } else if(id == 9){
                is = getActivity().getResources().openRawResource(R.raw.the_music);
            } else if(id == 10){
                is = getActivity().getResources().openRawResource(R.raw.the_singing_bird);
            } else if(id == 11){
                is = getActivity().getResources().openRawResource(R.raw.time_to);
            } else if(id == 12){
                is = getActivity().getResources().openRawResource(R.raw.trees);
            } else if(id == 13){
                is = getActivity().getResources().openRawResource(R.raw.what_number);
            } else if(id == 14){
                is = getActivity().getResources().openRawResource(R.raw.yellow_stone_national_park);
            }else{
                is = getActivity().getResources().openRawResource(R.raw.zachs_animals);
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