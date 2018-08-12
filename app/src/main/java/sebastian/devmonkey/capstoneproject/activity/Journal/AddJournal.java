package sebastian.devmonkey.capstoneproject.activity.Journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;

public class

AddJournal extends AppCompatActivity {

    DatabaseHelper db;
    Button btnSave;
    EditText edtTitle, edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);

        db = new DatabaseHelper(this);

        btnSave = findViewById(R.id.save);
        edtTitle = findViewById(R.id.inputTitle);
        edtContent = findViewById(R.id.inputContent);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();

                if (!title.equals("") && !content.equals("") && db.insertData(title, content)) {
                    Toast.makeText(getApplicationContext(), "Data added", Toast.LENGTH_LONG).show();
                    edtTitle.setText("");
                    edtContent.setText("");
                    startActivity(new Intent(getApplicationContext(), Journal.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_LONG).show();

                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Journal.class));
    }

    //Returning and Refreshing
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        startActivity(new Intent(this, Journal.class));
    }
}
