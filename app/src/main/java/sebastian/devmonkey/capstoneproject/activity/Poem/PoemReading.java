package sebastian.devmonkey.capstoneproject.activity.Poem;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.Quizzer.Quizzer;

public class PoemReading extends AppCompatActivity {

    TextView content;
    TextToSpeech textToSpeech;
    int Result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_reading);

        content = findViewById(R.id.txtContent);
    }

    public void Speech(View view) {

        //Setting the Text-to-Speech.
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                //if there is no problem or error in Text-to-Speech then it will set it's Language into default.
                if (i != TextToSpeech.ERROR){Result  = textToSpeech.setLanguage(Locale.getDefault());}
            }
        });

        //Delay for a moment
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Text-to-Speech will be reading the contents inside the TextView.
                textToSpeech.speak(content.getText().toString(), TextToSpeech.QUEUE_FLUSH,null );
            }
        }, 2000);
    }


    public void Quizzer(View view) {
        startActivity(new Intent(getApplicationContext(), Quizzer.class));
        finish();
    }


}
