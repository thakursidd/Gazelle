package com.example.gazelle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class home extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

    }

    /** Called when the user taps the Send button */
    public void startMain(View view) {
        Intent intent = new Intent(this, Hub.class);
        startActivity(intent);
    }
    public void editinfo(View view) {
        Intent intent = new Intent(this, personalinfo.class);
        startActivity(intent);
    }
}