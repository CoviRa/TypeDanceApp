package com.example.typedanceapp.MainScreen.interesting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.typedanceapp.R

class NewsAdapter(private val newsList: ArrayList<Container>, private val context: InterestingFragment): RecyclerView.Adapter<NewsAdapter.NewViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener: AdapterView.OnItemClickListener {
        fun onItemClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NewViewHolder(v, mListener)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        val currentNews = newsList[position]

        holder.tvHeading.text = currentNews.title.toString()
        Glide.with(context).load(newsList[position].image).into(holder.titleImage)
    }

    inner class NewViewHolder(v: View, listener: OnItemClickListener): RecyclerView.ViewHolder(v){
        var titleImage: ImageView = v.findViewById(R.id.title_image)
        var tvHeading: TextView = v.findViewById(R.id.tvHeading)

        init {
            v.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}