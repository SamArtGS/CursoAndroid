package com.example.protecoshop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ProductosAdapter(contexto : Context, lista_productos : ArrayList<Producto>) : BaseAdapter() {
    var productos = lista_productos
    var con = contexto
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista = p1
        if(p1 == null) {
            val layoutInflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            vista = layoutInflater.inflate(R.layout.grid_item, p2, false)
        }
        var producto : Producto = getItem(p0)
        val imagen = vista!!.findViewById<ImageView>(R.id.imagen_card)
        val nombre = vista!!.findViewById<TextView>(R.id.nombre_card)
        val precio = vista!!.findViewById<TextView>(R.id.precio_card)
        Glide.with(imagen.context).load(producto.image).into(imagen)
        nombre.text = producto.name
        precio.text = "$"+producto.price.toString()+ "0"
        return vista!!
    }

    override fun getItem(p0: Int): Producto {
        return productos.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return productos.size
    }

}