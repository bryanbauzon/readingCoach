package sebastian.devmonkey.capstoneproject.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import sebastian.devmonkey.capstoneproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReadingPlansFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReadingPlansFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadingPlansFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    Button btnBeginner, btnIntermediate, btnHardCore;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReadingPlansFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReadingPlansFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReadingPlansFragment newInstance(String param1, String param2) {
        ReadingPlansFragment fragment = new ReadingPlansFragment();
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

        View myView = inflater.inflate(R.layout.fragment_reading_plans, container, false);

        btnBeginner = (Button) myView.findViewById(R.id.beginnerbtn);
        btnIntermediate = (Button) myView.findViewById(R.id.intermediatebtn);
        btnHardCore = (Button) myView.findViewById(R.id.hardcorebtn);

        btnBeginner.setOnClickListener(this);
        btnIntermediate.setOnClickListener(this);
        btnHardCore.setOnClickListener(this);

        // Inflate the layout for this fragment
        return myView;
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
        switch (view.getId()) {
            case R.id.beginnerbtn:
                Toast.makeText(getActivity(),"Beginner Button Clicked",Toast.LENGTH_SHORT).show();
                break;

            case R.id.intermediatebtn:
                Toast.makeText(getActivity(), "Intermediate Button Clicked", Toast.LENGTH_LONG).show();

                break;

            case R.id.hardcorebtn:
                Toast.makeText(getActivity(),"Hard Core Button Clicked",Toast.LENGTH_SHORT).show();
                break;
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
}