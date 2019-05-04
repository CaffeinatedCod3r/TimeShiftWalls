package com.example.rishabh.timeshiftwalls;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import android.view.WindowManager;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class changeWallpaper extends Worker {
    private static final String TAG = changeWallpaper.class.getSimpleName();

    public changeWallpaper(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Worker.Result doWork() {

        Context applicationContext = getApplicationContext();

        try {
            final String filename;
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (String.valueOf(hour).length() == 1) {

                filename = "mojave_dynamic_0" + String.valueOf(hour);
            } else {
                filename = "mojave_dynamic_" + String.valueOf(hour);
            }
            final int resId = applicationContext.getResources().getIdentifier(filename, "drawable", applicationContext.getPackageName());
            Bitmap tempbitMap = BitmapFactory.decodeResource(applicationContext.getResources(), resId);
            Log.d(TAG, filename);

            WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            int width = size.x;
            int height = size.y;
            Bitmap b2 = Bitmap.createScaledBitmap(tempbitMap, width, height, true);
            WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
            myWallpaperManager.setBitmap(b2);
            return Result.success();
        } catch (Throwable throwable) {

            Log.e(TAG, "Error Setting Wallpaper", throwable);
            return Result.failure();
        }
    }
}
