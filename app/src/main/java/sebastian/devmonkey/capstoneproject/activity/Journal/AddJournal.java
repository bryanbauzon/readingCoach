package sebastian.devmonkey.capstoneproject.activity.Journal;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Add Journal");

        db = new DatabaseHelper(this);
        btnSave = findViewById(R.id.save);
        edtTitle = findViewById(R.id.inputTitle);
        edtContent = findViewById(R.id.inputContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.save_journal, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save:
                 save();
                return true;

            case android.R.id.home:
                Back();
                return true;

            default:
                break;
        }

        return false;
    }
    private void save(){
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        if (!title.equals("") && !content.equals("") && db.insertData(title, content)) {
            //Toast.makeText(getApplicationContext(), "Data added", Toast.LENGTH_LONG).show();
            edtTitle.setText("");
            edtContent.setText("");
            edtTitle.requestFocus();
            Back();
        } else {
            if(edtTitle.getText().toString().isEmpty() || edtTitle.getText().toString() == "" ){
                edtTitle.requestFocus();
                edtTitle.setError("Title is empty.");
            }else {
                edtContent.requestFocus();
                edtContent.setError("Content is empty.");
            }
        }

    }

    @Override
    public void onBackPressed() {
        Back();
    }


    private void Back() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            //
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
