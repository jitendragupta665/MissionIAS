package com.example.ayush.missionias;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    ArrayList<String> names;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager linearLayoutManager;
    DatabaseReference databaseReference;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("tag","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        names=new ArrayList<>();
        recyclerView=findViewById(R.id.recv_content);
        //linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        populate();
        load_data();
        customAdapter=new CustomAdapter(names);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(customAdapter);

    }
    public JSONArray createJsonArray(DataSnapshot dataSnapshot){
        JSONArray jsonArray=new JSONArray();
        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
            JSONObject jsonObject=new JSONObject();
            try {
                if (snapshot.getChildrenCount() > 0) {
                    jsonObject.put(snapshot.getKey(), createJsonArray(snapshot));
                } else {
                    jsonObject.put(snapshot.getKey(), snapshot.getValue());
                }
            }catch (Exception e){

            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;

    }
    public void  load_data(){
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Structure");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("tag",String.valueOf(dataSnapshot.getChildrenCount()));
                jsonArray=createJsonArray(dataSnapshot);
                try {
                    Log.i("tag",jsonArray.getJSONObject(0).getString("Point Wise Syllabus"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void populate(){
        names.add("ayush");
        names.add("john");
        names.add("joe");
        names.add("james");
        names.add("wayne");
        names.add("nike");
        names.add("rohan");
        names.add("Harry");
        names.add("mike");
        names.add("Wanes");
        names.add("Cameroon");
        names.add("Harley");
        names.add("Nicolas");
        names.add("Cena");
        names.add("Randy");
        names.add("Shawn");
        names.add("Andersan");
        names.add("Shames");
        names.add("Wiki");
        names.add("Holmes");
        names.add("Kurt");
        names.add("Hulk");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}