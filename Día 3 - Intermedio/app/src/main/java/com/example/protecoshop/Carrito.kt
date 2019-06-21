package com.example.protecoshop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.example.protecoshop.R
import kotlinx.android.synthetic.main.activity_carrito.*

class Carrito : AppCompatActivity() {
    // ---> Almacenar con Shared Preferences el estado del carrito, es decir al salir no se pierde la información
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        val adaptador = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nombresProductos())
        lista_productos.adapter = adaptador as ListAdapter?
    }

    fun nombresProductos() : ArrayList<String>{
        var nom_productos = arrayListOf<String>()
        for(item in ListaP.productos_comprados){
            nom_productos.add(item.name)
        }
        return nom_productos
    }
}