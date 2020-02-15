package com.kamiture.kamui.caldim2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

    }

    fun next(v: View) {
        val intent = Intent(this@Main2Activity, com.kamiture.kamui.caldim2.MainActivity::class.java)
        startActivity(intent)
    }

    fun record(v: View) {
        val intent =
            Intent(this@Main2Activity, com.kamiture.kamui.caldim2.Main3Activity::class.java)
        startActivity(intent)

    }

    fun boss(v: View) {
        val intent =
            Intent(this@Main2Activity, com.kamiture.kamui.caldim2.Main4Activity::class.java)
        startActivity(intent)
    }

}
