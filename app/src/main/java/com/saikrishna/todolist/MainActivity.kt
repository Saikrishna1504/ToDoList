package com.saikrishna.todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var item: EditText
    lateinit var add: Button
    lateinit var listView: ListView

    var itemList = ArrayList<String>()
    var fileHelper = FileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        listView = findViewById(R.id.list)

        itemList = fileHelper.readData(this)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList)

        listView.adapter = arrayAdapter

        add.setOnClickListener {
            val itemName: String = item.text.toString().trim()
            if (itemName.isNotEmpty()) {
                itemList.add(itemName)
                item.setText("")
                fileHelper.writeData(itemList, applicationContext)
                arrayAdapter.notifyDataSetChanged()
            }
        }

        // Use setOnItemClickListener to handle item clicks in the ListView
        listView.setOnItemClickListener { parent, view, position, id ->
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this item from the list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            alert.setPositiveButton("Yes") { dialogInterface, _ ->
                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                fileHelper.writeData(itemList, applicationContext)
            }

            alert.create().show()
        }
    }
}
