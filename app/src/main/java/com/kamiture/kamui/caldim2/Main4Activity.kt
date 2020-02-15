package com.kamiture.kamui.caldim2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast

import com.kamiture.kamui.caldim2.R.drawable.hebi
import com.kamiture.kamui.caldim2.R.drawable.raion
import com.kamiture.kamui.caldim2.R.drawable.tori

class Main4Activity : AppCompatActivity() {
    private var Level: Int = 0
    private var check: Int = 0
    private val STAGE_1_CLEAR = 1
    private val STAGE_2_CLEAR = 2
    private val STAGE_3_CLEAR = 3
    private val STAGE_4_CLEAR = 4
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        imageView = findViewById<View>(R.id.boss) as ImageView
        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)
        Level = data.getInt("LevelSave", 0)
        check = data.getInt("imagecount", 0)
        if (check == STAGE_1_CLEAR) {
            imageView.setImageResource(hebi)
        } else if (check == STAGE_2_CLEAR) {
            imageView.setImageResource(tori)
        } else if (check == STAGE_3_CLEAR) {
            imageView.setImageResource(raion)
        }
    }


    fun charenge(v: View) {

        if (Level < 100) {
            val toast =
                Toast.makeText(this@Main4Activity, "まだレベルが足りないよ！もっと頑張ろう！", Toast.LENGTH_SHORT)
            toast.show()
        } else if (Level < 300) {
            if (check == STAGE_1_CLEAR) {
                Toast.makeText(this@Main4Activity, "まだレベルが足りないよ！もっと頑張ろう！", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val toast =
                    Toast.makeText(this@Main4Activity, "やった！ボスを倒したぞ。次のボスを倒そう！", Toast.LENGTH_SHORT)
                toast.show()
                imageView.setImageResource(hebi)
                check = STAGE_1_CLEAR

            }

        } else if (Level < 500) {
            if (check == STAGE_2_CLEAR) {
                Toast.makeText(this@Main4Activity, "まだレベルが足りないよ！もっと頑張ろう！", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val toast =
                    Toast.makeText(this@Main4Activity, "やった！ボスを倒したぞ。次のボスを倒そう！", Toast.LENGTH_SHORT)
                toast.show()
                imageView.setImageResource(tori)
                check = STAGE_2_CLEAR

            }

        } else if (Level < 800) {
            if (check == STAGE_3_CLEAR) {
                Toast.makeText(this@Main4Activity, "まだレベルが足りないよ！もっと頑張ろう！", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val toast =
                    Toast.makeText(this@Main4Activity, "やった！ボスを倒したぞ。次のボスを倒そう！", Toast.LENGTH_SHORT)
                toast.show()
                imageView.setImageResource(raion)
                check = STAGE_3_CLEAR

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (check == STAGE_1_CLEAR) {
            val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)
            val editor = data.edit()
            editor.putInt("bossimage", raion)
            editor.putInt("imagecount", check)
            editor.apply()
        }
    }
}
