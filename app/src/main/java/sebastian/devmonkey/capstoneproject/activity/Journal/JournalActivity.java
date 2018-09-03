package sebastian.devmonkey.capstoneproject.activity.Journal;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.MainActivity;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;

public class JournalActivity extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ArrayList<String> id;
    ArrayList<String> content;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Journal");

        db = new DatabaseHelper(this);
        listItem = new ArrayList<>();
        id = new ArrayList<>();
        content = new ArrayList<>();
        listView = findViewById(R.id.listview);

        viewData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = listView.getItemAtPosition(i).toString();
                Intent intent = new Intent(getApplicationContext(), UpdateDeleteJournal.class);
                //putting data to intent
                intent.putExtra("ID", id.get(i));
                intent.putExtra("TITLE", title);
                intent.putExtra("CONTENT", content.get(i));
                startActivity(intent);
                finish();

            }
        });


    }

    //getting data in database to display in listview
    private void viewData() {

        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No data to show", Toast.LENGTH_SHORT).show();

        } else {

            while (cursor.moveToNext()) {
                //getting id in database to array
                id.add(cursor.getString(0));

                //getting title in database
                listItem.add(cursor.getString(1)); // index 1 is the name, index 0 is id

                //getting content
                content.add(cursor.getString(2));

            }


            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);

            listView.setAdapter(adapter);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
                startActivity(new Intent(getApplicationContext(), AddJournal.class));
                //    this.overridePendingTransition()
                return true;

            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
