package sebastian.devmonkey.capstoneproject.activity.Stories;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.MainActivity;
import sebastian.devmonkey.capstoneproject.fragments.StoryCategoryBottomNav.EasyFragment;
import sebastian.devmonkey.capstoneproject.fragments.StoryCategoryBottomNav.HardFragment;
import sebastian.devmonkey.capstoneproject.fragments.StoryCategoryBottomNav.IntermediateFragment;


public class StoryCategory extends AppCompatActivity implements EasyFragment.OnFragmentInteractionListener,
IntermediateFragment.OnFragmentInteractionListener, HardFragment.OnFragmentInteractionListener{

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_category);


        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Easy");


        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadFragment(new EasyFragment());

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_easy:
                    toolbar.setTitle("Easy");
                    fragment = new EasyFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_intermediate:
                    toolbar.setTitle("Intermediate");
                    fragment = new IntermediateFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.nav_hard:
                    toolbar.setTitle("Hard");
                    fragment = new HardFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottom_frame_container1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {

    }
}
