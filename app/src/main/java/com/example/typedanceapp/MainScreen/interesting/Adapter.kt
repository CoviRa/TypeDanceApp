package com.example.typedanceapp.MainScreen.interesting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.typedanceapp.R
import com.google.android.material.imageview.ShapeableImageView

class Adapter(private val newsList: ArrayList<News>): RecyclerView.Adapter<Adapter.NewsViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
//    var onItemClick: ((News) -> Unit)? = null
//    var listener:ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NewsViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        //количество элементов в списке новостей
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position] //получаем текущий элемент списка
        holder.titleImage.setImageResource(news.image)//устанавливаем изображение
        holder.tvHeading.text = news.name// и текст для этого элемента
    }

    class NewsViewHolder(v: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(v) {
        //наш держатель представления
        val titleImage: ShapeableImageView = v.findViewById(R.id.title_image)
        val tvHeading: TextView = v.findViewById(R.id.tvHeading)

        init {
            v.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
//        holder.itemView.setOnClickListener{
//            onItemClick?.invoke(news)//не будет принимать нулевое значение
//            fun OnClick(v: View){
//                val news: News = newsList[position]
//            }
//            val intent = Intent(context, NewsActivity::class.java)
//            context.startActivity(intent)
//            list?.clickItemPosition(news,position)

//
//    interface ItemClick {
//        fun clickItemPosition(news: News, position: Int) = Unit
//    }