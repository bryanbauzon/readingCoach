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
import sebastian.devmonkey.capstoneproject.activity.MainActivity;
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


        SharedPreferences preferences = getActivity().getSharedPreferences("settings",MODE_PRIVATE);

        //  SharedPreferences preferences = getActivity().getPreferences("settings",MODE_PRIVATE);
        String valueFont = preferences.getString("activeFont",null);


        if(valueFont != null){
            if(valueFont == "fonttype1"){
                GlobalVariable.font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/serif.ttf");

            }else if(valueFont == "fonttype2"){
                GlobalVariable.font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/sanserif.ttf");
               }

        }else{
            GlobalVariable.font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/serif.ttf");

          }

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
                    txtAuthor.setText(easy.author[x]);
                }
            }



        }else if(level.equals("intermediate")){
            for(int x = 0; x <= intermediate.story.length;x++){
                if(id==x){
                    txtContent.setText(intermediate.story[x]);
                    txtAuthor.setText(intermediate.author[x]);
                }


            }



        }else  {
            for(int x = 0;x <= hard.story.length; x++){
                if(id == x){
                    txtContent.setText(hard.story[x]);
                }
                txtAuthor.setText("Susan Carter");
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
