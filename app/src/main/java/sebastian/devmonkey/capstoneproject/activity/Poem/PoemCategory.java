package sebastian.devmonkey.capstoneproject.activity.Poem;

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


public class PoemCategory extends AppCompatActivity {


    ListView listView;
    ArrayAdapter<String> listviewAdapter;

    String[] poemTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_category);

        listView = findViewById(R.id.poemListview);

        setTitle("Poems");
        //back Button beside activity title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Arrays poemArrays = new Arrays();
        poemTitles = poemArrays.getPoemTitles();

        listviewAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                poemTitles
        );

        listView.setAdapter(listviewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),PoemReading.class);
                intent.putExtra("level","poem");
                String value = Long.toString(l);
                intent.putExtra("id",value);
                String title = adapterView.getItemAtPosition(i).toString();
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

    }





    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                //startActivity(new Intent(getApplicationContext(), StoryCategory.class));
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);

    }
}
