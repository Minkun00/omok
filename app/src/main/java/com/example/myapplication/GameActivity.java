package com.example.myapplication;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private static final float ROTATION_THRESHOLD = 1.0f; // 회전 감지 임계값

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;

    private boolean isEventNeed = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("자이로 인식하기");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gyroscopeEventListener = new RotatingSensorEventListener();

        Button button = findViewById(R.id.eventTest);
        button.setOnClickListener(this::onButtonClick);
        Button exitButton = findViewById(R.id.eventTestExit);
        exitButton.setOnClickListener(this::onExitButtonClick);
    }

    public void onButtonClick(View view) {
        isEventNeed = true;
    }

    public void onExitButtonClick(View view) {
        isEventNeed = false;
    }

    private class RotatingSensorEventListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (!isEventNeed) {
                return;
            }

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // x, y, z 값으로 회전 감지
            if (Math.abs(x) > ROTATION_THRESHOLD) {
                if (x > 0) {
                    Toast.makeText(GameActivity.this, "오른쪽으로 돌렸습니다", Toast.LENGTH_SHORT).show();
                    System.out.println("오른쪽");
                } else {
                    Toast.makeText(GameActivity.this, "왼쪽으로 돌렸습니다", Toast.LENGTH_SHORT).show();
                    System.out.println("왼쪽");
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // 센서 정확도 변경 이벤트 처리
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gyroscopeSensor != null) {
            sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "자이로 센서가 지원되지 않습니다", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventListener);
    }
}
