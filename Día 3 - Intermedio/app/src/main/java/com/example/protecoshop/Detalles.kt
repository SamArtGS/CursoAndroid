package com.example.protecoshop

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalles.*

class Detalles : AppCompatActivity() {
    lateinit var producto_selec: Producto
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        //---------------------------------------Recuperar datos del intent y mostrarlos en la vista
        val producto = this.intent.extras.getSerializable("Producto") as Producto
        producto_selec = producto
        nombre_detalles.text = producto.name
        try {
            Glide.with(applicationContext).load(producto.image).into(imagen_detalles)
        }catch (e : Exception){
            Log.e("Excepcion", e.printStackTrace().toString())
        }
        precio_detalles.text = "$"+ producto.price.toString() + "0"
        //var arreglo = arrayListOf<Producto>()
        //-------------------------------------------------------------------------------------
        //------------------------------------------------------Toolbar
        setSupportActionBar(toolbar)
        registerForContextMenu(imagen_detalles)
        boton_carrito.setOnClickListener {
            ListaP.productos_comprados.add(producto)
            Toast.makeText(this,"Se ha agregado el producto al carrito", Toast.LENGTH_SHORT).show()
        }


    }
    //------------------------------------------------------Icono del Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        /* Mostrar los productos que hemos agregado*
           ---> Ir a una nueva actividad
         */
        val intent = Intent(this, Carrito::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    //-----------------------------------------------------Menú de la imagen
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        val inflater = menuInflater
        inflater.inflate(R.menu.opciones_img,menu)
        super.onCreateContextMenu(menu, v, menuInfo)

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        //Realiza algo
        imagen_detalles.buildDrawingCache()
        var bitmap = imagen_detalles.drawingCache

        val permissionCheck = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso.")
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 225)
        } else {
            Log.i("Mensaje", "Se tiene permiso!")
            val salvar = SalvarImg(this, bitmap)
            salvar.salvar(producto_selec.name, "/imagenejem")
        }

        return super.onContextItemSelected(item)
    }






}