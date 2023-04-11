package com.example.typedanceapp.MainScreen.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.typedanceapp.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class VideoAdapter(options: FirebaseRecyclerOptions<VideoModel?>): FirebaseRecyclerAdapter<VideoModel?, VideoAdapter.VideoViewHolder>(options)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.single_video,
            parent,
            false
        )
        return VideoViewHolder(v)
    }


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int, model: VideoModel) {
        holder.setData(model)
    }

    inner class VideoViewHolder(v: View): RecyclerView.ViewHolder(v){
        var videoView: VideoView
        var title: TextView
        var desc: TextView
        var pbar: ProgressBar
        //        var fav: ImageView
        var isFav = false
        fun setData(obj: VideoModel){

            videoView.setVideoPath(obj.url)
            title.text = obj.title
            desc.text = obj.desc
            videoView.setOnPreparedListener{
                    mediaPlayer ->
                pbar.visibility = View.GONE
                mediaPlayer.start()
            }
            videoView.setOnCompletionListener {
                    mediaPlayer -> mediaPlayer.start()
            }
//            fav.setOnClickListener {
//                if(!isFav){
//                    fav.setImageResource(R.drawable.baseline_favorite)
//                    isFav = true
//                }
//                else{
//                    fav.setImageResource(R.drawable.baseline_favorite_border)
//                    isFav = true
//                }
//
//            }
        }
        init{
            videoView = v.findViewById(R.id.videoView)
            title = v.findViewById(R.id.textVideoName)
            desc = v.findViewById(R.id.textVideoDesc)
            pbar = v.findViewById(R.id.progressBar)
//            fav = v.findViewById(R.id.like)
        }
    }
}