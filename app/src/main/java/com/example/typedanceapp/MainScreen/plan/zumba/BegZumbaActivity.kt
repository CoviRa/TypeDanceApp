package com.example.typedanceapp.MainScreen.plan.zumba

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.example.typedanceapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.typedanceapp.MainScreen.plan.LessonAdapter
import com.example.typedanceapp.MainScreen.plan.Lessons
import com.example.typedanceapp.MainScreen.plan.hiphop.LessonsHipHopActivity
import com.example.typedanceapp.databinding.ActivityBegHipBinding
import com.example.typedanceapp.databinding.ActivityBegZumbaBinding
import com.google.firebase.database.*

class BegZumbaActivity : AppCompatActivity() {

    private lateinit var mediaC: MediaController
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<Lessons>
    lateinit var binding: ActivityBegZumbaBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBegZumbaBinding.inflate(layoutInflater)
        setContentView(binding.root)//подгружаем в активити

//        myVideo = findViewById(R.id.videoViewOne)
        setUpVideoPlayer()

        val layoutManager =
            LinearLayoutManager(this)//установили параметры менеджера компоновки слоев просмотра
//        recyclerView = findViewById(R.id.recView)//сослались на recycleview
        binding.recView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)//проверили, что фиксированный размер верен
//        adapter = NewsAdapter(newsList, this)//создали обьект адаптера и передали спсиок в качестве аргумента
//        recyclerView.adapter = adapter
        list = arrayListOf() //список массивов для новостей
//        newsdetail = arrayListOf()
        getUserData()
    }

    private fun getUserData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("lessons_zumba")
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(lessonsSnapShot in snapshot.children){
                        val lessons_list = lessonsSnapShot.getValue(Lessons::class.java)
                        list.add(lessons_list!!)
                    }
                    val mAdapter = LessonAdapter(list, this@BegZumbaActivity)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object: LessonAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@BegZumbaActivity, LessonsHipHopActivity::class.java)
                            intent.putExtra("text", list[position].text)//ключ text
                            intent.putExtra("url", list[position].url)
                            startActivity(intent)
                        }

                        override fun onItemClick(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                        }

                    })
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpVideoPlayer() {

//        myVideo.setVideoURI(
//            Uri.parse("android.resource://" + packageName + "/" + R.raw.zumba)
//        )
//        myVideo.setVideoURI(
//            Uri.parse("https://firebasestorage.googleapis.com/v0/b/shorts-bb865.appspot.com/o/hip_hop.mp4?alt=media&token=fef2c12e-dfca-4807-abad-ab7a69e35fab")
//        )
        mediaC = MediaController(this)
        binding.videoViewOne.setMediaController(mediaC)
        mediaC.setAnchorView(binding.videoViewOne)
//        myVideo.start()
        binding.videoViewOne.requestFocus()
        binding.videoViewOne.pause()
        binding.videoViewOne.setOnCompletionListener {
            Toast.makeText(applicationContext, "Видео загружено", Toast.LENGTH_SHORT).show()
        }
        binding.videoViewOne.setOnErrorListener{
                np, what, extra -> Toast.makeText(applicationContext, "Ошибка во время загрузки", Toast.LENGTH_SHORT).show()
            false
        }
    }
}