package com.example.typedanceapp.MainScreen.plan.kpop

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.typedanceapp.R
import androidx.core.content.ContextCompat
import com.example.typedanceapp.MainScreen.plan.Lessons
import com.example.typedanceapp.databinding.ActivityLessonsKpopBinding
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultAllocator

class LessonsKpopActivity : AppCompatActivity() {

    var isFullScreen = false
    var isLock = false

    private lateinit var binding: ActivityLessonsKpopBinding
    private lateinit var list: ArrayList<Lessons>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonsKpopBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val playerView: PlayerView= findViewById(R.id.player)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        val bt_lockscreen: ImageView = findViewById(R.id.exo_lock)
        val bt_fullscreen: ImageView = findViewById(R.id.bt_fullscreen)
        val videolink = intent.getStringExtra("url")

        val allocator = DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE)

        val loadControl = DefaultLoadControl.Builder()
            .setAllocator(allocator)
            .setTargetBufferBytes(C.LENGTH_UNSET)
            .setBufferDurationsMs(10000, 120000, 1000, 1000)
            .setPrioritizeTimeOverSizeThresholds(true)
            .build()

        val exoPlayer = ExoPlayer.Builder(this)
            .setLoadControl(loadControl)
            .setHandleAudioBecomingNoisy(true)
            .setSeekBackIncrementMs(5000)
            .setSeekForwardIncrementMs(5000)
            .build()
        playerView.player = exoPlayer
        playerView.keepScreenOn = true
        exoPlayer.addListener(object: Player.Listener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if(playbackState == Player.STATE_BUFFERING){
                    progressBar.visibility = View.VISIBLE
                }
                else if(playbackState == Player.STATE_READY){
                    progressBar.visibility = View.GONE
                }
            }
        })
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        val videoSource = Uri.parse("https://defeez.ru/wp-content/uploads/2022/12/Wednesday-Addams-Dance-Scene.mp4")
        val mediaItem = MediaItem.fromUri(videolink!!)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = false
        bt_fullscreen.setOnClickListener{//метод отвечать за раскрытие и сворачивание экрана

            if(!isFullScreen){
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.baseline_fullscreen_exit))
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            }
            else{
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.baseline_fullscreen))
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
            isFullScreen = !isFullScreen
        }

        bt_lockscreen.setOnClickListener{//метод отвечает за открытие и закрытие замка в видео
            if(!isLock){
                bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.baseline_lock))
            }
            else{
                bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.baseline_lock_open))
            }
            isLock = !isLock
            lockscreen(isLock)
        }
    }
    private fun lockscreen(lock: Boolean) {
        val sec_mid: LinearLayout = findViewById(R.id.sec_controlid)
        val sec_mid2: LinearLayout = findViewById(R.id.sec_controlid2)
        if(lock){
            sec_mid.visibility = View.INVISIBLE
            sec_mid2.visibility = View.INVISIBLE
        }
        else{
            sec_mid.visibility = View.VISIBLE
            sec_mid2.visibility = View.VISIBLE
        }
    }

}