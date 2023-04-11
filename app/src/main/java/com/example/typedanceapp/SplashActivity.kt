package com.example.typedanceapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.example.typedanceapp.Login_Register.PreviewScreenActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )//активити на весь экран

        supportActionBar?.hide()//скрываем actionbar
        setContentView(R.layout.activity_splash)
//        val path: String = "android.resource://" + packageName + "/" + R.raw.logo_1080
//        val uri: Uri = Uri.parse(path)
//        val video: VideoView = findViewById(R.id.videoView)
//        video.setVideoURI(uri)
//        video.start()
//        video.setOnCompletionListener(MediaPlayer.OnCompletionListener(){
//            onEnterAnimationComplete()
//        })

        Handler().postDelayed({
            startActivity(Intent(this, PreviewScreenActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 2000)//переход на лог активити спустя 2 секунды
    }

//    private fun makeFullScreen(){
//        supportRequestWindowFeature(FEATURE_NO_TITLE)
//        window.setFlags(
//            FLAG_FULLSCREEN,
//            FLAG_FULLSCREEN
//        )//активити на полный экран
//        supportActionBar?.hide()//скрываем actionbar
//    }
}