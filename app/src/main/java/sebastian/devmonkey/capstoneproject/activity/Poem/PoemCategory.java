package sebastian.devmonkey.capstoneproject.activity.Poem;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
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
TextToSpeech textToSpeech;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

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





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    listviewAdapter.getFilter().filter(newText);

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



    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                //startActivity(new Intent(getApplicationContext(), StoryCategory.class));
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onStop() {
        super.onStop();
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
