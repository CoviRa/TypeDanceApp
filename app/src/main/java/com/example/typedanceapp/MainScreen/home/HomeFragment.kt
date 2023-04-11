package com.example.typedanceapp.MainScreen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.VideoView
import androidx.viewpager2.widget.ViewPager2
import com.example.typedanceapp.R
import com.example.typedanceapp.databinding.FragmentHomeBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private lateinit var adapter: VideoAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var videoView: VideoView
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
//        requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN) //на весь экран
        val viewPager2 = v.findViewById<ViewPager2>(R.id.viewPager)
        //устанавливаем базу данных Firebase
        val mDataBase = Firebase.database.getReference("videos")
        val options = FirebaseRecyclerOptions.Builder<VideoModel>()
            .setQuery(mDataBase, VideoModel::class.java)
            .build()
        //устанавливаем адаптер
        adapter = VideoAdapter(options)
        viewPager2.adapter = adapter

//        val recyclerView:RecyclerView = v.findViewById(com.example.typedance.R.id.recycler_view)
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        recyclerView.setHasFixedSize(true)//проверили, что фиксированный размер верен
//
//        val videoUrls = arrayListOf(
//            "https://defeez.ru/wp-content/uploads/2022/12/Wednesday-Addams-Dance-Scene.mp4",
//        )
//        adapter = VideoAdapter(videoUrls)
//        recyclerView.adapter = adapter
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val mSearchMenuItem = menu.findItem(R.id.searchV)
        val searchView: SearchView? = mSearchMenuItem.actionView as SearchView?
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}