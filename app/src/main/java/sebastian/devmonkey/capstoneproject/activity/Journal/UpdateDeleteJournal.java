package sebastian.devmonkey.capstoneproject.activity.Journal;

import android.content.Intent;
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
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;

public class UpdateDeleteJournal extends AppCompatActivity {


    DatabaseHelper db;
    EditText edtTitle, edtContent;
    String id, title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_journal);

        db = new DatabaseHelper(this);

        edtTitle = findViewById(R.id.updateTitle);
        edtContent = findViewById(R.id.updateContent);
        id = getIntent().getStringExtra("ID");
        title = getIntent().getStringExtra("TITLE");
        content = getIntent().getStringExtra("CONTENT");


        edtTitle.setText(title);
        edtContent.setText(content);


    }



    @Override
    public void onBackPressed() {
        Back();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.update_delete, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.update:

                Update();

                return true;

            case R.id.delete:

                Delete();
                return true;

            default:
                break;
        }

        return false;
    }



    private void Update(){
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        if (!title.equals("") && !content.equals("")) {

            db.updateData(id,title,content);
            Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_LONG).show();
            edtTitle.setText("");
            edtContent.setText("");
            Back();
        } else {
            Toast.makeText(getApplicationContext(), "Failed to update", Toast.LENGTH_LONG).show();

        }
    }

    private void Delete() {
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        if (!title.equals("") && !content.equals("")) {

            db.deleteData(id);
            Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_LONG).show();
            edtTitle.setText("");
            edtContent.setText("");
            Back();
        } else {
            Toast.makeText(getApplicationContext(), "Failed to delete", Toast.LENGTH_LONG).show();

        }
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
