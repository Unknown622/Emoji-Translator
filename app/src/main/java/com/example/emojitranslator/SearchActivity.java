package com.example.emojitranslator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Iterator;

public class SearchActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ExampleItem> emojiList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search Emojis");

        fillList();
        setUpRecyclerView();
    }

    private void fillList()
    {
        emojiList = new ArrayList<>();
        Iterator<Emoji> iterator = EmojiList.instance().getEmojis();

        while (iterator.hasNext())
        {
            Emoji e = (Emoji) iterator.next();

            emojiList.add(new ExampleItem(e.toString(),
                    arrayToString(e.getDescription(), e.getDescriptionLength())));
        }
    }

    private void setUpRecyclerView()
    {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(emojiList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        android.widget.SearchView searchView = (android.widget.SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }

    private String arrayToString(String[] array, int length)
    {
        String string = "";

        for (int i = 0; i < length; ++i)
        {
            string += array[i];

            if (i != (length - 1))
                string += ", ";
        }

        return string;
    }
}
