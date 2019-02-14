package com.kamiture.kamui.caldim2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements Runnable, SensorEventListener {

    private static final int DIRECTION_UP = 0;
    private static final int DIRECTION_DOWN = 1;
    private static final int DIRECTION_RIGHT = 2;
    private static final int DIRECTION_LEFT = 3;
    private static final int DIRECTION_NONE = 4;
    Random randomQuestion = new Random();
    SensorManager sm;
    float gx, gy, gz;
    Handler h;
    int questionNumber1;
    int questionNumber2;
    int correctAnswer;
    int scoreNumber;
    int recordNumber;
    float x = 0;
    float y = 0;
    float z = 0;
    int answerDirection;
    int all;
    int Level;
    private TextView answerText_UP;
    private TextView answerText_LEFT;
    private TextView answerText_RIGHT;
    private TextView answerText_DOWN;
    private TextView questionText1;
    private TextView questionText2;
    private TextView Sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        Level = data.getInt("LevelSave", 0);


        answerText_UP = (TextView) findViewById(R.id.anser1);
        answerText_LEFT = (TextView) findViewById(R.id.answer2);
        answerText_RIGHT = (TextView) findViewById(R.id.answer3);
        answerText_DOWN = (TextView) findViewById(R.id.answer4);
        questionText1 = (TextView) findViewById(R.id.count1);
        questionText2 = (TextView) findViewById(R.id.count2);
        Sign = (TextView) findViewById(R.id.sign);
        h = new Handler();
        h.postDelayed(this, 500);


        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors =
                sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (0 < sensors.size()) {
            sm.registerListener(this, sensors.get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);

//        makeQuestions();
//        startQuestions();
        }
        make();
    }


    private void make() {
        int firstQuestion = randomQuestion.nextInt(9);
        int secondQuestion = randomQuestion.nextInt(9);
        int sign = randomQuestion.nextInt(3);
        answerDirection = randomQuestion.nextInt(4);

        questionNumber1 = firstQuestion;
        questionText1.setText(String.valueOf(questionNumber1));

        questionNumber2 = secondQuestion;
        questionText2.setText(String.valueOf(questionNumber2));


        if (sign == 0) {
            this.Sign.setText("+");
            plus();
        } else if (sign == 1) {
            this.Sign.setText("-");
            minus();
        } else if (sign == 2) {
            this.Sign.setText("×");
            kakeru();
        }
        if (answerDirection == DIRECTION_UP) {
            answerText_UP.setText(String.valueOf(correctAnswer));
            answerText_LEFT.setText(String.valueOf(correctAnswer - 1));
            answerText_RIGHT.setText(String.valueOf(correctAnswer + 1));
            answerText_DOWN.setText(String.valueOf(correctAnswer + 10));

        } else if (answerDirection == DIRECTION_LEFT) {
            answerText_UP.setText(String.valueOf(correctAnswer + 1));
            answerText_LEFT.setText(String.valueOf(correctAnswer));
            answerText_RIGHT.setText(String.valueOf(correctAnswer + 10));
            answerText_DOWN.setText(String.valueOf(correctAnswer - 1));
        } else if (answerDirection == DIRECTION_RIGHT) {
            answerText_UP.setText(String.valueOf(correctAnswer - 1));
            answerText_LEFT.setText(String.valueOf(correctAnswer + 10));
            answerText_RIGHT.setText(String.valueOf(correctAnswer));
            answerText_DOWN.setText(String.valueOf(correctAnswer + 1));
        } else {
            answerText_UP.setText(String.valueOf(correctAnswer + 10));
            answerText_LEFT.setText(String.valueOf(correctAnswer + 1));
            answerText_RIGHT.setText(String.valueOf(correctAnswer - 1));
            answerText_DOWN.setText(String.valueOf(correctAnswer));
        }

//        doingAnswer();

    }

    private void plus() {
        correctAnswer = questionNumber1 + questionNumber2;

    }


    private void minus() {
        correctAnswer = questionNumber1 - questionNumber2;


    }

    private void kakeru() {
        correctAnswer = questionNumber1 * questionNumber2;

    }


    private int checkDirection() {
        recordNumber++;
        x = x + gx;
        y = y + gy;
        z = z + gz;
//        Log.d("tags",String.valueOf(x));
//        Log.d("tags",String.valueOf(y));


        if (recordNumber == 5) {

            recordNumber = 0;
//            if (y / 5 < -5) {
            if (y / 5 < -5) {
                x = 0;
                y = 0;
                z = 0;
                return DIRECTION_UP;


//                number6 = 1;
//                checkAnswer();
//            } else if (y / 5 > 5) {
            } else if (y / 5 > 5) {
                x = 0;
                y = 0;
                z = 0;
                return DIRECTION_DOWN;

//                number6 = 2;
//                checkAnswer();
//            } else if (x / 5 > 5) {
            } else if (x / 5 > 5) {
                x = 0;
                y = 0;
                z = 0;
                return DIRECTION_LEFT;

//                number6 = 3;
//                checkAnswer();
//            } else if (x / 5 < -5) {
            } else if (x / 5 < -5) {
                x = 0;
                y = 0;
                z = 0;
                return DIRECTION_RIGHT;

//                number6 = 4;
//                checkAnswer();
            }
        }
        return DIRECTION_NONE;
//                number6 = 0;


    }


    private void checkAnswer() {
        int direction = checkDirection();
        all = scoreNumber + Level;
//      Log.d("DIRECTION", String.valueOf(direction));

        if (answerDirection == direction) {
//            正解のとき
            scoreNumber++;

            if (all == 1) {
                Toast toast = Toast.makeText(MainActivity.this, "レベルが上がったよ！スタート画面からボスに挑戦だ", Toast.LENGTH_SHORT);
                toast.show();
                make();
            } else if (all == 300) {
                Toast toast = Toast.makeText(MainActivity.this, "レベルが上がったよ！スタート画面からボスに挑戦だ", Toast.LENGTH_SHORT);
                toast.show();
                make();
            } else if (all == 500) {
                Toast toast = Toast.makeText(MainActivity.this, "レベルが上がったよ！スタート画面からボスに挑戦だ", Toast.LENGTH_SHORT);
                toast.show();
                make();
            } else if (all == 800) {
                Toast toast = Toast.makeText(MainActivity.this, "レベルが上がったよ！スタート画面からボスに挑戦だ", Toast.LENGTH_SHORT);
                toast.show();
                make();
            } else {
                make();
            }


        } else if (direction != DIRECTION_NONE) {
//          不正解のとき
        } else {
//          加速度センサ測定中
        }
    }


//    private void doingAnswer(){
//        int answer = rando.nextInt(4);
//        boolean flag = true;
//
//            try {
//                Thread.sleep(100);
//            }catch (InterruptedException e){
//                Log.e("error", e.toString());
//            }
//            if (answer == 0 && gy < -3) {
//
//            } else if (answer == 1 && gx > 5) {
//
//
//
//            } else if (answer == 2 && gx < -5) {
//
//
//
//            } else if (answer == 3 && gy > 5) {
//


//            } else {
//
//            }
//        }

//    private void check (){
//
//        recordNumber++;
//        x = x+gx;
//         y = y+gy;
//         z = z + gz;
//        int answer = rando.nextInt(4);
//        if (recordNumber == 5) {
//            recordNumber = 0;
//
//            if (answer == 0 && x/5 < -5) {
//                hozon();
//                make();
//
//
//            } else if (answer == 1 && x/5 > 5) {
//                hozon();
//                make();
//
//            } else if (answer == 2 && y/5 < -5) {
//                hozon();
//                make();
//            } else if (answer == 3 && y/5 > 5) {
//                hozon();
//                make();
//
//            } else {
//                check();
//            }
//        }
//

//   }

    public void hozon() {
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
//        int Level = data.getInt("LevelSave", 0);
        all = scoreNumber + Level;
        Log.d("kamui2", String.valueOf(data));
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("LevelSave", all);
        editor.apply();
    }


    @Override
    public void run() {


    }


    protected void onResume() {
        super.onResume();
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors =
                sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (0 < sensors.size()) {
            sm.registerListener(this, sensors.get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeCallbacks(this);
        hozon();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        gx = event.values[0];
        gy = event.values[1];
        gz = event.values[2];


        checkAnswer();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

