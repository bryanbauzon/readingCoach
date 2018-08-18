package sebastian.devmonkey.capstoneproject.activity.Quizzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.Arrays;

public class Quizzer extends AppCompatActivity implements View.OnClickListener {

    private Arrays QuestionLibrary = new Arrays();

    List<Integer> available;

    Button a,b,c,d;

    private String answer;
    private int score = 0;
    private int questionNumber = 0;


    TextView questions = null, containerScore = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzer);

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

        available = new ArrayList<>();

        for (int i = 0; i< 10; i++) {
            available.add(i);
        }

        updateQuestion();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.a:
                if (a.getText() == answer) {
                    score++;
                    updateScore(score);
                    updateQuestion();

                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }

                break;
            case R.id.b:
                if (b.getText() == answer) {
                    score++;
                    updateScore(score);
                    updateQuestion();

                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                break;
            case R.id.c:
                if (c.getText() == answer) {
                    score++;
                    updateScore(score);
                    updateQuestion();

                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                break;

            case R.id.d:
            if (d.getText() == answer) {
                score++;
                updateScore(score);
                updateQuestion();

                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                updateQuestion();
            }
            break;



            default:

        }

    }
//    public void shuffle(){
//            randomized = rand.nextInt(max);
//           questions.setText(object.question[randomized]);
//            a.setText(object.answers[randomized][0]);
//            b.setText(object.answers[randomized][1]);
//            c.setText(object.answers[randomized][2]);
//            d.setText(object.answers[randomized][3]);
//
//    }

    private void updateQuestion() {

        Collections.shuffle(available);

        questions.setText(QuestionLibrary.getQuestion(available.get(0)));
        a.setText(QuestionLibrary.getChoice1(available.get(0)));
        b.setText(QuestionLibrary.getChoice2(available.get(0)));
        c.setText(QuestionLibrary.getChoice3(available.get(0)));
        d.setText(QuestionLibrary.getChoice4(available.get(0)));
        answer = QuestionLibrary.getCorrectAnswer(available.get(0));

        available.remove(0);

        questionNumber++;
    }

    private void updateScore (int point) {
        containerScore.setText("" + point);
    }


}
