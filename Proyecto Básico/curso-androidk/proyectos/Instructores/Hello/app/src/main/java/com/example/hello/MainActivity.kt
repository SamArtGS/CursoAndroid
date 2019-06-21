package com.example.hello

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.index.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.index)

        val quizzes = ArrayList<Quizz>()

        quizzes.add(Quizz(R.drawable.dogo,"Animals",R.drawable.padlock))
        quizzes.add(Quizz(R.drawable.dogo,"Animals",R.drawable.padlock))
        quizzes.add(Quizz(R.drawable.dogo,"Animals",R.drawable.padlock))

        rv_quizz.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        rv_quizz.adapter = QuizAdapter(quizzes,object : ClickListener{
            override fun onClick(view: View, position: Int) {
                //Toast.makeText(applicationContext,quizzes[position].nombreQuizz,Toast.LENGTH_SHORT).show()
                val intento = Intent(applicationContext,Content::class.java)
                intento.putExtra("quizName",quizzes[position].nombreQuizz)
                startActivity(intento)

            }
        })
    }

    override fun onPause() {
        super.onPause()
        mostrarMensaje("En pausa")
    }

    override fun onResume() {
        super.onResume()
        mostrarMensaje("En Resumen")

    }

    override fun onStop() {
        super.onStop()
        mostrarMensaje("En stop")

    }

    override fun onRestart() {
        super.onRestart()
        mostrarMensaje("En reseteo")

    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarMensaje("Destruyendo...")

    }

    fun mostrarMensaje (mensaje: String) =
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()



}
