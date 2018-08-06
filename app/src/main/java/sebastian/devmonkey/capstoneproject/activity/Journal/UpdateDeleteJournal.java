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
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;

public class UpdateDeleteJournal extends AppCompatActivity {


    DatabaseHelper db;
    EditText edtTitle, edtContent;
    Button btnUpdate, btnDelete;
    String id, title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_journal);

        db = new DatabaseHelper(this);

        edtTitle = findViewById(R.id.updateTitle);
        edtContent = findViewById(R.id.updateContent);
        btnUpdate = findViewById(R.id.updatebtn);
        btnDelete = findViewById(R.id.deletebtn);
        id = getIntent().getStringExtra("ID");
        title = getIntent().getStringExtra("TITLE");
        content = getIntent().getStringExtra("CONTENT");


        edtTitle.setText(title);
        edtContent.setText(content);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();

                if (!title.equals("") && !content.equals("")) {

                    db.updateData(id,title,content);
                    Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_LONG).show();
                    edtTitle.setText("");
                    edtContent.setText("");
//                    startActivity(new Intent(getApplicationContext(), JournalFragments.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to update", Toast.LENGTH_LONG).show();

                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();

                if (!title.equals("") && !content.equals("")) {

                    db.deleteData(id);
                    Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_LONG).show();
                    edtTitle.setText("");
                    edtContent.setText("");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to delete", Toast.LENGTH_LONG).show();

                }
            }
        });


    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

}
