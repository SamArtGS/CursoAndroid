package com.example.protecoshop

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var authenticacion:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        crear_cuenta.setOnClickListener {
            linear_login.setBackgroundResource(R.drawable.fondo_dr)
            val anim_bg: AnimationDrawable = linear_login.background as AnimationDrawable
            iniciar_transicion(anim_bg,1)

        }
        login_forgot.setOnClickListener {
            linear_login.setBackgroundResource(R.drawable.fondo_con)
            val anim_bg: AnimationDrawable = linear_login.background as AnimationDrawable
            iniciar_transicion(anim_bg,2)
        }
        login.setOnClickListener {
            authenticacion = FirebaseAuth.getInstance()
            loginUser()
        }
    }

    fun loginUser(){
        val user:String = login_correo.text.toString()
        val contra:String = login_contra.text.toString()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(contra)){
            progressBar.visibility = View.VISIBLE
            authenticacion.signInWithEmailAndPassword(user,contra).addOnCompleteListener(this) { 
                task ->
                if(task.isSuccessful){
                    val intent = Intent(this, Principal::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Nombre de usuario o contraseña incorrecto",Toast.LENGTH_LONG   ).show()
                }
            }
        }
    }



    private fun iniciar_transicion(animacion : AnimationDrawable, caso : Int){
        login.isEnabled = false
        crear_cuenta.isEnabled = false
        login_forgot.isEnabled = false
        animacion.setEnterFadeDuration(3000)
        animacion.setExitFadeDuration(3000)
        animacion.start()

        val manejador = Handler()
        manejador.postDelayed({
            login.isEnabled = true
            crear_cuenta.isEnabled = true
            login_forgot.isEnabled = true
            animacion.stop()
            if(caso == 1){
                val intent = Intent(this, Registro::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, Password::class.java)
                startActivity(intent)
            }
        }, 3700)
    }

}
