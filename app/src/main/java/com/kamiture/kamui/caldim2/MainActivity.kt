package com.kamiture.kamui.caldim2

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : Activity(), Runnable, SensorEventListener {
    private var randomQuestion = Random()
    private lateinit var sm: SensorManager
    private var gx: Float = 0.toFloat()
    private var gy: Float = 0.toFloat()
    private var gz: Float = 0.toFloat()
    private lateinit var h: Handler
    private var questionNumber1: Int = 0
    private var questionNumber2: Int = 0
    private var correctAnswer: Int = 0
    private var scoreNumber: Int = 0
    private var recordNumber: Int = 0
    private var x = 0f
    private var y = 0f
    private var z = 0f
    private var answerDirection: Int = 0
    private var all: Int = 0
    private var Level: Int = 0
    private var answerText_UP: TextView? = null
    private var answerText_LEFT: TextView? = null
    private var answerText_RIGHT: TextView? = null
    private var answerText_DOWN: TextView? = null
    private var questionText1: TextView? = null
    private var questionText2: TextView? = null
    private var Sign: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)
        Level = data.getInt("LevelSave", 0)


        answerText_UP = findViewById(R.id.anser1)
        answerText_LEFT = findViewById(R.id.answer2)
        answerText_RIGHT = findViewById(R.id.answer3)
        answerText_DOWN = findViewById(R.id.answer4)
        questionText1 = findViewById(R.id.count1)
        questionText2 = findViewById(R.id.count2)
        Sign = findViewById(R.id.sign)
        h = Handler()
        h.postDelayed(this, 500)


        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER)
        if (0 < sensors.size) {
            sm.registerListener(
                this, sensors[0],
                SensorManager.SENSOR_DELAY_NORMAL
            )

            //        makeQuestions();
            //        startQuestions();
        }
        make()
    }


    private fun make() {
        val firstQuestion = randomQuestion.nextInt(9)
        val secondQuestion = randomQuestion.nextInt(9)
        val sign = randomQuestion.nextInt(3)
        answerDirection = randomQuestion.nextInt(4)

        questionNumber1 = firstQuestion
        questionText1!!.text = questionNumber1.toString()

        questionNumber2 = secondQuestion
        questionText2!!.text = questionNumber2.toString()


        if (sign == 0) {
            this.Sign!!.text = "+"
            plus()
        } else if (sign == 1) {
            this.Sign!!.text = "-"
            minus()
        } else if (sign == 2) {
            this.Sign!!.text = "×"
            kakeru()
        }
        if (answerDirection == DIRECTION_UP) {
            answerText_UP!!.text = correctAnswer.toString()
            answerText_LEFT!!.text = (correctAnswer - 1).toString()
            answerText_RIGHT!!.text = (correctAnswer + 1).toString()
            answerText_DOWN!!.text = (correctAnswer + 10).toString()

        } else if (answerDirection == DIRECTION_LEFT) {
            answerText_UP!!.text = (correctAnswer + 1).toString()
            answerText_LEFT!!.text = correctAnswer.toString()
            answerText_RIGHT!!.text = (correctAnswer + 10).toString()
            answerText_DOWN!!.text = (correctAnswer - 1).toString()
        } else if (answerDirection == DIRECTION_RIGHT) {
            answerText_UP!!.text = (correctAnswer - 1).toString()
            answerText_LEFT!!.text = (correctAnswer + 10).toString()
            answerText_RIGHT!!.text = correctAnswer.toString()
            answerText_DOWN!!.text = (correctAnswer + 1).toString()
        } else {
            answerText_UP!!.text = (correctAnswer + 10).toString()
            answerText_LEFT!!.text = (correctAnswer + 1).toString()
            answerText_RIGHT!!.text = (correctAnswer - 1).toString()
            answerText_DOWN!!.text = correctAnswer.toString()
        }

        //        doingAnswer();

    }

    private fun plus() {
        correctAnswer = questionNumber1 + questionNumber2

    }


    private fun minus() {
        correctAnswer = questionNumber1 - questionNumber2


    }

    private fun kakeru() {
        correctAnswer = questionNumber1 * questionNumber2

    }


    private fun checkDirection(): Int {
        recordNumber++
        x = x + gx
        y = y + gy
        z = z + gz
        //        Log.d("tags",String.valueOf(x));
        //        Log.d("tags",String.valueOf(y));


        if (recordNumber == 5) {

            recordNumber = 0
            //            if (y / 5 < -5) {
            if (y / 5 < -5) {
                x = 0f
                y = 0f
                z = 0f
                return DIRECTION_UP


                //                number6 = 1;
                //                checkAnswer();
                //            } else if (y / 5 > 5) {
            } else if (y / 5 > 5) {
                x = 0f
                y = 0f
                z = 0f
                return DIRECTION_DOWN

                //                number6 = 2;
                //                checkAnswer();
                //            } else if (x / 5 > 5) {
            } else if (x / 5 > 5) {
                x = 0f
                y = 0f
                z = 0f
                return DIRECTION_LEFT

                //                number6 = 3;
                //                checkAnswer();
                //            } else if (x / 5 < -5) {
            } else if (x / 5 < -5) {
                x = 0f
                y = 0f
                z = 0f
                return DIRECTION_RIGHT

                //                number6 = 4;
                //                checkAnswer();
            }
        }
        return DIRECTION_NONE
        //                number6 = 0;


    }


    private fun checkAnswer() {
        val direction = checkDirection()
        all = scoreNumber + Level
        //      Log.d("DIRECTION", String.valueOf(direction));

        if (answerDirection == direction) {
            //            正解のとき
            scoreNumber++

            when (all) {
                100 -> {
                    val toast = Toast.makeText(
                            this@MainActivity,
                            "レベルが上がったよ！スタート画面からボスに挑戦だ",
                            Toast.LENGTH_SHORT
                    )
                    toast.show()
                    make()
                }
                300 -> {
                    val toast = Toast.makeText(
                            this@MainActivity,
                            "レベルが上がったよ！スタート画面からボスに挑戦だ",
                            Toast.LENGTH_SHORT
                    )
                    toast.show()
                    make()
                }
                500 -> {
                    val toast = Toast.makeText(
                            this@MainActivity,
                            "レベルが上がったよ！スタート画面からボスに挑戦だ",
                            Toast.LENGTH_SHORT
                    )
                    toast.show()
                    make()
                }
                800 -> {
                    val toast = Toast.makeText(
                            this@MainActivity,
                            "レベルが上がったよ！スタート画面からボスに挑戦だ",
                            Toast.LENGTH_SHORT
                    )
                    toast.show()
                    make()
                }
                else -> {
                    make()
                }
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

    fun hozon() {
        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)
        //        int Level = data.getInt("LevelSave", 0);
        all = scoreNumber + Level
        Log.d("kamui2", data.toString())
        val editor = data.edit()
        editor.putInt("LevelSave", all)
        editor.apply()
    }


    override fun run() {


    }


    override fun onResume() {
        super.onResume()
        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER)
        if (0 < sensors.size) {
            sm.registerListener(
                this, sensors[0],
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onPause() {
        super.onPause()
        sm.unregisterListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        h.removeCallbacks(this)
        hozon()
    }

    override fun onSensorChanged(event: SensorEvent) {

        gx = event.values[0]
        gy = event.values[1]
        gz = event.values[2]


        checkAnswer()

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }

    companion object {

        private const val DIRECTION_UP = 0
        private const val DIRECTION_DOWN = 1
        private const val DIRECTION_RIGHT = 2
        private const val DIRECTION_LEFT = 3
        private const val DIRECTION_NONE = 4
    }
}

