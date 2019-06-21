package com.example.protecoshop
import java.io.Serializable

class Producto(precio : Float, imagen : Int, nombre : String, detalles: String) : Serializable{
    var price = precio
    var image = imagen
    var name = nombre
    var details = detalles
}