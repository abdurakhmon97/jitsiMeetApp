package com.example.jitsimeetintegrationappsample;



import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Presenter.View {

    private EditText conferenceName;
    private EditText yourName;
    private Button joinButton;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conferenceName = findViewById(R.id.conferenceName);
        yourName = findViewById(R.id.PersonName);
        joinButton = findViewById(R.id.joinButton);
        presenter = new Presenter(this);

        joinButton.setOnClickListener(v -> {

            String conferenceNameText = conferenceName.getText().toString();
            String yourNameText = yourName.getText().toString();

            if(!conferenceNameText.isEmpty() && !yourNameText.isEmpty()) {
                presenter.addToDB(this, yourNameText, conferenceNameText);
                finish();
            }

        });
    }

    @Override
    public void setDataToRecyclerView(List<CallRoomModel> list) {

    }

    @Override
    public void addDataToDB(JitsiMeetConferenceOptions options) {
        JitsiMeetActivity.launch(this, options);
    }
}