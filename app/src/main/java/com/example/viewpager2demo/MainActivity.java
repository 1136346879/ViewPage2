package com.example.viewpager2demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * 首页
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnHorizontalScrolling = findViewById(R.id.btn_horizontal_scrolling);
        btnHorizontalScrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HorizontalScrollingActivity.start(MainActivity.this);
            }
        });
        Button btnVerticalScrolling = findViewById(R.id.button_vertical_scrolling);
        btnVerticalScrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerticalScrollingActivity.start(MainActivity.this);
            }
        });
        Button btnFragmentStateAdapter = findViewById(R.id.button_fragment_state_adpater);
        btnFragmentStateAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentStateAdapterActivity.start(MainActivity.this);
            }
        });
        Button btnActivityFragmentStateAdapter = findViewById(R.id.button_activity_fragment_state_adpater);
        btnActivityFragmentStateAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTabStateActivity.start(MainActivity.this);
            }
        });
    }
}
