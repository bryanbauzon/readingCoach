package sebastian.devmonkey.capstoneproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.Stories.StoryReading;
import sebastian.devmonkey.capstoneproject.other.Arrays;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookmarksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookmarksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookmarksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayAdapter<String> listviewAdapter;
    ListView listView;
    DatabaseHelper db;
    ArrayList<String> title;
    ArrayList<String> titleid;
    ArrayList<String> level;
    TextView textView;

    ArrayAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public BookmarksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookmarksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookmarksFragment newInstance(String param1, String param2) {
        BookmarksFragment fragment = new BookmarksFragment();
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
        View view = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        db = new DatabaseHelper(getContext());
        title = new ArrayList<>();
        titleid = new ArrayList<>();
        level = new ArrayList<>();
        textView = view.findViewById(R.id.txtNoItem);


        listView = view.findViewById(R.id.listviewBookmarks);

        viewDataBookmarks();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), StoryReading.class);
                intent.putExtra("id", titleid.get(i));
                intent.putExtra("title", title.get(i));
                intent.putExtra("level", level.get(i));
                Toast.makeText(getContext(),titleid.get(i),Toast.LENGTH_SHORT).show();

                startActivity(intent);
              //  System.exit(0);
            }
        });

        return view;
    }

    private void viewDataBookmarks(){
        Cursor cursor = db.viewDataBookmarks();

        if (cursor.getCount() == 0) {
            textView.setText("No bookmarks entries found");

        } else {

            textView.setText("");

            while (cursor.moveToNext()) {
                //getting id in database to array
                title.add(cursor.getString(1));
                titleid.add(cursor.getString(2));
                level.add(cursor.getString(3));

            }


            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, title);

            listView.setAdapter(adapter);
        }
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
