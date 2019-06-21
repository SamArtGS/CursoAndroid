package com.example.protecoshop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import android.app.ProgressDialog
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registro.*


class Registro : AppCompatActivity() {

    lateinit var database: FirebaseDatabase
    lateinit var dbReference: DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var progressbar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        progressbar = ProgressBar(this)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbReference = database.reference.child("User")

        registro_registro.setOnClickListener {
            crearCuenta()
        }
    }



    fun crearCuenta() {
        val username: String = registro_nombre.text.toString()
        val email: String = registro_correo.text.toString()
        val password: String = registro_contra.text.toString()
        //Validación si es nula o vacía

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->

                if (task.isComplete) {
                    var user: FirebaseUser? = auth.currentUser
                    verificarEmail(user)
                    var userBaseD = dbReference.child(user!!.uid)
                    userBaseD.child("NomUser").setValue(username)
                    userBaseD.child("correo").setValue(email)
                    userBaseD.child("contrasena").setValue(password)
                    accion()
                }

            }
        }

        }



    fun accion(){
        startActivity(Intent(this,MainActivity::class.java))
    }
    fun verificarEmail(user:FirebaseUser?){
        user!!.sendEmailVerification().addOnCompleteListener(this){
            task ->

            if(task.isComplete){
                Toast.makeText(this,"Email de verificación enviado",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Error con los datos insertados",Toast.LENGTH_LONG).show()
            }
        }
    }



}
