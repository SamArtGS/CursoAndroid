package com.example.protecoshop

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_password.*

class Password : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        auth = FirebaseAuth.getInstance()
        forgot_password.setOnClickListener() {
            enviar()
        }
    }
    fun enviar(){
        val email=forgot_correo.text.toString()
        if(!TextUtils.isEmpty(email)){
            auth.sendPasswordResetEmail(email).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
startActivity(Intent(this,MainActivity::class.java))
                }else{
                    Toast.makeText(this,"Error al enviar el email",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
