package com.example.jitsimeetintegrationappsample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.jitsimeetintegrationappsample.model.CallRoomModel;

import org.jitsi.meet.sdk.JitsiMeetUserInfo;

import java.util.ArrayList;
import java.util.List;


public class DBClass extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "callRoomDB";
    private static final String CALLROOM_TABLE = "callRoom";
    private static final String ID = "id";
    private static final String ROOM_NAME = "roomName";
    private static final String USER_NAME = "userName";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + CALLROOM_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ROOM_NAME + " TEXT, "
            + USER_NAME + " TEXT)";

    private SQLiteDatabase db;

    public DBClass(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CALLROOM_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(CallRoomModel crm){
        ContentValues cv = new ContentValues();
        System.out.println("it is insert task DB class ------------------------- \n");
        cv.put(ROOM_NAME, crm.getRoomName());
        cv.put(USER_NAME, crm.getUserInfo().getDisplayName());
        db.insert(CALLROOM_TABLE, null, cv);
    }

    public List<CallRoomModel> getAllRooms(){
        System.out.println("happening ------------------------------");
        List<CallRoomModel> CRMList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(CALLROOM_TABLE, null, null, null, null, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do {
                        CallRoomModel crm = new CallRoomModel();
                        crm.setId(cur.getInt(cur.getColumnIndex(ID)));
                        crm.setRoomName(cur.getString(cur.getColumnIndex(ROOM_NAME)));
                        JitsiMeetUserInfo jitsiMeetUserInfo = new JitsiMeetUserInfo();
                        jitsiMeetUserInfo.setDisplayName(cur.getString(cur.getColumnIndex(USER_NAME)));
                        crm.setUserInfo(jitsiMeetUserInfo);

                        CRMList.add(crm);
                    }
                    while (cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return CRMList;
    }


    public void deleteTask(int id){
        db.delete(CALLROOM_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }

}