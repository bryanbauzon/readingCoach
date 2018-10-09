package sebastian.devmonkey.capstoneproject.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sebastian.devmonkey.capstoneproject.other.GlobalVariable;
import sebastian.devmonkey.capstoneproject.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int ctr = 0;

    boolean smallB = false,mediumB = false, largeB = false,font1 = false,font2 = false, darkB = false, lightB = false;
    Button small;
    Button medium ;
    Button large;
    Button fontType1;
    Button fontType2;
    Button dark;
    Button light;
    Button lineSpacing;
    Button lineSpacing1 ;
    Button lineSpacing2 ;
    Button pageMargin1;
    Button pageMargin2;
    Button pageMargin3 ;
    Button reset ;


  //  TextView appName,overView;
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
     // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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


//
//        if(valueText.equals("small")){
//            removeIndicatorsTextSize();
//            small.setPaintFlags(large.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            small.setTextColor(getResources().getColor(R.color.orange));
//        }else if(valueText.equals("medium")){
//            removeIndicatorsTextSize();
//            medium.setPaintFlags(large.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            medium.setTextColor(getResources().getColor(R.color.orange));
//        }else if(valueText.equals("large")){
//            removeIndicatorsTextSize();
//            large.setPaintFlags(large.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            large.setTextColor(getResources().getColor(R.color.orange));
//        }else{
//            removeIndicatorsTextSize();
//
//        }
//
////
//
//        if(valueFont.equals("fonttype1")){
//            removeIndicatorsTextSize();
//            fontType1.setPaintFlags(fontType1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            fontType1.setTextColor(getResources().getColor(R.color.orange));
//        }else if(valueFont.equals("fonttype2")){
//            removeIndicatorsTextSize();
//            fontType2.setPaintFlags(fontType1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            fontType2.setTextColor(getResources().getColor(R.color.orange));
//        }else{
//            removeIndicatorsTextSize();
//
//        }
//
//        if(valueTheme.equals("dark")){
//            removeIndicatorsTextSize();
//            dark.setPaintFlags(dark.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            dark.setTextColor(getResources().getColor(R.color.orange));
//        }else if(valueTheme.equals("light")){
//            removeIndicatorsTextSize();
//            light.setPaintFlags(fontType1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            light.setTextColor(getResources().getColor(R.color.orange));
//        }else{
//            removeIndicatorsTextSize();
//        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings,container,false);

         small = view.findViewById(R.id.btnSmall);
         medium = view.findViewById(R.id.btnMedium);
         large = view.findViewById(R.id.btnLarge);
         fontType1 = view.findViewById(R.id.btnFontType1);
         fontType2 = view.findViewById(R.id.btnFontType2);
         dark = view.findViewById(R.id.btnDark);
         light = view.findViewById(R.id.btnLight);
         lineSpacing = view.findViewById(R.id.btnLineSpacing);
         lineSpacing1 = view.findViewById(R.id.btnLineSpacing1);
         lineSpacing2 = view.findViewById(R.id.btnLineSpacing2);
         pageMargin1 = view.findViewById(R.id.btnPageMargin1);
         pageMargin2 = view.findViewById(R.id.btnPageMargin2);
         pageMargin3 = view.findViewById(R.id.btnPageMargin3);
         reset = view.findViewById(R.id.btnReset);

        small.setOnClickListener(this);
        medium.setOnClickListener(this);
        large.setOnClickListener(this);
        fontType1.setOnClickListener(this);
        fontType2.setOnClickListener(this);
        dark.setOnClickListener(this);
        light.setOnClickListener(this);
        pageMargin1.setOnClickListener(this);
        pageMargin2.setOnClickListener(this);
        pageMargin3.setOnClickListener(this);
        lineSpacing.setOnClickListener(this);
        lineSpacing1.setOnClickListener(this);
        lineSpacing2.setOnClickListener(this);
        reset.setOnClickListener(this);

        fontType1.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        fontType2.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        dark.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        light.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));

        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String valueText = preferences.getString("activeText",null);
        String valueFont = preferences.getString("activeFont",null);
        String valueTheme = preferences.getString("activeTheme",null);

        Toast.makeText(getContext(),valueText,Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(),valueFont,Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(),valueTheme,Toast.LENGTH_SHORT).show();


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
    public void onClick(View view) {

        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

     //   SharedPreferences pref = getActivity().getSharedPreferences("setting",MODE_PRIVATE);




        switch (view.getId()){
            case R.id.btnSmall:
                GlobalVariable.fontSize = 15;
                removeIndicatorsTextSize();
                small.setPaintFlags(small.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                small.setTextColor(getResources().getColor(R.color.orange));
                editor.clear();
                editor.putString("activeText","small");
                editor.apply();

                break;
            case R.id.btnMedium:
                GlobalVariable.fontSize = 17;
                removeIndicatorsTextSize();
                medium.setPaintFlags(medium.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                medium.setTextColor(getResources().getColor(R.color.orange));
                editor.clear();
                editor.putString("activeText","medium");
                editor.apply();

                break;

            case R.id.btnLarge:
                GlobalVariable.fontSize = 19;
                removeIndicatorsTextSize();
                large.setPaintFlags(large.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                large.setTextColor(getResources().getColor(R.color.orange));
                editor.clear();

                editor.putString("activeText","large");
                editor.apply();

                break;

            case R.id.btnFontType1:
                GlobalVariable.font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Bold.ttf");
               removeIndicatorFontType();
               fontType1.setPaintFlags(fontType1.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);

                fontType1.setTextColor(getResources().getColor(R.color.orange));
                editor.clear();
                editor.putString("activeFont","fonttype1");
                editor.apply();
                break;

            case R.id.btnFontType2:
                GlobalVariable.font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Italic.ttf");
                removeIndicatorFontType();
                fontType2.setPaintFlags(fontType2.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);

                fontType2.setTextColor(getResources().getColor(R.color.orange));
                editor.clear();
                editor.putString("activeFont","fonttype2");
                editor.apply();
                break;

            case R.id.btnDark:
                GlobalVariable.color  = 1;
                removeIndicatorTheme();
                dark.setPaintFlags(dark.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);

                dark.setTextColor(getResources().getColor(R.color.orange));
                editor.clear();
                editor.putString("activeTheme","light");
                editor.apply();
                break;


            case R.id.btnLight:
                GlobalVariable.color  = 0;
                removeIndicatorTheme();
                light.setPaintFlags(light.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);

                light.setTextColor(getResources().getColor(R.color.orange));
                editor.clear();
                editor.putString("activeTheme","light");
                editor.apply();
                break;


            case R.id.btnPageMargin1:
                GlobalVariable.left = 10;
                GlobalVariable.top = 10;
                GlobalVariable.right = 10;
                GlobalVariable.bottom = 10;


                break;

            case R.id.btnPageMargin2:
                GlobalVariable.left = 20;
                GlobalVariable.top = 20;
                GlobalVariable.right = 20;
                GlobalVariable.bottom = 20;


                break;



            case R.id.btnPageMargin3:
                GlobalVariable.left = 30;
                GlobalVariable.top = 30;
                GlobalVariable.right = 30;
                GlobalVariable.bottom = 30;


                break;

            case R.id.btnLineSpacing:
                GlobalVariable.lineSpacing = 10;

                break;

            case R.id.btnLineSpacing1:
                GlobalVariable.lineSpacing = 12;

                break;

            case R.id.btnLineSpacing2:
                GlobalVariable.lineSpacing = 14;

                break;

            case R.id.btnReset:
                GlobalVariable.fontSize = 15;

                GlobalVariable.font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf");

                GlobalVariable.left = 0;
                GlobalVariable.top = 0;
                GlobalVariable.right = 0;
                GlobalVariable.bottom = 0;

                GlobalVariable.color  = 0;

                GlobalVariable.lineSpacing = 0;



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

    private void removeIndicatorsTextSize(){
        small.setPaintFlags(small.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        medium.setPaintFlags(medium.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        large.setPaintFlags(large.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));





        small.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        medium.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        large.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));



    }
    private void removeIndicatorFontType(){
        fontType1.setPaintFlags(fontType1.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        fontType2.setPaintFlags(fontType2.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        fontType1.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        fontType2.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
    }
    private void removeIndicatorTheme(){

        dark.setPaintFlags(dark.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        light.setPaintFlags(light.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        dark.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        light.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
    }
}
