package com.example.jitsimeetintegrationappsample;

import android.content.Context;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Presenter {
    private CallRoomModel callRoomModel;
    private View view;
    private DBClass dbClass;
    private JitsiMeetUserInfo jitsiMeetUserInfo;

    public Presenter(View view) {
        this.callRoomModel = new CallRoomModel();
        this.jitsiMeetUserInfo = new JitsiMeetUserInfo();
        this.view = view;
    }

    public void usingTheDB(Context context){
        dbClass = new DBClass(context);
        dbClass.openDatabase();
        List<CallRoomModel> model = dbClass.getAllRooms();
        Collections.reverse(model);
        view.setDataToRecyclerView(model);
    }

    public void addToDB(Context context, String userName, String conferenceName) {
        dbClass = new DBClass(context);
        dbClass.openDatabase();

        try {
            JitsiMeetConferenceOptions defaultOptions
                    = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .setWelcomePageEnabled(true)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid server URL!");
        }

        callRoomModel.setRoomName(conferenceName);
        jitsiMeetUserInfo.setDisplayName(userName);
        callRoomModel.setUserInfo(jitsiMeetUserInfo);
        JitsiMeetConferenceOptions options
                = new JitsiMeetConferenceOptions.Builder()
                .setRoom(conferenceName)
                .setUserInfo(callRoomModel.getUserInfo())
                .build();
        CallRoomModel temp = new CallRoomModel();
        temp.setRoomName(conferenceName);
        temp.setUserInfo(jitsiMeetUserInfo);
        dbClass.insertTask(temp);

        view.addDataToDB(options);
    }

    public interface View {

        void setDataToRecyclerView(List<CallRoomModel> list);

        void addDataToDB(JitsiMeetConferenceOptions options);

    }

}
