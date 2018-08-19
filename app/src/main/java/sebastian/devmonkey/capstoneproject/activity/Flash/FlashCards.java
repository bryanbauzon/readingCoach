package sebastian.devmonkey.capstoneproject.activity.Flash;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import sebastian.devmonkey.capstoneproject.R;

public class FlashCards extends AppCompatActivity {
    TextToSpeech tts;
    TextView textContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);
        setTitle("Flash Cards");
        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textContainer = findViewById(R.id.text);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i !=TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                }
            }
        });
    }

    public void speech(View view) {
        prompSpeech();
    }
    private void prompSpeech(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something!");
        try{
            startActivityForResult(i,100);
        }catch(ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(), "Intent problem", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        switch (requestCode){
            case 100:
                if(resultCode == RESULT_OK && data != null){
                    ArrayList<String> resultData = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textContainer.setText(resultData.get(0));
                    //..displaying the result by the use of toast or a pop up message
                        Toast.makeText(this,"You said : "+textContainer.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                        if(textContainer.getText().toString().equals("back")){
                            onBackPressed();
                        }else if(textContainer.getText().toString().equals("introduce yourself")){
                            String toSpeak = "Hi, my name is Kaitlyn, only limited commands will be executed. Thank you!";
                            Toast.makeText(this,toSpeak,Toast.LENGTH_SHORT).show();
                            tts.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
                        }else if(textContainer.getText().toString().equalsIgnoreCase("open Google")){
                            String toSpeak = "Your command is under construction. Sorry.";
                            Toast.makeText(this,toSpeak,Toast.LENGTH_SHORT).show();
                            tts.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
                        }
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        Back();

    }


    public boolean onOptionsItemSelected(MenuItem item){
        Back();
        return true;

    }

    private void Back() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        } else {
            if (getFragmentManager().getBackStackEntryCount() > 1) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }


}
