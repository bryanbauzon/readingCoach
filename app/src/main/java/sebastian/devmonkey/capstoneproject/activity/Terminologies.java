package sebastian.devmonkey.capstoneproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Array;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.Arrays;

public class Terminologies extends AppCompatActivity {

    TextView txtTitle, txtMeaning;
    String getID;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminologies);

        setTitle("Terminologies");
        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTitle = findViewById(R.id.txtTerminologyTitle);
        txtMeaning = findViewById(R.id.txtTerminologyMeaning);


        Intent intent = getIntent();
        getID = intent.getStringExtra("id");

        id = Integer.parseInt(getID);

        Arrays arrays = new Arrays();

        txtTitle.setText(arrays.getTerminologiesWords1(id));
        txtMeaning.setText(arrays.getTerminologiesMeaning(id));

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

}