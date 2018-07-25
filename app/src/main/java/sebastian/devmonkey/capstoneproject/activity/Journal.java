package sebastian.devmonkey.capstoneproject.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;

public class Journal extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        this.setTitle("Journal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = new DatabaseHelper(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);


        switch (item.getItemId()) {
            case R.id.add:
                startActivity(new Intent(this, AddJournal.class));
                finish();
                return true;

            default :
                return true;
        }



    }


//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(this, MainActivity.class));
//
//    }



}
