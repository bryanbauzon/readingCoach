package sebastian.devmonkey.capstoneproject.activity.Poem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sebastian.devmonkey.capstoneproject.R;

public class PoemCategory extends AppCompatActivity {

    ArrayAdapter<String> listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_difficulty);


        // array
        String[] menuItems = {"Story 1", "Story 2", "Story 3"};

        ListView listView1 = findViewById(R.id.easyList);
        ListView listView2 = findViewById(R.id.mediumList);
        ListView listView3 = findViewById(R.id.hardList);

        //get array and display to listview
        listview = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                menuItems
        );


        listView1.setAdapter(listview);
        listView2.setAdapter(listview);
        listView3.setAdapter(listview);


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(), Story.class));
                finish();
            }
        });






    }
}
