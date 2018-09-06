package sebastian.devmonkey.capstoneproject.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sebastian.devmonkey.capstoneproject.other.GlobalVariable;
import sebastian.devmonkey.capstoneproject.R;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings,container,false);

        Button small = view.findViewById(R.id.btnSmall);
        Button medium = view.findViewById(R.id.btnMedium);
        Button large = view.findViewById(R.id.btnLarge);
        Button fontType1 = view.findViewById(R.id.btnFontType1);
        Button fontType2 = view.findViewById(R.id.btnFontType2);
        Button dark = view.findViewById(R.id.btnDark);
        Button light = view.findViewById(R.id.btnLight);
        Button lineSpacing = view.findViewById(R.id.btnLineSpacing);
        Button lineSpacing1 = view.findViewById(R.id.btnLineSpacing1);
        Button lineSpacing2 = view.findViewById(R.id.btnLineSpacing2);
        Button pageMargin1 = view.findViewById(R.id.btnPageMargin1);
        Button pageMargin2 = view.findViewById(R.id.btnPageMargin2);
        Button pageMargin3 = view.findViewById(R.id.btnPageMargin3);
        Button reset = view.findViewById(R.id.btnReset);

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


        switch (view.getId()){
            case R.id.btnSmall:
                GlobalVariable.fontSize = 15;
                Toast.makeText(getContext(), GlobalVariable.fontSize + "", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnMedium:
                GlobalVariable.fontSize = 17;
                Toast.makeText(getContext(), GlobalVariable.fontSize + "", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnLarge:
                GlobalVariable.fontSize = 19;
                Toast.makeText(getContext(), GlobalVariable.fontSize + "", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnFontType1:
                GlobalVariable.font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Bold.ttf");
                Toast.makeText(getContext(), "Font Changed", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnFontType2:
                GlobalVariable.font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Italic.ttf");
                Toast.makeText(getContext(), "Font Changed", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnDark:
                GlobalVariable.color  = 1;
                Toast.makeText(getContext(), "Clicked Dark", Toast.LENGTH_LONG).show();
                break;


            case R.id.btnLight:
                GlobalVariable.color  = 0;
                Toast.makeText(getContext(), "Clicked Light", Toast.LENGTH_LONG).show();
                break;


            case R.id.btnPageMargin1:
                GlobalVariable.left = 10;
                GlobalVariable.top = 10;
                GlobalVariable.right = 10;
                GlobalVariable.bottom = 10;

                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnPageMargin2:
                GlobalVariable.left = 20;
                GlobalVariable.top = 20;
                GlobalVariable.right = 20;
                GlobalVariable.bottom = 20;

                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_LONG).show();
                break;



            case R.id.btnPageMargin3:
                GlobalVariable.left = 30;
                GlobalVariable.top = 30;
                GlobalVariable.right = 30;
                GlobalVariable.bottom = 30;

                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnLineSpacing:
                GlobalVariable.lineSpacing = 10;
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnLineSpacing1:
                GlobalVariable.lineSpacing = 12;
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnLineSpacing2:
                GlobalVariable.lineSpacing = 14;
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_LONG).show();
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

//    private void RefreshActivity() {
//        new Handler().post(new Runnable() {
//
//            @Override
//            public void run()
//            {
//                Intent intent = getActivity().getIntent();
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
//                        | Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                getActivity().overridePendingTransition(0, 0);
//                getActivity().finish();
//
//                getActivity().overridePendingTransition(0, 0);
//                startActivity(intent);
//            }
//        });
//    }
}
