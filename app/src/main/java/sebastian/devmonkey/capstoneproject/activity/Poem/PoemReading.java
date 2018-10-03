package sebastian.devmonkey.capstoneproject.activity.Poem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;
import sebastian.devmonkey.capstoneproject.other.Poems;

public class PoemReading extends AppCompatActivity {

    TextView txtTitle, txtContent;
    Intent intent;
    GlobalVariable gv;
    String id_temp, title, level;
    InputStream is;
    RelativeLayout rl;
    Poems poems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_reading);

        gv = new GlobalVariable();
        poems = new Poems();

        intent = getIntent();
        txtTitle = findViewById(R.id.txtTitlePoem);
        txtContent = findViewById(R.id.txtContentPoem);
        rl = findViewById(R.id.rl);

        setTitle("Poems");

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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

        //fontsize
        txtTitle.setTextSize(GlobalVariable.fontSize);
        txtContent.setTextSize(GlobalVariable.fontSize);

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
            if (id == 0) {
              txtContent.setText(poems.sonnet1[0]);
            } else if(id == 1){
                is = getResources().openRawResource(R.raw.poem_sonnet2);
            } else if(id == 2){
                is = getResources().openRawResource(R.raw.poem_sonnet3);
            } else if(id == 3){
                is = getResources().openRawResource(R.raw.poem_sonnet4);
            } else if(id == 4){
                is = getResources().openRawResource(R.raw.poem_sonnet5);
            } else if(id == 5){
                is = getResources().openRawResource(R.raw.poem_sonnet6);
            } else if(id == 6){
                is = getResources().openRawResource(R.raw.poem_sonnet7);
            } else if(id == 7){
                is = getResources().openRawResource(R.raw.poem_sonnet8);
            } else if(id == 8){
                is = getResources().openRawResource(R.raw.poem_sonnet9);
            } else if(id == 9){
                is = getResources().openRawResource(R.raw.poem_sonnet10);
            } else if(id == 10){
                is = getResources().openRawResource(R.raw.poem_sonnet11);
            } else if(id == 11){
                is = getResources().openRawResource(R.raw.poem_sonnet12);
            } else if(id == 12){
                is = getResources().openRawResource(R.raw.poem_sonnet13);
            } else if(id == 13){
                is = getResources().openRawResource(R.raw.poem_sonnet14);
            } else if(id == 14){
                is = getResources().openRawResource(R.raw.poem_sonnet15);
            } else if(id == 15){
                is = getResources().openRawResource(R.raw.poem_sonnet16);
            } else if (id == 16) {
                is = getResources().openRawResource(R.raw.poem_sonnet17);
            } else if (id == 17) {
                is = getResources().openRawResource(R.raw.poem_sonnet18);
            } else if (id == 18) {
                is = getResources().openRawResource(R.raw.poem_sonnet19);
            } else if (id == 19) {
                is = getResources().openRawResource(R.raw.poem_sonnet20);
            } else if (id == 20) {
                is = getResources().openRawResource(R.raw.poem_sonnet21);
            } else if (id == 21) {
                is = getResources().openRawResource(R.raw.poem_sonnet22);
            } else if (id == 22) {
                is = getResources().openRawResource(R.raw.poem_sonnet23);
            } else if (id == 23) {
                is = getResources().openRawResource(R.raw.poem_sonnet24);
            } else if (id == 24) {
                is = getResources().openRawResource(R.raw.poem_sonnet25);
            } else if (id == 25) {
                is = getResources().openRawResource(R.raw.poem_sonnet26);
            } else if (id == 26) {
                is = getResources().openRawResource(R.raw.poem_sonnet27);
            } else if (id == 27) {
                is = getResources().openRawResource(R.raw.poem_sonnet28);
            } else if (id == 28) {
                is = getResources().openRawResource(R.raw.poem_sonnet29);
            } else if (id == 29) {
                is = getResources().openRawResource(R.raw.poem_sonnet30);
            } else if (id == 30) {
                is = getResources().openRawResource(R.raw.poem_sonnet31);
            } else if (id == 31) {
                is = getResources().openRawResource(R.raw.poem_sonnet32);
            } else if (id == 32) {
                is = getResources().openRawResource(R.raw.poem_sonnet33);
            } else if (id == 33) {
                is = getResources().openRawResource(R.raw.poem_sonnet34);
            } else if (id == 34) {
                is = getResources().openRawResource(R.raw.poem_sonnet35);
            } else if (id == 35) {
                is = getResources().openRawResource(R.raw.poem_sonnet36);
            } else if (id == 36) {
                is = getResources().openRawResource(R.raw.poem_sonnet37);
            } else if (id == 37) {
                is = getResources().openRawResource(R.raw.poem_sonnet38);
            } else if (id == 38) {
                is = getResources().openRawResource(R.raw.poem_sonnet39);
            } else if (id == 39) {
                is = getResources().openRawResource(R.raw.poem_sonnet40);
            } else if (id == 40) {
                is = getResources().openRawResource(R.raw.poem_sonnet41);
            } else if (id == 41) {
                is = getResources().openRawResource(R.raw.poem_sonnet42);
            } else if (id == 42) {
                is = getResources().openRawResource(R.raw.poem_sonnet43);
            } else if (id == 43) {
                is = getResources().openRawResource(R.raw.poem_sonnet44);
            } else if (id == 44) {
                is = getResources().openRawResource(R.raw.poem_sonnet45);
            } else if (id == 45) {
                is = getResources().openRawResource(R.raw.poem_sonnet46);
            } else if (id == 46) {
                is = getResources().openRawResource(R.raw.poem_sonnet47);
            } else if (id == 47) {
                is = getResources().openRawResource(R.raw.poem_sonnet48);
            } else if (id == 48) {
                is = getResources().openRawResource(R.raw.poem_sonnet49);
            } else if (id == 49) {
                is = getResources().openRawResource(R.raw.poem_sonnet50);
            } else if (id == 50) {
                is = getResources().openRawResource(R.raw.poem_sonnet51);
            } else if (id == 51) {
                is = getResources().openRawResource(R.raw.poem_sonnet52);
            }
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            if (is != null) {
//                try {
//                    //assigning the value of reader to data and test it until the condition meets,
//                    while ((data = reader.readLine()) != null) {
//                        stringBuffer.append(data + "");
//                    }
//                    txtContent.setText(stringBuffer);
//                    is.close();
//                } catch (Exception e) {
//
//                }
//            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case android.R.id.home:
                finish();
                return true;
        }

        return false;
    }
}
