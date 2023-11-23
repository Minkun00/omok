package com.example.myapplication;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private RecyclerView leaderboardRecyclerView;
    private LeaderboardAdapter leaderboardAdapter;
    private List<PlayerScore> playerScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        playerScores = dbHelper.getAllScores();

        // 데이터 초기화
        playerScores = new ArrayList<>();
        // 예시 데이터 추가
        playerScores.add(new PlayerScore("Player 1", 5));
        playerScores.add(new PlayerScore("Player 2", 3));

        // 리사이클러 뷰 설정
        leaderboardRecyclerView = findViewById(R.id.leaderboardRecyclerView);
        leaderboardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        leaderboardAdapter = new LeaderboardAdapter(playerScores);
        leaderboardRecyclerView.setAdapter(leaderboardAdapter);
    }

}

