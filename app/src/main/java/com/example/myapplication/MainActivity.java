package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String YOUTUBE_ID = "y0kBs4-DNe8";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("펜타고");

        Button tutorialButton = findViewById(R.id.Tutorial);
        tutorialButton.setOnClickListener(this::onClickTutorial);
        Button startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(this::onClickGameButton);
        Button leaderboardButton = findViewById(R.id.leaderboardButton);
        leaderboardButton.setOnClickListener(this::onClickLeaderboardButton);
    }

    private void onClickTutorial(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + YOUTUBE_ID));
        intent.putExtra("VIDEO_ID", YOUTUBE_ID);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + YOUTUBE_ID));
            startActivity(intent);
        }
    }

    private void onClickGameButton(View view) {
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
    }
    private void onClickLeaderboardButton(View view) {
        Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
        startActivity(intent);
    }

}
