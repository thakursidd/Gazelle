package com.example.gazelle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.VideoView;

public class Hub extends AppCompatActivity {
private VideoView mVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hub);

    }
    /** Called when the user taps the Send button */
    public void startMain2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}