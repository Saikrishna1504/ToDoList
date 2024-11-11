package com.saikrishna.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {

    val FILENAME = "listinfo.dat"

    fun writeData(item: ArrayList<String>, context: Context) {
        try {
            val fos: FileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
            val oas = ObjectOutputStream(fos)
            oas.writeObject(item)
            oas.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readData(context: Context): ArrayList<String> {
        val itemlist = ArrayList<String>()
        try {
            val fis: FileInputStream = context.openFileInput(FILENAME)
            val ois = ObjectInputStream(fis)
            itemlist.addAll(ois.readObject() as ArrayList<String>)
            ois.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return itemlist
    }
}
