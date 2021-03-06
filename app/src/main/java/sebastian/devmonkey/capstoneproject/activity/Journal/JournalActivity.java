package sebastian.devmonkey.capstoneproject.activity.Journal;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.MainActivity;
import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;

public class JournalActivity extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<String> listItem;
    SimpleAdapter adapter;
    List<Map<String, String>> data;
    Map<String, String> getData;
    ArrayList<String> id;
    ArrayList<String> content;
    String[]titleJournal,contentJournal;
    ListView listView;
    HashMap<String,String> titleContent;
    TextView textView;

    //-------------------------------------------------------------------------------
    public static ArrayList<String> itemIdList = new ArrayList<String>();
    public static ArrayList<String> titles = new ArrayList<String>();
    public static ArrayList<String> contents = new ArrayList<String>();
    public static ArrayList<String> dates = new ArrayList<String>();
    //-------------------------------------------------------------------------------

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Journal");

        db = new DatabaseHelper(this);
         titleContent = new HashMap<>();
        listItem = new ArrayList<>();
        id = new ArrayList<>();
        content = new ArrayList<>();
        listView = findViewById(R.id.listview);
        textView = findViewById(R.id.txtNoItem1);

        viewData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = listView.getItemAtPosition(i).toString();
                Intent intent = new Intent(getApplicationContext(), UpdateDeleteJournal.class);
                //putting data to intent
                intent.putExtra("ID", id.get(i));
                intent.putExtra("TITLE", listItem.get(i));
                intent.putExtra("CONTENT", content.get(i));
                startActivity(intent);

            }
        });


    }

    //getting data in database to display in listview
    private void viewData() {

        Cursor cursor = db.viewData();
        data = new ArrayList<>();

        if (cursor.getCount() == 0) {
            textView.setText("No journal entries found.");

        } else {

            textView.setText("");

            while (cursor.moveToNext()) {
                //getting id in database to array
                id.add(cursor.getString(0));

                //getting title in database
                listItem.add(cursor.getString(1)); // index 1 is the name, index 0 is id

                //getting content
                content.add(cursor.getString(2));

                titleContent.put(cursor.getString(1),cursor.getString(2));


                getData = new HashMap<String, String>(2);
                getData.put("title", cursor.getString(1));
                getData.put("date", cursor.getString(3));
                data.add(getData);
            }

            adapter = new SimpleAdapter(this, data,
                    android.R.layout.simple_list_item_2,
                    new String[] {"title", "date"},
                    new int[] {android.R.id.text1,
                            android.R.id.text2});


            listView.setAdapter(adapter);


        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search1);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    //  Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }


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
