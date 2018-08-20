package sebastian.devmonkey.capstoneproject.activity.Stories;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.Quizzer.Quizzer;

public class StoryReading extends AppCompatActivity {

    TextView content;
    TextToSpeech textToSpeech;
    int ctr;

    String level;
    String id_temp;
    InputStream is;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_reading);

        Intent intent = getIntent();
        content = findViewById(R.id.txtContent);

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Story");



       //this variable is used for conditional statement and serves as the id
         level = intent.getStringExtra("level");
         id_temp = intent.getStringExtra("id");

        int id = Integer.parseInt(id_temp);
        Toast.makeText(this,id_temp,Toast.LENGTH_SHORT).show();
        //text to speech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.US);
                    textToSpeech.setPitch(0.8f);
                }
            }
        });

        content.setMovementMethod(new ScrollingMovementMethod());
        String data = "";
        StringBuffer stringBuffer = new StringBuffer();
            if(level.equals("easy")){
                //testing for id
                if(id == 0){
                    //array is always starts at 0;
                     is = getApplicationContext().getResources().openRawResource(R.raw.easy_story1);
                } if(id == 1){
                    is = getApplicationContext().getResources().openRawResource(R.raw.test);

                }
            }//succeding is for higher level

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if(is != null){
            try {
                //assigning the value of reader to data and test it until the condition meets,
                while ((data = reader.readLine()) != null){
                    stringBuffer.append(data + "n");
                }
                content.setText(stringBuffer);
                is.close();
            }catch (Exception e){

            }
        }
    }

    public void Speech(View view) {
        ctr++;
        if(ctr == 1){
            String toSpeak = content.getText().toString();
            textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);

        }else{
            textToSpeech.stop();
            ctr = 0;
        }

    }
    public void Quizzer(View view) {
        textToSpeech.stop();
        //startActivity(new Intent(getApplicationContext(), Quizzer.class));
        Intent intent = new Intent(getApplicationContext(),Quizzer.class);
        intent.putExtra("level",level);
        intent.putExtra("id",id_temp);
        startActivity(intent);
 }
    @Override
    public void onBackPressed() {
        textToSpeech.shutdown();
        textToSpeech.stop();
        finish();
    }


    public boolean onOptionsItemSelected(MenuItem item){
        textToSpeech.shutdown();
        textToSpeech.stop();
        finish();
        return true;

    }




}
