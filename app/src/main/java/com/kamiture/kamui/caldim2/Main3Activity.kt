package com.kamiture.kamui.caldim2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView

class Main3Activity : AppCompatActivity() {
    private var nextLevel: Int = 0
    private var nowLevel: Int = 0
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView

    //    ListView listView;
    //
    //    static List<String> items = new ArrayList<String>();
    //    static ArrayAdapter<String> adapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)

        Log.d("kamui", data.getInt("LevelSave", 0).toString())
        val Level = data.getInt("LevelSave", 0)

        when {
            Level < 100 -> {
                nextLevel = 100
                nowLevel = 1
            }
            Level < 300 -> {
                nextLevel = 300
                nowLevel = 2
            }
            Level < 500 -> {
                nextLevel = 500
                nowLevel = 3
            }
            Level < 800 -> {

                nextLevel = 800
                nowLevel = 4

            }
            Level < 1100 -> {
                nextLevel = 1100
                nowLevel = 5

            }

            // listView = (ListView)findViewById(R.id.listView);


            //        adapter =
            //                new ArrayAdapter<String>(
            //                        this,
            //                        R.layout.listView,
            //                        R.id.listViewLine,
            //                        items
            //                        );
            //
            //        listView.setAdapter(adapter);


            //        String[] texts = {
            //                "今まで正解した問題", String.valueOf(Level), "次のレベルアップまでに必要な正答数",
            //                String.valueOf(nextLevel), "現在のレベル", String.valueOf(nowLevel)
            //
            //        };
            //
            //        ListView listView = new ListView(this);
            //        setContentView(listView);
            //        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, texts);
            //        listView.setAdapter(arrayAdapter);
        }





        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)
        textView2.text = "今までに正解した問題数\n$Level"
        textView3.text = "次のレベルアップまでに必要な正答数\n$nextLevel"
        textView4.text = "現在のレベル\n$nowLevel"

        // listView = (ListView)findViewById(R.id.listView);


        //        adapter =
        //                new ArrayAdapter<String>(
        //                        this,
        //                        R.layout.listView,
        //                        R.id.listViewLine,
        //                        items
        //                        );
        //
        //        listView.setAdapter(adapter);


        //        String[] texts = {
        //                "今まで正解した問題", String.valueOf(Level), "次のレベルアップまでに必要な正答数",
        //                String.valueOf(nextLevel), "現在のレベル", String.valueOf(nowLevel)
        //
        //        };
        //
        //        ListView listView = new ListView(this);
        //        setContentView(listView);
        //        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, texts);
        //        listView.setAdapter(arrayAdapter);
    }


}
