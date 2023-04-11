package com.example.typedanceapp.MainScreen.plan.kpop

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.typedanceapp.MainScreen.plan.LessonAdapter
import com.example.typedanceapp.MainScreen.plan.Lessons
import com.example.typedanceapp.MainScreen.plan.hiphop.LessonsHipHopActivity
import com.example.typedanceapp.databinding.ActivityBegHipBinding
import com.example.typedanceapp.databinding.ActivityBegKpopBinding
import com.google.firebase.database.*

class BegKpopActivity : AppCompatActivity() {

    private lateinit var mediaC: MediaController
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<Lessons>
    lateinit var binding: ActivityBegKpopBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBegKpopBinding.inflate(layoutInflater)
        setContentView(binding.root)//подгружаем в активити

        setUpVideoPlayer()

        val layoutManager =
            LinearLayoutManager(this)//установили параметры менеджера компоновки слоев просмотра
//        recyclerView = findViewById(R.id.recViewTwo)//сослались на recycleview
        binding.recViewTwo.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)//проверили, что фиксированный размер верен
//        adapter = NewsAdapter(newsList, this)//создали обьект адаптера и передали спсиок в качестве аргумента
//        recyclerView.adapter = adapter
        list = arrayListOf() //список массивов для новостей
//        newsdetail = arrayListOf()
        getUserData()
    }

    private fun getUserData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("lessons_kpop")
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(lessonsSnapShot in snapshot.children){
                        val lessons_list = lessonsSnapShot.getValue(Lessons::class.java)
                        list.add(lessons_list!!)
                    }
                    val mAdapter = LessonAdapter(list, this@BegKpopActivity)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object: LessonAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@BegKpopActivity, LessonsHipHopActivity::class.java)
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

//        video.setVideoURI(
//            Uri.parse("android.resource://" + packageName + "/" + R.raw.kpop)
//        )
//        video.setVideoURI(
//            Uri.parse("https://firebasestorage.googleapis.com/v0/b/shorts-bb865.appspot.com/o/hip_hop.mp4?alt=media&token=fef2c12e-dfca-4807-abad-ab7a69e35fab")
//        )
        mediaC = MediaController(this)
        binding.videoViewTwo.setMediaController(mediaC)
        mediaC.setAnchorView(binding.videoViewTwo)
//        myVideo.start()
        binding.videoViewTwo.requestFocus()
        binding.videoViewTwo.pause()
        binding.videoViewTwo.setOnCompletionListener {
            Toast.makeText(applicationContext, "Видео загружено", Toast.LENGTH_SHORT).show()
        }
        binding.videoViewTwo.setOnErrorListener{
                np, what, extra -> Toast.makeText(applicationContext, "Ошибка во время загрузки", Toast.LENGTH_SHORT).show()
            false
        }
    }
}