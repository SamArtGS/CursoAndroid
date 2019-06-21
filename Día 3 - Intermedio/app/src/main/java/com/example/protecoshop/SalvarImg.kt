package com.example.protecoshop

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class SalvarImg(c : Context, imagen : Bitmap){
    var imagenS = imagen
    val contexto = c
    fun salvar(nombre : String, folder : String){
        val file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + folder
        val CurrentDateAndTime = getCurrentDateAndTime()
        val dir = File(file_path)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val file = File(dir, nombre + CurrentDateAndTime + ".jpg")
        try {
            val fOut = FileOutputStream(file)
            imagenS.compress(Bitmap.CompressFormat.JPEG, 85, fOut)
            fOut.flush()
            fOut.close()
            MakeSureFileWasCreatedThenMakeAvabile(file)
            Toast.makeText(contexto, "Se guardó la imagen", Toast.LENGTH_SHORT).show()
        } catch (e: FileNotFoundException) {
            Toast.makeText(contexto, "No se pudo guardar la imagen", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(contexto, "No se pudo guardar la imagen", Toast.LENGTH_SHORT).show()
        }


    }

    private fun MakeSureFileWasCreatedThenMakeAvabile(file: File) {
        MediaScannerConnection.scanFile(contexto,
            arrayOf(file.toString()), null,
            object : MediaScannerConnection.OnScanCompletedListener {
                override fun onScanCompleted(path: String, uri: Uri) {}
            })
    }

    fun getCurrentDateAndTime(): String {
        val c = Calendar.getInstance()
        val df = SimpleDateFormat("yyyy-MM-dd-HH-mm-­ss")
        return df.format(c.getTime())
    }


//convertir imagen


}