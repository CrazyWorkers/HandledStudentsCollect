package com.ziyan.handledstudentscollect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;

public class BellwetherActivity extends AppCompatActivity {

    private final int Bellwether_Display_Length=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bellwether);
        ViewGroup view=(ViewGroup)findViewById(R.id.surface_View);
        view.addView(new DrawBellwether(BellwetherActivity.this));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(BellwetherActivity.this, LoginAndRegister.class);
                startActivity(intent);
                BellwetherActivity.this.finish();
            }
        },Bellwether_Display_Length);
        findViewById(R.id.ToLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BellwetherActivity.this, LoginAndRegister.class);
                startActivity(intent);
                BellwetherActivity.this.finish();
            }
        });
    }


}
