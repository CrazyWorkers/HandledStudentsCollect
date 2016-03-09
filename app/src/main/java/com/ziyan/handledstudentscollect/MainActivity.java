package com.ziyan.handledstudentscollect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test= (TextView) findViewById(R.id.test);
        Intent intent=getIntent();
        if(intent!=null)
        {
            test.setText(intent.getStringExtra("name"));
        }
    }

     public  void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.ExitLogin:
                Intent intent=new Intent(MainActivity.this,LoginAndRegister.class);
                startActivity(intent);
                MainActivity.this.finish();
                break;
            default:
                break;
        }

    }
}
