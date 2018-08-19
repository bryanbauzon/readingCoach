package sebastian.devmonkey.capstoneproject.activity.Stories;

import android.speech.tts.SynthesisCallback;
import android.speech.tts.SynthesisRequest;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import sebastian.devmonkey.capstoneproject.R;

public class Story extends AppCompatActivity {

    TextView story;
    TextToSpeech tts;
	//...
    int ctr = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Story");

        story = findViewById(R.id.story);
         tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
             @Override
             public void onInit(int i) {
              if(i != TextToSpeech.ERROR){
                  tts.setLanguage(Locale.UK);
                 // tts.setPitch(1.1f);
              }
             }
         });
    }

    public void play(View view) {
        ctr++;
        if(ctr == 1){
            String toSpeak = story.getText().toString();
            tts.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);

        }else{
            tts.stop();
            //tts.shutdown();
            ctr = 0;
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
        //this is to stop the speech t
        tts.shutdown();
        tts.stop();
    }

}
