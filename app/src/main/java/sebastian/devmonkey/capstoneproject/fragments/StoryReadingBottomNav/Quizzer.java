package sebastian.devmonkey.capstoneproject.fragments.StoryReadingBottomNav;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.Arrays;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;
import sebastian.devmonkey.capstoneproject.other.QuestionAndAnswer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Quizzer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Quizzer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Quizzer extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private QuestionAndAnswer QuestionLibrary = new QuestionAndAnswer();

    List<Integer> available;

    Button a,b,c,d;

    private String answer;
    private int score = 0;
    TextView questions = null, containerScore = null;
    GlobalVariable gv;
    Intent intent;
    String id_temp, level;
    int id;


    private OnFragmentInteractionListener mListener;

    public Quizzer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Quizzer.
     */
    // TODO: Rename and change types and number of parameters
    public static Quizzer newInstance(String param1, String param2) {
        Quizzer fragment = new Quizzer();
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

        View view = inflater.inflate(R.layout.fragment_quizzer, container, false);

        gv = new GlobalVariable();

        intent = getActivity().getIntent();

        level = intent.getStringExtra("level");
        id_temp = intent.getStringExtra("id");

        id = Integer.parseInt(id_temp);

        questions = view.findViewById(R.id.question);
        containerScore = view. findViewById(R.id.score);
        a = view.findViewById(R.id.a);
        b = view.findViewById(R.id.b);
        c = view.findViewById(R.id.c);
        d = view.findViewById(R.id.d);

        questions.setTypeface(GlobalVariable.font);
        containerScore.setTypeface(GlobalVariable.font);

        gv.setMargins(questions, GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);
        gv.setMargins(containerScore ,GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);



        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);

        available = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            available.add(i);
        }

        updateQuestion();


        // Inflate the layout for this fragment
        return view;
    }

    private void updateQuestion() {
        if (!available.isEmpty()) {

            Collections.shuffle(available);

            if (level.equals("easy")) {

                if (id == 0) {
                    questions.setText(QuestionLibrary.getQuestion(available.get(0), id, level));
                    a.setText(QuestionLibrary.getChoice1(available.get(0), id, level));
                    b.setText(QuestionLibrary.getChoice2(available.get(0), id, level));
                    c.setText(QuestionLibrary.getChoice3(available.get(0), id, level));
                    d.setText(QuestionLibrary.getChoice4(available.get(0), id, level));
                    answer = QuestionLibrary.getCorrectAnswer(available.get(0), id, level);

                    available.remove(0);

                } else if (id == 1) {
                    questions.setText(QuestionLibrary.getQuestion(available.get(0), id, level));
                    a.setText(QuestionLibrary.getChoice1(available.get(0), id, level));
                    b.setText(QuestionLibrary.getChoice2(available.get(0), id, level));
                    c.setText(QuestionLibrary.getChoice3(available.get(0), id, level));
                    d.setText(QuestionLibrary.getChoice4(available.get(0), id, level));
                    answer = QuestionLibrary.getCorrectAnswer(available.get(0), id, level);

                    available.remove(0);

                }
            }

        } else {
            Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
            a.setEnabled(false);
            b.setEnabled(false);
            c.setEnabled(false);
            d.setEnabled(false);
        }
    }

    private void updateScore (int point) {
        containerScore.setText("" + point);
    }

    private void Correct() {
        score++;
        updateScore(score);
        updateQuestion();

        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
    }

    private void Wrong() {
        Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT).show();
        updateQuestion();
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
            case R.id.a:
                if (a.getText() == answer) {
                    Correct();
                } else {
                    Wrong();
                }

                break;
            case R.id.b:
                if (b.getText() == answer) {
                    Correct();
                } else {
                    Wrong();
                }
                break;
            case R.id.c:
                if (c.getText() == answer) {
                    Correct();
                } else {
                    Wrong();
                }
                break;

            case R.id.d:
                if (d.getText() == answer) {
                    Correct();
                } else {
                    Wrong();
                }
                break;

            default:

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
