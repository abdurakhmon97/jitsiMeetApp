package com.example.jitsimeetintegrationappsample.model;

import org.jitsi.meet.sdk.JitsiMeetUserInfo;

public class CallRoomModel {
    private int id;
    private String roomName;
    private JitsiMeetUserInfo userInfo;

    public CallRoomModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public JitsiMeetUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(JitsiMeetUserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
