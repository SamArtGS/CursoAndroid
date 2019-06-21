package com.example.protecoshop

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_principal.*
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_detalles.*
import kotlinx.android.synthetic.main.activity_principal.toolbarm

class Principal : AppCompatActivity() {

    //-------------------------------Se hará uso de Carrousel View (Librería externa)
    //-------------------------------Y Grid View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        setSupportActionBar(toolbarm)
        //------------------------------------------------------------------------------------------Carrusel View
        val array = arrayListOf(R.drawable.carrusel1, R.drawable.carrusel2, R.drawable.carrusel3)
        carrusel.setPageCount(array.size)
        var imageListener: ImageListener = object : ImageListener {
            override fun setImageForPosition(position: Int, imageView: ImageView?) {
                imageView?.setImageResource(array[position])
            }

        }
        carrusel.setImageListener(imageListener);
        //--------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------Grid View
        //----------Recuperar de una base de datos



        var productos = arrayListOf<Producto>(Producto(135.50F ,R.drawable.producto ,"Playera 1", "" ),Producto(200.50F ,R.drawable.playera2,"Playera 2" , ""),
            Producto(300.5F ,R.drawable.playera3,"Playera3" , ""), Producto(200.0F, R.drawable.playera4, "Playera4", ""))

/*
*
* val products = arrayListOf<Producto>()
        val ref = FirebaseDatabase.getInstance().getReference("Productos")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (productSnapshot in dataSnapshot.children) {
                    val product = productSnapshot.getValue(Product::class.java)
                    products.add(product!!)
                }
                System.out.println(products)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })
    }
*
*
* */


        //----------------------------------------
        var adapter = ProductosAdapter(this, productos)
        grid_view.adapter = adapter
        grid_view.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, Detalles::class.java)
            val producto = productos.get(i)
            intent.putExtra("Producto", producto)
            intent.putExtra("Position", i)

            startActivity(intent)
        }
        //--------------------------------------------------------------------------------------------------------
    }

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



}