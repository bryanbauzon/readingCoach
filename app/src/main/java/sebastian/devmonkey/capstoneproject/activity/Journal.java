package sebastian.devmonkey.capstoneproject.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import sebastian.devmonkey.capstoneproject.R;

public class Journal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        this.setTitle("Journal");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }


//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(this, MainActivity.class));
//
//    }



}
