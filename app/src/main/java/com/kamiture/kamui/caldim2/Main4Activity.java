package com.kamiture.kamui.caldim2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static com.kamiture.kamui.caldim2.R.drawable.hebi;
import static com.kamiture.kamui.caldim2.R.drawable.raion;
import static com.kamiture.kamui.caldim2.R.drawable.tori;

public class Main4Activity extends AppCompatActivity {
    int Level;
    int check;
    private final int STAGE_1_CLEAR = 1;
    private final int STAGE_2_CLEAR = 2;
    private final int STAGE_3_CLEAR = 3;
    private final int STAGE_4_CLEAR = 4;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        imageView = (ImageView) findViewById(R.id.boss);
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        Level = data.getInt("LevelSave", 0);
        check = data.getInt("imagecount", 0);
        if (check == STAGE_1_CLEAR) {
            imageView.setImageResource(R.drawable.hebi);
        } else if (check == STAGE_2_CLEAR) {
            imageView.setImageResource(R.drawable.tori);
        } else if (check == STAGE_3_CLEAR) {
            imageView.setImageResource(R.drawable.raion);
        }
    }


    public void charenge(View v) {

        if (Level < 100) {
            Toast toast = Toast.makeText(Main4Activity.this, "まだレベルが足りないよ！もっと頑張ろう！", Toast.LENGTH_SHORT);
            toast.show();
        } else if (Level < 300) {
            if (check == STAGE_1_CLEAR) {
                Toast.makeText(Main4Activity.this, "まだレベルが足りないよ！もっと頑張ろう！", Toast.LENGTH_SHORT).show();
            } else {
                Toast toast = Toast.makeText(Main4Activity.this, "やった！ボスを倒したぞ。次のボスを倒そう！", Toast.LENGTH_SHORT);
                toast.show();
                imageView.setImageResource(hebi);
                check = STAGE_1_CLEAR;

            }

        } else if (Level < 500) {
            if (check == STAGE_2_CLEAR) {
                Toast.makeText(Main4Activity.this, "まだレベルが足りないよ！もっと頑張ろう！", Toast.LENGTH_SHORT).show();
            } else {
                Toast toast = Toast.makeText(Main4Activity.this, "やった！ボスを倒したぞ。次のボスを倒そう！", Toast.LENGTH_SHORT);
                toast.show();
                imageView.setImageResource(tori);
                check = STAGE_2_CLEAR;

            }

        } else if (Level < 800) {
            if (check == STAGE_3_CLEAR) {
                Toast.makeText(Main4Activity.this, "まだレベルが足りないよ！もっと頑張ろう！", Toast.LENGTH_SHORT).show();
            } else {
                Toast toast = Toast.makeText(Main4Activity.this, "やった！ボスを倒したぞ。次のボスを倒そう！", Toast.LENGTH_SHORT);
                toast.show();
                imageView.setImageResource(raion);
                check = STAGE_3_CLEAR;

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (check == STAGE_1_CLEAR) {
            SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putInt("bossimage", raion);
            editor.putInt("imagecount", check);
            editor.apply();
        }
    }
}
