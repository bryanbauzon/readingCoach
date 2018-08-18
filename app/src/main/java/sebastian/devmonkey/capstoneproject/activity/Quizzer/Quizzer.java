package sebastian.devmonkey.capstoneproject.activity.Quizzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import sebastian.devmonkey.capstoneproject.R;

public class Quizzer extends AppCompatActivity implements View.OnClickListener {

    EasyStoryOne object = new EasyStoryOne();

    Button a,b,c,d;

    int max = 10;
    int randomized;
    int score = 0;

    Random rand = new Random(max);

    TextView questions = null, containerScore = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzer);

        Intent intent = getIntent();
        questions = findViewById(R.id.question);
        containerScore = findViewById(R.id.score);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);

         shuffle();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.a:
                if(a.getText().toString() == object.correct[randomized]){
                    Toast.makeText(this,"You got it",Toast.LENGTH_SHORT).show();
                    incrementScore();

                    shuffle();
                }else{
                    shuffle();
                }
                break;
            case R.id.b:
                if(b.getText().toString() == object.correct[randomized]){
                    Toast.makeText(this,"You got it",Toast.LENGTH_SHORT).show();
                    incrementScore();

                    shuffle();
                }else{
                    shuffle();
                }
                break;
            case R.id.c:
                if(c.getText().toString() == object.correct[randomized]){
                    Toast.makeText(this,"You got it",Toast.LENGTH_SHORT).show();
                    incrementScore();
                    shuffle();
                }else{
                    shuffle();
                }
                break;
            default:
                if(d.getText().toString() == object.correct[randomized]){
                    Toast.makeText(this,"You got it",Toast.LENGTH_SHORT).show();
                    incrementScore();

                    shuffle();
                }else{
                    shuffle();
                }
                break;
        }

    }
    public void shuffle(){
            randomized = rand.nextInt(max);
           questions.setText(object.question[randomized]);
            a.setText(object.answers[randomized][0]);
            b.setText(object.answers[randomized][1]);
            c.setText(object.answers[randomized][2]);
            d.setText(object.answers[randomized][3]);

    }
    public void incrementScore(){
        score++;
        containerScore.setText(Integer.toString(score));
    }


}
