package com.example.alfon.guerranaval

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mp:MediaPlayer
    private lateinit var mpBackground:MediaPlayer
    private lateinit var vibratorServ:Vibrator
    private lateinit var handler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        mpBackground=MediaPlayer.create(this,R.raw.backgroundmusic)
        handler=Handler()


        if(!mpBackground.isPlaying)
            handler.postDelayed(Runnable { mpBackground.start() },1000)


        mp=MediaPlayer.create(this,R.raw.clickboton)
        vibratorServ=this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        btnJugar.setOnClickListener {
            mp.start()
            vibratorServ.vibrate(80)
            val i=Intent(this,JugarActivity::class.java)
            startActivity(i)
            mpBackground.stop()

        }

        btnAcerca.setOnClickListener {
            mp.start()
            vibratorServ.vibrate(80)
        }

        btnSalir.setOnClickListener{
            this.finish();
        }

    }
}
