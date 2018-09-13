package sebastian.devmonkey.capstoneproject.activity.Poem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;

public class PoemReading extends AppCompatActivity {

    TextView txtTitle, txtContent;
    Intent intent;
    GlobalVariable gv;
    String id_temp, title, level;
    InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_reading);

        intent = getIntent();
        txtTitle = findViewById(R.id.txtTitlePoem);
        txtContent = findViewById(R.id.txtContentPoem);


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
                //array is always starts at 0;
                is = getResources().openRawResource(R.raw.poem_sonnet1);
//            } else if(id == 1){
//                is = getResources().openRawResource(R.raw.a_christmas_in_march);
//            } else if(id == 2){
//                is = getResources().openRawResource(R.raw.a_call_to_the_pool);
//            } else if(id == 3){
//                is = getActivity().getResources().openRawResource(R.raw.a_happy_visitor);
//            } else if(id == 4){
//                is = getActivity().getResources().openRawResource(R.raw.alligators);
//            } else if(id == 5){
//                is = getActivity().getResources().openRawResource(R.raw.an_adventure);
//            } else if(id == 6){
//                is = getActivity().getResources().openRawResource(R.raw.bears);
//            } else if(id == 7){
//                is = getActivity().getResources().openRawResource(R.raw.beds);
//            } else if(id == 8){
//                is = getActivity().getResources().openRawResource(R.raw.bella_hides);
//            } else if(id == 9){
//                is = getActivity().getResources().openRawResource(R.raw.big_city_noise);
//            } else if(id == 10){
//                is = getActivity().getResources().openRawResource(R.raw.birds);
//            } else if(id == 11){
//                is = getActivity().getResources().openRawResource(R.raw.butterfly);
//            } else if(id == 12){
//                is = getActivity().getResources().openRawResource(R.raw.dogs);
//            } else if(id == 13){
//                is = getActivity().getResources().openRawResource(R.raw.empress_the_blues);
//            } else if(id == 14){
//                is = getActivity().getResources().openRawResource(R.raw.fish);
//            } else if(id == 15){
//                is = getActivity().getResources().openRawResource(R.raw.flags);
//            } else if (id == 16) {
//                is = getActivity().getResources().openRawResource(R.raw.green_grass);
//            }

            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            if (is != null) {
                try {
                    //assigning the value of reader to data and test it until the condition meets,
                    while ((data = reader.readLine()) != null) {
                        stringBuffer.append(data + "");
                    }
                    txtContent.setText(stringBuffer);
                    is.close();
                } catch (Exception e) {

                }
            }
        }

    }
}
