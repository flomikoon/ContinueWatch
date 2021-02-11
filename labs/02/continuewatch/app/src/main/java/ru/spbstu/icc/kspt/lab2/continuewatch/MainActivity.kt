package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    val TAG = "States"
    private lateinit var mSettings: SharedPreferences

    var stop = true

    private var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            if(stop) {
                textSecondsElapsed.post {
                  textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        backgroundThread.start()
        Log.d(TAG, "MainActivity: onCreate()")
    }

    override fun onResume() {
        super.onResume()
        mSettings=getSharedPreferences("myPref", Context.MODE_PRIVATE)
        secondsElapsed=mSettings.getInt("seconds",0)
        stop = true
        Log.d(TAG, "MainActivity: onResume()")
    }

    override fun onPause() {
        super.onPause()
        stop = false
        val editor = mSettings.edit()
        editor.putInt("seconds",secondsElapsed)
        editor.apply()
        Log.d(TAG, "MainActivity: onPause()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity: onStart()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity: onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity: onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "MainActivity: onRestart()")
    }
}
