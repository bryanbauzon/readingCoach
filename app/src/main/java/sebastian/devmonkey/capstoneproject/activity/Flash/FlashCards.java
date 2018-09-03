package sebastian.devmonkey.capstoneproject.activity.Flash;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;

public class FlashCards extends AppCompatActivity {
    TextToSpeech tts;
    TextView textContainer,word,score;
    Button  alertYes;
    GlobalVariable gv;
//    RelativeLayout layout;

    public String[] words = {
            "hello",
            "best",
            "define",
            "savage",
            "deadline"
    };
    String lastitem = words[4];
    int ctr = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);
        setTitle("Flash Cards");

        gv = new GlobalVariable();


        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textContainer = findViewById(R.id.text);
        word = findViewById(R.id.words);
        score = findViewById(R.id.score);
//        layout = findViewById(R.id.flashCardLayout);
//
//
//        if (GlobalVariable.color == 1){
//            layout.setBackgroundColor(Color.BLACK);
//            textContainer.setTextColor(Color.WHITE);
//            word.setTextColor(Color.WHITE);
//            score.setTextColor(Color.WHITE);
//        }

        textContainer.setTypeface(GlobalVariable.font);
        word.setTypeface(GlobalVariable.font);
        score.setTypeface(GlobalVariable.font);

        gv.setMargins(textContainer ,GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);
        gv.setMargins(word ,GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);
        gv.setMargins(score ,GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);


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
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say the word...");
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
                        if(textContainer.getText().toString().equals(word.getText().toString())){

                            LayoutInflater factory = LayoutInflater.from(this);
                            final View correctDialogView = factory.inflate(R.layout.customized_alert_dialog,null);
                            final AlertDialog correctDialog = new AlertDialog.Builder(this).create();

                            //initialization of button from custom alert dialog
                            Button yes = (Button)correctDialogView.findViewById(R.id.btnYes);

                            correctDialog.setView(correctDialogView);
                            correctDialog.show();
                            //incerment by 1
                            ctr++;
                            score.setText("Score: "+ ctr);
                            if(words[ctr].equals(5)){
                                ctr = 0;
                                Toast.makeText(getApplicationContext(),"You have reach the ending word. The module is still under construction",Toast.LENGTH_LONG).show();
                                correctDialog.dismiss();
                            }else{
                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        correctDialog.dismiss();
                                        word.setText(words[ctr]);
                                        textContainer.setText(null);
                                        prompSpeech();
                                    }
                                });

                                word.setText(words[ctr]);
                                textContainer.setText(null);
                            }
//
                          //  textContainer.setBackgroundColor(getResources().getColor(R.color.correct));
                        }else{
                            textContainer.setBackgroundColor(getResources().getColor(R.color.incorrect));
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
