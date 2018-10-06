package sebastian.devmonkey.capstoneproject.activity.Flash;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;

public class FlashCards extends AppCompatActivity {
    TextToSpeech tts;
    TextView word;
    GlobalVariable gv;
//    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);
        setTitle("Flash Cards");

        gv = new GlobalVariable();


        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        word = findViewById(R.id.word);

//
//        if (GlobalVariable.color == 1){
//            layout.setBackgroundColor(Color.BLACK);
//            textContainer.setTextColor(Color.WHITE);
//            word.setTextColor(Color.WHITE);
//            score.setTextColor(Color.WHITE);
//        }

        gv.setMargins(word ,GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);


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


        String myWord = word.getText().toString();
        myWord = myWord.trim();
        if(myWord.equals("")){
            tts.speak("Sorry, Please fill-up the field.",TextToSpeech.QUEUE_FLUSH,null);
        }else{
            prompSpeech();

        }
    }
    private void prompSpeech(){
//        tts.speak("Please connect to the internet or turn on your mobile data for me to translate your voice more accurate. Thank you and good day.",TextToSpeech.QUEUE_FLUSH,null);

        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say the word...");
        try{
            startActivityForResult(i,100);
        }catch(ActivityNotFoundException ignored){

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        switch (requestCode){
            case 100:
                if(resultCode == RESULT_OK && data != null){
                    ArrayList<String> resultData = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                        if(word.getText().toString().equals(resultData.get(0))){
                            tts.speak("You got it!",TextToSpeech.QUEUE_FLUSH,null);

                            LayoutInflater factory = LayoutInflater.from(this);
                            final View correctDialogView = factory.inflate(R.layout.customized_alert_dialog,null);
                            final AlertDialog correctDialog = new AlertDialog.Builder(this).create();

                            //initialization of button from custom alert dialog
                            Button yes = (Button)correctDialogView.findViewById(R.id.btnYes);
                            Button no = (Button)correctDialogView.findViewById(R.id.btnNo);

                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    correctDialog.dismiss();
                                    word.setText(null);
                                    word.requestFocus();

                                }
                            });

                            no.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    correctDialog.dismiss();
                                }
                            });
                            correctDialog.setView(correctDialogView);
                            correctDialog.show();
                        }else{
                            tts.speak("Sorry, please try again.",TextToSpeech.QUEUE_FLUSH,null);

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

    public void speak(View view) {
        String myWord = word.getText().toString();
        myWord = myWord.trim();
        if(myWord.equals("")){
            tts.speak("Sorry, Please fill-up the field.",TextToSpeech.QUEUE_FLUSH,null);
        }else{
            tts.setSpeechRate(0.8f);
            tts.speak(myWord,TextToSpeech.QUEUE_FLUSH,null);
        }
    }



    @Override
    public void onStop() {
        super.onStop();

        if (tts != null) {
            tts.shutdown();
        }
    }
}
