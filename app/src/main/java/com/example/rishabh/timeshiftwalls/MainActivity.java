package com.example.rishabh.timeshiftwalls;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setWallpaper(View view) {
        Calendar cal = Calendar.getInstance();
        int minutes = cal.get(Calendar.MINUTE);
        int offset=60-minutes;
        OneTimeWorkRequest firstChange = new OneTimeWorkRequest.Builder(periodicWorkScheduler.class).
                setInitialDelay(offset, TimeUnit.MINUTES).
                build();
        WorkManager.getInstance().enqueue(firstChange);
        Log.d("MainActivity", "Button Clicked OneTimeWorkRequest set");
    }
}
