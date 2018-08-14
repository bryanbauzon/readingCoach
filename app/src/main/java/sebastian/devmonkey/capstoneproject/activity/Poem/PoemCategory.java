package sebastian.devmonkey.capstoneproject.activity.Poem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.other.Arrays;

public class PoemCategory extends AppCompatActivity {


    ArrayAdapter<String> listviewAdapter;
    String[] menuItems;

    ListView listView, listView1, listView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_category);

        Arrays storyTitles = new Arrays();

        menuItems = storyTitles.getStoryTitles();


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
                startActivity(new Intent(getApplicationContext(), PoemReading.class));
            }
        });


    }
}
