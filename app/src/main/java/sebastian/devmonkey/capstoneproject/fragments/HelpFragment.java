package sebastian.devmonkey.capstoneproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.SliderAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HelpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;
    private  Button finish,back;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    private int currentPage;

    private OnFragmentInteractionListener mListener;

    public HelpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpFragment newInstance(String param1, String param2) {
        HelpFragment fragment = new HelpFragment();
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


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

         finish = (Button)getView().findViewById(R.id.finishHelp);
         back = (Button)getView().findViewById(R.id.backHelp);
        mSliderViewPager = (ViewPager)getView().findViewById(R.id.slideViewPagerHelp);
        mDotLayout =  (LinearLayout)getView().findViewById(R.id.dotLayoutHelp);


        finish.setText(null);
        back.setText(null);
        finish.setEnabled(false);
        back.setEnabled(false);
        dots = new TextView[8];

        sliderAdapter = new SliderAdapter(getContext());
        mSliderViewPager.setAdapter(sliderAdapter);

        addDotIndicator(0);
        mSliderViewPager.addOnPageChangeListener(viewListener);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSliderViewPager.setCurrentItem(currentPage - 1);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finish.getText().toString().equals("Next")){
                    mSliderViewPager.setCurrentItem(currentPage + 1);
                }
            }
        });

    }
    public void addDotIndicator(int position){

        mDotLayout.removeAllViews();
        for(int i = 0 ; i < dots.length; i++){
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transparentWhite));
            mDotLayout.addView(dots[i]);
        }
        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addDotIndicator(position);
        currentPage = position;
        if(position == 0  ){
            invisibleButton();

        }else if(currentPage == dots.length - 1){
            invisibleButton();

        }
        else{
            invisibleButton();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
};

    private void invisibleButton(){
        back.setVisibility(View.INVISIBLE);
        finish.setVisibility(View.INVISIBLE);
        finish.setText(null);
        back.setText(null);
        back.setEnabled(false);
        finish.setEnabled(false);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false);
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
}
