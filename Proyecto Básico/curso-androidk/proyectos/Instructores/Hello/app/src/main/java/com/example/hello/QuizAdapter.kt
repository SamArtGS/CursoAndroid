package com.example.hello

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.quizitem.view.*
import java.text.FieldPosition

interface ClickListener{
    fun onClick(view:View,position: Int)
}


class QuizAdapter(val quizList: ArrayList<Quizz>,var listener: ClickListener): RecyclerView.Adapter<QuizAdapter.ViewHolder>(){

    var viewHolder: ViewHolder? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val vista = LayoutInflater.from(p0.context).inflate(R.layout.quizitem,p0,false)
        viewHolder = ViewHolder(vista,listener)

        return viewHolder as ViewHolder
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = quizList[p1]
        p0.quizImage?.setImageResource(item.imgQuiz)
        p0.quizTitle?.text = item.nombreQuizz
        p0.lockImage?.setImageResource(item.lock)
    }


    class ViewHolder(itemView: View,listener: ClickListener) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        override fun onClick(v: View?) {
            if (v != null) {
                mylistener.onClick(v,adapterPosition)
            }
        }

        val vista = itemView
        val mylistener = listener

        var quizImage : ImageView? = null
        var quizTitle: TextView? = null
        var lockImage: ImageView? = null

        init {
            quizImage = itemView.iv_animal
            quizTitle = itemView.tv_titulo
            lockImage = itemView.iv_lock

            vista.setOnClickListener(this)

        }
    }
}