package sebastian.devmonkey.capstoneproject.activity.Journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.Journal.AddJournal;
import sebastian.devmonkey.capstoneproject.activity.Journal.UpdateDeleteJournal;
import sebastian.devmonkey.capstoneproject.activity.MainActivity;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;

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
//                Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();

//                Toast.makeText(getApplicationContext(), id.get(i), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), UpdateDeleteJournal.class);
                //putting data to intent
                intent.putExtra("ID", id.get(i));
                intent.putExtra("TITLE", title);
                intent.putExtra("CONTENT", content.get(i));
                startActivity(intent);

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
            //display title to listview


            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);

            listView.setAdapter(adapter);
            //  listView.notifyDataSetChanged(adapter);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
                startActivity(new Intent(getApplicationContext(), AddJournal.class));
                //    this.overridePendingTransition()
                return true;

            default:
                break;
        }

        return false;

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
