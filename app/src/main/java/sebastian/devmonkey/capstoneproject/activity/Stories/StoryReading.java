package sebastian.devmonkey.capstoneproject.activity.Stories;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.fragments.BottomFragments.AddJournalFragment;
import sebastian.devmonkey.capstoneproject.fragments.BottomFragments.Quizzer;
import sebastian.devmonkey.capstoneproject.fragments.BottomFragments.Story;
import sebastian.devmonkey.capstoneproject.fragments.SettingsFragment;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;

public class StoryReading extends AppCompatActivity implements sebastian.devmonkey.capstoneproject.fragments.BottomFragments.Quizzer.OnFragmentInteractionListener
, SettingsFragment.OnFragmentInteractionListener, Story.OnFragmentInteractionListener, AddJournalFragment.OnFragmentInteractionListener{

    private ActionBar toolbar;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_reading);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Story");

        db = new DatabaseHelper(this);

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadFragment(new Story());


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.story:
                    toolbar.setTitle("Story");
                    fragment = new Story();
                    loadFragment(fragment);
                    return true;
                case R.id.quizzer:
                    toolbar.setTitle("Quizzer");
                    fragment = new Quizzer();
                    loadFragment(fragment);
                    return true;

                case R.id.jounralFrag:
                    toolbar.setTitle("Journal");
                    fragment = new AddJournalFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.settings:
                    toolbar.setTitle("Settings");
                    fragment = new SettingsFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }



    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(), StoryCategory.class));
                finish();
                return true;

//            case R.id.addJournal:
//                LayoutInflater factory = LayoutInflater.from(StoryReading.this);
//                final View journalDialogView = factory.inflate(R.layout.customized_alert_dialog_journal,null);
//                final AlertDialog journalDialog = new AlertDialog.Builder(StoryReading.this).create();
//                final EditText txtTitle = (EditText)journalDialogView.findViewById(R.id.txtTitle);
//                final EditText txtContent = (EditText)journalDialogView.findViewById(R.id.txtContent);
//                final Button save = (Button)journalDialogView.findViewById(R.id.btnSave);
//
//
//                txtTitle.requestFocus();
//                journalDialog.setView(journalDialogView);
//                journalDialog.show();
//
//                save.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if(!txtTitle.getText().toString().isEmpty() && !txtContent.getText().toString().isEmpty()){
//
//                            db.insertData(txtTitle.getText().toString(),txtContent.getText().toString());
//                            Toast.makeText(getApplicationContext(),"yey",Toast.LENGTH_SHORT).show();
//                            journalDialog.dismiss();
//                        }
//
//                    }
//                });
//                return true;

        }

        return super.onOptionsItemSelected(item);

    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
