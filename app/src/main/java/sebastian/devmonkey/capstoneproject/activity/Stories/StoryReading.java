package sebastian.devmonkey.capstoneproject.activity.Stories;

import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.MainActivity;
import sebastian.devmonkey.capstoneproject.fragments.StoryReadingBottomNav.AddJournalFragment;
import sebastian.devmonkey.capstoneproject.fragments.StoryReadingBottomNav.Quizzer;
import sebastian.devmonkey.capstoneproject.fragments.StoryReadingBottomNav.Story;
import sebastian.devmonkey.capstoneproject.fragments.SettingsFragment;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;

public class StoryReading extends AppCompatActivity implements sebastian.devmonkey.capstoneproject.fragments.StoryReadingBottomNav.Quizzer.OnFragmentInteractionListener
, SettingsFragment.OnFragmentInteractionListener, Story.OnFragmentInteractionListener, AddJournalFragment.OnFragmentInteractionListener{

    private ActionBar toolbar;

    DatabaseHelper db;
    String bookmark;

    public TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_reading);

        Intent intent = getIntent();
        bookmark = intent.getStringExtra("bookmark");

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
                   // textToSpeech.stop();
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

                if (bookmark.equals("0")){
                    finish();
                } else if (bookmark.equals("1")){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                return true;

        }

        return super.onOptionsItemSelected(item);

    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
