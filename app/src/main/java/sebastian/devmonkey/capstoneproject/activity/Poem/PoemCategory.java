package sebastian.devmonkey.capstoneproject.activity.Poem;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.fragments.PoemBottomNav.PoemEasyFragment;
import sebastian.devmonkey.capstoneproject.fragments.PoemBottomNav.PoemHardFragment;
import sebastian.devmonkey.capstoneproject.fragments.PoemBottomNav.PoemIntermediateFragment;
import sebastian.devmonkey.capstoneproject.fragments.StoryCategoryBottomNav.EasyFragment;
import sebastian.devmonkey.capstoneproject.fragments.StoryCategoryBottomNav.HardFragment;
import sebastian.devmonkey.capstoneproject.fragments.StoryCategoryBottomNav.IntermediateFragment;

public class PoemCategory extends AppCompatActivity implements PoemEasyFragment.OnFragmentInteractionListener,
        PoemIntermediateFragment.OnFragmentInteractionListener, PoemHardFragment.OnFragmentInteractionListener{

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_category);


        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.poem_bottom_nav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Easy");


        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadFragment(new PoemEasyFragment());
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_poem_easy:
                    toolbar.setTitle("Easy");
                    fragment = new PoemEasyFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_poem_intermediate:
                    toolbar.setTitle("Intermediate");
                    fragment = new PoemIntermediateFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.nav_poem_hard:
                    toolbar.setTitle("Hard");
                    fragment = new PoemHardFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.poem_bottom_frame_container1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                //startActivity(new Intent(getApplicationContext(), StoryCategory.class));
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);

    }
}
