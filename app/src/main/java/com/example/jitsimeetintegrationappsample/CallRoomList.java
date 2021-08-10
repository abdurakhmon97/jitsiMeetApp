package com.example.jitsimeetintegrationappsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CallRoomList extends AppCompatActivity implements Presenter.View{
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_room_list);

    }

    @Override
    protected void onResume() {
        presenter = new Presenter(this);

        floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.roomRV);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(CallRoomList.this, MainActivity.class);
            startActivity(intent);
        });

        presenter.usingTheDB(this);
        System.out.println("It has been used --------------------------------");
        super.onResume();
    }

    @Override
    public void setDataToRecyclerView(List<CallRoomModel> list) {
        if(list != null) {
            System.out.println("we have entered here ---------------------");
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new Adapter(this);
            adapter.setTasks(list);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void addDataToDB(JitsiMeetConferenceOptions options) {

    }
}