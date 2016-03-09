package com.ziyan.handledstudentscollect;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class BellwetherActivity extends AppCompatActivity {

    private static  final  int arg=0x123;
    private TextView appName;
    private Animation scaleAnimation;
    private ImageView heart;
    private TextView app_target;
    private DBHelper dbHelper;

    private Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case arg:
                    Intent intent=new Intent(BellwetherActivity.this,LoginAndRegister.class);

                    startActivity(intent);
                    BellwetherActivity.this.finish();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bellwether);
        File sd = Environment.getExternalStorageDirectory();
        String path = sd.getPath() + "/掌上同学汇";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
            Log.e("test", file.getPath());
        }
        appName =(TextView)findViewById(R.id.appName);
        app_target =(TextView)findViewById(R.id.app_target);
        heart =(ImageView)findViewById(R.id.heart);
        scaleAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_app_name);
        appName.startAnimation(scaleAnimation);
        app_target.setAnimation(scaleAnimation);
        appName.setTextSize(30);
        scaleAnimation=AnimationUtils.loadAnimation(this,R.anim.anim_heart);
        heart.startAnimation(scaleAnimation);
        Message msg=new Message();
        msg.what=arg;
        mHandler.sendMessageDelayed(msg,5000);
    }
}
