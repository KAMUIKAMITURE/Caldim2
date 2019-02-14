package com.kamiture.kamui.caldim2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main2Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    public void next(View v) {
        Intent intent = new Intent(Main2Activity.this, com.kamiture.kamui.caldim2.MainActivity.class);
        startActivity(intent);
    }

    public void record(View v) {
        Intent intent = new Intent(Main2Activity.this, com.kamiture.kamui.caldim2.Main3Activity.class);
        startActivity(intent);

    }

    public void boss(View v) {
        Intent intent = new Intent(Main2Activity.this, com.kamiture.kamui.caldim2.Main4Activity.class);
        startActivity(intent);
    }
}
