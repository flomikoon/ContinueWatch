package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0

    private lateinit var mSettings: SharedPreferences


    private var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSettings=getSharedPreferences("myPref", Context.MODE_PRIVATE)
        backgroundThread.start()
    }

    override fun onResume() {
        super.onResume()
        secondsElapsed=mSettings.getInt("seconds",secondsElapsed);
    }

    override fun onPause() {
        super.onPause()
        val editor = mSettings.edit()
        editor.putInt("seconds",secondsElapsed)
        editor.apply()
    }
}
