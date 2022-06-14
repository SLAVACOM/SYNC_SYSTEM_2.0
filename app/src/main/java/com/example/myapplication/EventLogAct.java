package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.sqlite.DataBaseHelper;
import com.example.myapplication.adapter.EventListAdapter;

import java.util.ArrayList;

public class EventLogAct extends AppCompatActivity {
    private MenuItem item;
    private static int size = 0, scrollcount=0;
    private RecyclerView recyclerView;
    private DataBaseHelper dataBaseHelper;
    private ArrayList<String> id, time, event;
    private EventListAdapter customAdapter;
    private boolean ubdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_log);

        init();
        customAdapter= new EventListAdapter(EventLogAct.this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventLogAct.this));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                    if (!recyclerView.canScrollVertically(1)){
                        if (ubdate) {
                            if (scrollcount > 0) {
                                customAdapter.notifyDataSetChanged();
                                scrollcount = 0;
                            } else {
                                scrollcount++;
                                Toast.makeText(EventLogAct.this, "Потяни ещё раз для добавления", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EventLogAct.this, "Больше данных нет", Toast.LENGTH_SHORT).show();
                        }
                    } }

        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private  void  init(){

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        dataBaseHelper = new DataBaseHelper(EventLogAct.this);
        recyclerView = findViewById(R.id.recycler);
        id = new ArrayList<>();
        time = new ArrayList<>();
        event = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppStatus.openStatus = true;
        customAdapter.notifyDataSetChanged();
    }



    public void update() {
    }
}
