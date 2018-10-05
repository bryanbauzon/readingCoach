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
                txtContent.setText(poems.sonnet2[0]);
                //is = getResources().openRawResource(R.raw.poem_sonnet2);
            } else if(id == 2){
                txtContent.setText(poems.sonnet3[0]);

            } else if(id == 3){
                txtContent.setText(poems.sonnet4[0]);

            } else if(id == 4){
                txtContent.setText(poems.sonnet5[0]);

            } else if(id == 5){
                txtContent.setText(poems.sonnet6[0]);

            } else if(id == 6){
                txtContent.setText(poems.sonnet7[0]);

            } else if(id == 7){
                txtContent.setText(poems.sonnet8[0]);

            } else if(id == 8){
                txtContent.setText(poems.sonnet9[0]);

            } else if(id == 9){
                txtContent.setText(poems.sonnet10[0]);

            } else if(id == 10){
                txtContent.setText(poems.sonnet11[0]);

            } else if(id == 11){
                txtContent.setText(poems.sonnet12[0]);

            } else if(id == 12){
                txtContent.setText(poems.sonnet13[0]);

            } else if(id == 13){
                txtContent.setText(poems.sonnet14[0]);

            } else if(id == 14){
                txtContent.setText(poems.sonnet15[0]);

            } else if(id == 15){
                txtContent.setText(poems.sonnet16[0]);

            } else if (id == 16) {
                txtContent.setText(poems.sonnet17[0]);

            } else if (id == 17) {
                txtContent.setText(poems.sonnet18[0]);

            } else if (id == 18) {
                txtContent.setText(poems.sonnet19[0]);

            } else if (id == 19) {
                txtContent.setText(poems.sonnet20[0]);

            } else if (id == 20) {
                txtContent.setText(poems.sonnet21[0]);

            } else if (id == 21) {
                txtContent.setText(poems.sonnet22[0]);

            } else if (id == 22) {
                txtContent.setText(poems.sonnet23[0]);

            } else if (id == 23) {
                txtContent.setText(poems.sonnet24[0]);

            } else if (id == 24) {
                txtContent.setText(poems.sonnet25[0]);

            } else if (id == 25) {
                txtContent.setText(poems.sonnet26[0]);

            } else if (id == 26) {
                txtContent.setText(poems.sonnet27[0]);

            } else if (id == 27) {
                txtContent.setText(poems.sonnet28[0]);

            } else if (id == 28) {
                txtContent.setText(poems.sonnet29[0]);

            } else if (id == 29) {
                txtContent.setText(poems.sonnet30[0]);

            } else if (id == 30) {
                txtContent.setText(poems.sonnet31[0]);

            } else if (id == 31) {
                txtContent.setText(poems.sonnet32[0]);

            } else if (id == 32) {
                txtContent.setText(poems.sonnet33[0]);

            } else if (id == 33) {
                txtContent.setText(poems.sonnet34[0]);

            } else if (id == 34) {
                txtContent.setText(poems.sonnet35[0]);

            } else if (id == 35) {
                txtContent.setText(poems.sonnet36[0]);

            } else if (id == 36) {
                txtContent.setText(poems.sonnet37[0]);

            } else if (id == 37) {
                txtContent.setText(poems.sonnet38[0]);

            } else if (id == 38) {
                txtContent.setText(poems.sonnet39[0]);

            } else if (id == 39) {
                txtContent.setText(poems.sonnet40[0]);

            } else if (id == 40) {
                txtContent.setText(poems.sonnet41[0]);

            } else if (id == 41) {
                txtContent.setText(poems.sonnet42[0]);

            } else if (id == 42) {
                txtContent.setText(poems.sonnet43[0]);

            } else if (id == 43) {
                txtContent.setText(poems.sonnet44[0]);

            } else if (id == 44) {
                txtContent.setText(poems.sonnet45[0]);

            } else if (id == 45) {
                txtContent.setText(poems.sonnet46[0]);

            } else if (id == 46) {
                txtContent.setText(poems.sonnet47[0]);

            } else if (id == 47) {
                txtContent.setText(poems.sonnet48[0]);

            } else if (id == 48) {
                txtContent.setText(poems.sonnet49[0]);

            } else if (id == 49) {
                txtContent.setText(poems.sonnet50[0]);

            } else if (id == 50) {
                txtContent.setText(poems.sonnet51[0]);

            } else if (id == 51) {
                txtContent.setText(poems.sonnet52[0]);

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
