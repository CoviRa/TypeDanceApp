package com.example.typedanceapp.MainScreen.plan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.typedanceapp.R

class LessonAdapter(private val lessonList: ArrayList<Lessons>, private val context: Context): RecyclerView.Adapter<LessonAdapter.LessonViewHolder>()  {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener: AdapterView.OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    inner class LessonViewHolder(v: View, listener: OnItemClickListener): RecyclerView.ViewHolder(v){
        var text: TextView = v.findViewById(R.id.text)

        init {
            v.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lessons_hip_hop, parent, false)
        return LessonViewHolder(v, mListener)
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val currentLesson = lessonList[position]

        holder.text.text = currentLesson.text
    }
}