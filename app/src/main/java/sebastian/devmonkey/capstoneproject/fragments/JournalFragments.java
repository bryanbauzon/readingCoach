package sebastian.devmonkey.capstoneproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.Journal.AddJournal;
import sebastian.devmonkey.capstoneproject.activity.Journal.UpdateDeleteJournal;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JournalFragments.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JournalFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JournalFragments extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    DatabaseHelper db;
    ArrayList<String> listItem;
    ArrayAdapter adapter;


    ArrayList<String> id;
    ArrayList<String> content;

    ListView listView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public JournalFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JournalFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static JournalFragments newInstance(String param1, String param2) {
        JournalFragments fragment = new JournalFragments();
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
        View view = inflater.inflate(R.layout.fragment_journal, container, false);


        db = new DatabaseHelper(getActivity());
        listItem = new ArrayList<>();
        id = new ArrayList<>();
        content = new ArrayList<>();
        listView = view.findViewById(R.id.listview);


//        // Auto update listview
//        final Handler handler = new Handler();
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    public void run() {
//                        listItem.clear();
//                        viewData();
//                    }
//                });
//            }
//        };
//        timer.schedule(task, 0, 1000);

        viewData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = listView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();

//                Toast.makeText(getApplicationContext(), id.get(i), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), UpdateDeleteJournal.class);
                //putting data to intent
                intent.putExtra("ID", id.get(i));
                intent.putExtra("TITLE", title);
                intent.putExtra("CONTENT", content.get(i));
                startActivity(intent);

            }
        });





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


    //getting data in database to display in listview
    private void viewData() {

        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data to show", Toast.LENGTH_SHORT).show();

        } else {

            while (cursor.moveToNext()) {
                //getting id in database to array
                id.add(cursor.getString(0));

                //getting title in database
                listItem.add(cursor.getString(1)); // index 1 is the name, index 0 is id

                //getting content
                content.add(cursor.getString(2));

            }
            //display title to listview
            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listItem);
            listView.setAdapter(adapter);
        }

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.action_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
               startActivity(new Intent(getActivity(), AddJournal.class));
            //    this.overridePendingTransition()
                return true;

            default:
                break;
        }

        return false;

    }

}
