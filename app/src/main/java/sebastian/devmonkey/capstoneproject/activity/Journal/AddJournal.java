package sebastian.devmonkey.capstoneproject.activity.Journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.MainActivity;
import sebastian.devmonkey.capstoneproject.fragments.JournalFragments;
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
    }

    public void save(View view){
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        if (!title.equals("") && !content.equals("") && db.insertData(title, content)) {
            Toast.makeText(getApplicationContext(), "Data added", Toast.LENGTH_LONG).show();
            edtTitle.setText("");
            edtContent.setText("");
            edtTitle.requestFocus();
            Back();
        } else {
            if(edtTitle.getText().toString().isEmpty() || edtTitle.getText().toString() == "" ){
                edtTitle.requestFocus();
                edtTitle.setError("Title is empty.");
            }else if(edtContent.getText().toString().isEmpty() || edtContent.getText().toString() == ""){
                edtContent.requestFocus();
                edtContent.setError("Content is empty.");
            }else{
                Toast.makeText(this,"All fields are required to fill-up.",Toast.LENGTH_SHORT).show();
                edtTitle.requestFocus();
            }
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, JournalFragments.class));
    }

    //Returning and Refreshing
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        startActivity(new Intent(this, JournalFragments.class));
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
