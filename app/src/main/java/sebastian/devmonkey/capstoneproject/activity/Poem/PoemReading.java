package sebastian.devmonkey.capstoneproject.activity.Poem;

import android.content.Intent;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;
import sebastian.devmonkey.capstoneproject.other.Poems;

public class PoemReading extends AppCompatActivity {

    TextView txtTitle, txtContent,txtAuthor;
    Intent intent;
    GlobalVariable gv;
    String id_temp, title, level;
    private Menu menu;
    InputStream is;
    public static boolean isTrue;
    RelativeLayout rl;
    Poems poems;
    int ctr = 0;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_reading);

        gv = new GlobalVariable();
        poems = new Poems();

        intent = getIntent();
        txtTitle = findViewById(R.id.txtTitlePoem);
        txtContent = findViewById(R.id.txtContentPoem);
        txtAuthor = findViewById(R.id.a);
        rl = findViewById(R.id.rl);

        setTitle("Poems");

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                    textToSpeech.setPitch(0.7f);
                    textToSpeech.setSpeechRate(0.8f);
                }
            }
        });

        //color scheme
        if (GlobalVariable.color == 1){
            rl.setBackgroundColor(Color.parseColor("#000000"));
            txtTitle.setTextColor(Color.WHITE);
            txtContent.setTextColor(Color.WHITE);
        } else {
            rl.setBackgroundColor(Color.WHITE);
            txtTitle.setTextColor(Color.BLACK);
            txtContent.setTextColor(Color.BLACK);
        }


        //font
        txtTitle.setTypeface(GlobalVariable.font);
        txtContent.setTypeface(GlobalVariable.font);
        txtAuthor.setTypeface(GlobalVariable.font);

        //fontsize
       // txtTitle.setTextSize(GlobalVariable.fontSize);
        txtContent.setTextSize(GlobalVariable.fontSize);
        txtAuthor.setTextSize(GlobalVariable.fontSize);

        //line spacing
        gv.setMargins(txtTitle, GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);
        gv.setMargins(txtContent ,GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom);

        txtContent.setLineSpacing(GlobalVariable.lineSpacing, 1);


        level = intent.getStringExtra("level");
        id_temp = intent.getStringExtra("id");
        title = intent.getStringExtra("title");



        txtTitle.setText(title);

        int id = Integer.parseInt(id_temp);


        txtContent.setMovementMethod(new ScrollingMovementMethod());
        String data = "";
        StringBuffer stringBuffer = new StringBuffer();
        if(level.equals("poem")) {

            //testing for id
           for(int x = 0; x <= poems.poems.length;x++){
               if(id  == x){
                   txtContent.setText(poems.poems[x]);
                   if(id <= 32){
                       txtAuthor.setText(poems.authors[x]);
                   }else{
                       txtAuthor.setText("William Shakespeare");
                   }
               }
           }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.poemspeech,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.texttoSpeech:
                String content = txtContent.getText().toString();
               ctr++;
               if(ctr == 1){
                   textToSpeech.speak(content,TextToSpeech.QUEUE_FLUSH,null);
                   menu.getItem(0).setIcon(ContextCompat.getDrawable(getApplication(),R.drawable.speech));

               }else{
                   menu.getItem(0).setIcon(ContextCompat.getDrawable(getApplication(),R.drawable.defaultspeaker));
                   textToSpeech.stop();
                   ctr = 0;
               }
            return true;
        }

        return false;
    }




}




