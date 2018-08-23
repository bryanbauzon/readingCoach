package sebastian.devmonkey.capstoneproject.activity.Stories;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.fragments.BottomFragments.Notes;
import sebastian.devmonkey.capstoneproject.fragments.BottomFragments.Story;
import sebastian.devmonkey.capstoneproject.fragments.SettingsFragment;

public class StoryReading extends AppCompatActivity implements sebastian.devmonkey.capstoneproject.fragments.BottomFragments.Quizzer.OnFragmentInteractionListener
, Notes.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, Story.OnFragmentInteractionListener{

    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_reading);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Story");

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Story");
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
                    fragment = new sebastian.devmonkey.capstoneproject.fragments.BottomFragments.Quizzer();
                    loadFragment(fragment);
                    return true;
                case R.id.notes:
                    toolbar.setTitle("Notes");
                    fragment = new Notes();
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

//    public void Speech(View view) {
//        ctr++;
//        if(ctr == 1){
//            String toSpeak = content.getText().toString();
//            textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//
//        }else{
//            textToSpeech.stop();
//            ctr = 0;
//        }
//
//    }
//    public void Quizzer(View view) {
//        textToSpeech.stop();
//        //startActivity(new Intent(getApplicationContext(), Quizzer.class));
//        Intent intent = new Intent(getApplicationContext(),Quizzer.class);
//        intent.putExtra("level",level);
//        intent.putExtra("id",id_temp);
//        startActivity(intent);
// }
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

        }

        return super.onOptionsItemSelected(item);

    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
