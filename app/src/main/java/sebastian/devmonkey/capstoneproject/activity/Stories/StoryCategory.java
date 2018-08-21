package sebastian.devmonkey.capstoneproject.activity.Stories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.Arrays;

public class StoryCategory extends AppCompatActivity {


    ArrayAdapter<String> listviewAdapter;
    String[] menuItems;

    ListView listView, listView1, listView2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_category);

        Arrays storyTitles = new Arrays();

        menuItems = storyTitles.getStoryTitles();

        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Story");


        listView = findViewById(R.id.easyList);
        listView1 = findViewById(R.id.mediumList);
        listView2 = findViewById(R.id.hardList);


        //get array and display to listview
        listviewAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                menuItems
        );

        listView.setAdapter(listviewAdapter);
        listView1.setAdapter(listviewAdapter);
        listView2.setAdapter(listviewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  Object object = adapter.getI
                //Toast.makeText(getApplicationContext(),"id "+ l,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),StoryReading.class);
                intent.putExtra("level","easy");
                String value = Long.toString(l);
                intent.putExtra("id",value);
                startActivity(intent);
                finish();

            }
        });


    }


    public boolean onOptionsItemSelected(MenuItem item){
        Back();
        return true;

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
