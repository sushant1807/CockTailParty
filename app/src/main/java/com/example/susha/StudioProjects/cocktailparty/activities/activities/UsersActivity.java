package com.example.susha.StudioProjects.cocktailparty.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.susha.StudioProjects.cocktailparty.R;
import com.example.susha.StudioProjects.cocktailparty.activities.helper.EventsAdapter;
import com.example.susha.StudioProjects.cocktailparty.activities.model.Event;
import com.example.susha.StudioProjects.cocktailparty.activities.model.Movie;

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UsersActivity extends AppCompatActivity {

    private TextView textViewName;
    private Toolbar mTopToolbar;

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recycle;
    private EventsAdapter mAdapter;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<Event> list;
    public Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_events);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(mTopToolbar);
        recycle = (RecyclerView) findViewById(R.id.recycler_view);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Events");
        query = myRef;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("logger1",""+dataSnapshot.getKey());
                list = new ArrayList<Event>();
                //for (DataSnapshot dataSnapshot3 : dataSnapshot.getChildren()) {

                for (DataSnapshot dataSnapshot3 : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                        for (DataSnapshot dataSnapshot2 : dataSnapshot4.getChildren()) {

                        Log.d("UsersActivity", dataSnapshot2.toString());
                        Log.d("UsersActivity", dataSnapshot2.getKey().toString());
                        Event value = dataSnapshot2.getValue(Event.class);
                        Event fire = new Event();
                        String name = value.getTitle();
                        String address = value.getYear();
                        String dop = value.getTime();
                        String desc = value.getDescription();

                        fire.setTitle(name);
                        fire.setYear(address);
                        fire.setTime(dop);
                        fire.setDescription(desc);

                        list.add(fire);

                    }}
                }
                showPosts(list);
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

/////////////////////////////////////////////////////////////////////////////////////////////////////
        /*recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new EventsAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();*/

//        textViewName = (TextView) findViewById(R.id.text1);
//        String nameFromIntent = getIntent().getStringExtra("EMAIL");
//        textViewName.setText("CocktailParty welcomes " + nameFromIntent);
    }

    private void showPosts(List<Event> list) {
        EventsAdapter recyclerAdapter = new EventsAdapter(list,UsersActivity.this);
        //RecyclerView.LayoutManager recycle = new GridLayoutManager(ItemObject.this,2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(UsersActivity.this);
        recycle.setLayoutManager(mLayoutManager);
        //recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);
    }

    private void prepareMovieData() {
        Movie movie = new Movie("Paris Beaches - Paris Plages", "September", "2018");
        movieList.add(movie);

        movie = new Movie("Rock en Seine Music Festival", "August", "2018");
        movieList.add(movie);

        movie = new Movie("Classique au Vert Music Festival", "August", "2018");
        movieList.add(movie);

        movie = new Movie("Liberation of Paris Festivities -  74th Anniversary Celebration", "September", "2018");
        movieList.add(movie);

        movie = new Movie("Paris Beaches - Paris Plages", "September", "2018");
        movieList.add(movie);

        movie = new Movie("Rock en Seine Music Festival", "August", "2018");
        movieList.add(movie);

        movie = new Movie("Classique au Vert Music Festival", "August", "2018");
        movieList.add(movie);

        movie = new Movie("Liberation of Paris Festivities -  74th Anniversary Celebration", "September", "2018");
        movieList.add(movie);

        movie = new Movie("Paris Beaches - Paris Plages", "September", "2018");
        movieList.add(movie);

        movie = new Movie("Rock en Seine Music Festival", "August", "2018");
        movieList.add(movie);

        movie = new Movie("Classique au Vert Music Festival", "August", "2018");
        movieList.add(movie);

        movie = new Movie("Liberation of Paris Festivities -  74th Anniversary Celebration", "September", "2018");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Intent intent = new Intent(UsersActivity.this, AddEventActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
