fun main(args: Array<String>) {
   //¿Mutable o inmutable?
   
    var x = 5
    val y:Int = 10
    
    x = x+1
    //y = y+1
    
    println(x)
    println(y)
    
    //Tipos de variables
    
   var a: Int = 10000
   var c: Int = 100 
   val d: Double = 100.00
   var f: Float = 100.00f
   val l: Long = 1000000004
   var s: Short = 10
   val b: Byte = 1
   
   println("El valor de tu entero es: "+a);
   println("El valor de tu Double es: "+d);
   println("El valor de tu float es: "+f);
   println("El valor de tu Long es: "+l);
   println("El valor de tu Short es: "+s);
   println("El valor de tu byte es: "+b);
    
    
   var Hola:String = "Hola, ¿Como estas?"
    
    println(Hola)
   
   //Arreglos
   
   

    //Estructuras de control
    a = 10
    c = 20
    var max = 0
    if (a > c) {
      max = a
  	 } else {
      max = c
   }
   println("El numero más grande es: " +max)
    //
    //Le decimos hola a When X Switch X
  	val numero:Int = 7
   	when (numero) {
      1 -> print(":)")
      2 -> print(":(")
      3 -> print(":O")
      4, 5 -> print(":P")
      in 6..10 -> "Número entre 6 a 10"
      else -> { 
         println("No se encuentra el número dentro de las opciones")
      }
   }
	var elementoCualquiera:Any = ""
        //Forma inteligente de casteo
    when (elementoCualquiera) {
    	is Int -> println(elementoCualquiera + 1)
    	is String -> println(elementoCualquiera.length + 1)
   		is IntArray -> println(elementoCualquiera.sum())
    }
    
   	// CICLOS
   	var arreglo: IntArray = intArrayOf(10, 20, 30, 40, 50)
   	for (i in arreglo) println("Número "+i)
    
    var items = listOf(1, 22, 83, 4)
   
   for ((index, value) in items.withIndex()) {
      println("El elemento en la posición $index es:  $value")
   }
   
   var dia= 1
	println("Empiza la semana")
	while(dia < 6) {
    	if  (dia == 1) {
       		 println("$dia dia trabajando")
    	} else {
        println("$dia dias trabajando")
    	}
    dia++ // Actualizamos la condicion
}
println("A descansar")

var numeroMagico:Int = 2
do {
    numeroMagico = numeroMagico + 1
} while(numeroMagico in 1..100) // numero < 1 || numero > 100
println("Gracias " + numeroMagico)


//Si quiero salir de un ciclo o saltar un paso
for (num in 1..10) {
    if (num % 2 == 0) {
        continue
    }
    print("$num ")
}

for (num in 1..10) {
    if (num % 5 == 0) {
        break
    }
    print("$num ")
}
println()

//Funciones

var sumaTotal = suma(10,20)
println(sumaTotal)


//Manejo de errores y excepciones
   division(4,0)
   val persona: Persona
   persona = Persona("Sam",20)
   
   persona.imprimir()
   println(persona.nombre)
   
   var perrito = Animal("Amarok","Sam",4)
   perrito.decirDuenio()
   
  //dataclass
val juan:Niño = Niño("Juan", "El #1", 10)
val Carlos:Niño = juan.copy(nombre="Carlos")

	println(juan)
    println(Carlos)
   //Interfaces en Kotlin
   //
   
   var examen:C
   examen = C()
   examen.bar()
   
   
   //NULL SAFETY OPERATION
   //
   //RECUERDAN EN JAVA EL FAMOSO NULL POINTER EXCEPTION
   //
   	var prueba: String? = "abc"
	prueba = null // ok
	println(prueba)
   
   	val posibleString: String? = "Kotlin"
	if (posibleString != null && posibleString.length > 0) {
   		 println("String de longitud ${posibleString.length}")
	} else {
    	print("String vacío")
	}
    
    val valor: String? = null
	println(valor?.length)
    
    
    
    //¿se puede usar en listas?
    
    val listaConNulos: List<String?> = listOf("Kotlin", null)
	for (item in listaConNulos) {
    	item?.let { 
            println(item) 
        }
        //let solo toma en cuenta los valores no nulos
	}
    
    //DESTAPANDO OPCIONALES
    //Para destapar opcionales ya que queramos su valor explícito haremos:
    var numerito:Int? = 10
    print(numerito!!)
    
    //Operador Elvis
    //
    //
    var palabraPrueba:String? = "Curso Kotlin"
    palabraPrueba = null
    
    val operador: Int = if (palabraPrueba != null) palabraPrueba.length else 0
    val operador2 = palabraPrueba?.length ?: 0
    
    
    //Concatenación de manera segura
    val aInt: Int? = a as? Int
}

//
//Una data class no es más que una clase que sólo contiene estado y no realiza ninguna operación.
//
data class Niño(var nombre: String, var apodo: String, var edad: Int)

//retorno de valores
fun suma(a: Int, b: Int): Int {
    return a + b
}

//Diferencia entre init y constructor
class Constructores {
    init {
        println("Bloque inicializador")
    }

    constructor() {
        println("Constructor")
    }
}
//Programación Orientada a Objetos
class Persona {
    var nombre: String = ""
    var edad: Int = 0

    fun inicializar(nombre: String, edad: Int) {
        this.nombre = nombre
        this.edad = edad
    }
    constructor() { 
    }
	constructor(nombre: String, edad: Int) {
      this.nombre = nombre
      this.edad = edad  
    } 
    fun imprimir() {
        println("Nombre: $nombre y tiene una edad de $edad")
    }

    fun esMayorEdad() {
        if (edad >= 18)
            println("Es mayor de edad $nombre")
        else
            println("No es mayor de edad $nombre")
    }
}

class Animal(val nombre: String, val dueno: String, var edad: Int) { 
	fun decirDuenio() {
        println("Hola, soy $nombre y mi dueño es $dueno")
    }
	fun decirEdad() {
        println("Hola, soy $nombre y tengo $edad años")
    }
}


fun division(numero1: Int, numero2:Int) {
    try {
        println(numero1/numero2)
    }catch(exception:ArithmeticException){
        println("Hay una excepción aritmética")   
    }
}



interface A {
    fun foo() { println("A") }
    fun bar() { println("D")}
}

interface B {
    fun foo() { println("B") }
    fun bar() { println("C") }
}

class C : A {
    /*override fun bar() { 
        println("bar") 
    }*/
}