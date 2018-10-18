package sebastian.devmonkey.capstoneproject.activity;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Locale;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.Arrays;

public class Terminologies extends AppCompatActivity {

    TextView txtTitle, txtMeaning;
    String getID;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminologies);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.US);
                    textToSpeech.setPitch(0.8f);
                }
            }
        });

        setTitle("Terminologies");
        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTitle = findViewById(R.id.txtTerminologyTitle);
        txtMeaning = findViewById(R.id.txtTerminologyMeaning);

        Arrays arrays = new Arrays();


        Intent intent = getIntent();
        getID = intent.getStringExtra("id");

        for (int x=0; x < arrays.getTerminologiesLength(); x++ ){
            if (getID.equals(arrays.getTerminologiesWords1(x))){
                Toast.makeText(getApplicationContext(), arrays.getTerminologiesWords1(x), Toast.LENGTH_SHORT).show();
                txtTitle.setText(arrays.getTerminologiesWords1(x));
                txtMeaning.setText(arrays.getTerminologiesMeaning(x));
                break;
            }
        }

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public void listen(View view) {
        String title = txtTitle.getText().toString().trim();
        textToSpeech.speak(title,TextToSpeech.QUEUE_FLUSH,null);

    }
}
