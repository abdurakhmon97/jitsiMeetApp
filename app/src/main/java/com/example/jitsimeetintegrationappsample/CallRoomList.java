package com.example.jitsimeetintegrationappsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jitsi.meet.sdk.JitsiMeetUserInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CallRoomList extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<CallRoomModel> list;
    private List<CallRoomModel> dbList;
    private DBClass db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_room_list);
        /*db = new DBClass(this);
        db.openDatabase();

        floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.roomRV);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(CallRoomList.this, MainActivity.class);
            startActivity(intent);
        });


*//*
        CallRoomModel crm = new CallRoomModel();
        crm.setRoomName("Testing 1234");
        JitsiMeetUserInfo jitsiMeetUserInfo = new JitsiMeetUserInfo();
        jitsiMeetUserInfo.setDisplayName("Jon");
        crm.setUserInfo(jitsiMeetUserInfo);

        CallRoomModel crm1 = new CallRoomModel();
        crm1.setRoomName("Testing 12345");
        JitsiMeetUserInfo jitsiMeetUserInfo1 = new JitsiMeetUserInfo();
        jitsiMeetUserInfo1.setDisplayName("Jon1");
        crm1.setUserInfo(jitsiMeetUserInfo1);

        CallRoomModel crm2 = new CallRoomModel();
        crm2.setRoomName("Testing 123456");
        JitsiMeetUserInfo jitsiMeetUserInfo2 = new JitsiMeetUserInfo();
        jitsiMeetUserInfo2.setDisplayName("Jon2");
        crm2.setUserInfo(jitsiMeetUserInfo2);

        list.add(crm);
        list.add(crm1);
        list.add(crm2);
*//*


        dbList = db.getAllRooms();
        System.out.println(dbList.toString() + " ---------------- here db");
        if(dbList != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new Adapter(this);
            adapter.setTasks(dbList);
            recyclerView.setAdapter(adapter);
        }*/

    }

    @Override
    protected void onResume() {
        db = new DBClass(this);
        db.openDatabase();

        floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.roomRV);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(CallRoomList.this, MainActivity.class);
            startActivity(intent);
        });

        dbList = db.getAllRooms();
        Collections.reverse(dbList);
        System.out.println(dbList.toString() + " ---------------- here db");
        if(dbList != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new Adapter(this);
            adapter.setTasks(dbList);
            recyclerView.setAdapter(adapter);
        }
        super.onResume();
    }
}