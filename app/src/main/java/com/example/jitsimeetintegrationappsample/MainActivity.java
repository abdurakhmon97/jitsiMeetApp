package com.example.jitsimeetintegrationappsample;


import static com.google.android.gms.tasks.Tasks.await;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;
import org.jitsi.meet.sdk.JitsiMeetView;

import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private EditText conferenceName;
    private EditText yourName;
    private Button joinButton;
    private JitsiMeetUserInfo jitsiMeetUserInfo = new JitsiMeetUserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conferenceName = findViewById(R.id.conferenceName);
        yourName = findViewById(R.id.PersonName);
        joinButton = findViewById(R.id.joinButton);

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

        joinButton.setOnClickListener(v -> {
            String conferenceNameText = conferenceName.getText().toString();
            String yourNameText = yourName.getText().toString();

            if(!conferenceNameText.isEmpty() && !yourNameText.isEmpty()) {
                jitsiMeetUserInfo.setDisplayName(yourNameText);
                JitsiMeetConferenceOptions options
                        = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(conferenceNameText)
                        .setUserInfo(jitsiMeetUserInfo)
                        .build();
                JitsiMeetActivity.launch(MainActivity.this, options);
            }

        });
    }

}