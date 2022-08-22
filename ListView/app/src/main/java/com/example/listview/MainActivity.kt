package com.example.listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.linedish.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var arrayDish : ArrayList<Dish> = ArrayList()

        arrayDish.add(Dish("Hambuger 1", R.drawable.hambuger1, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Hambuger 2", R.drawable.hambuger2, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 1", R.drawable.garan1, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 2", R.drawable.garan2, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 3", R.drawable.garan3, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 4", R.drawable.garan4, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 3", R.drawable.garan3, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 4", R.drawable.garan4, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 3", R.drawable.garan3, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 4", R.drawable.garan4, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 3", R.drawable.garan3, CheckBox(this@MainActivity), 0))
        arrayDish.add(Dish("Gà rán 4", R.drawable.garan4, CheckBox(this@MainActivity), 0))

        var adapter : ListViewAdapter = ListViewAdapter(this@MainActivity, arrayDish)
        listview.adapter = adapter


        var check : Boolean = false
        var num : Int = 0

        var arrayTemp : ArrayList<Dish2> = ArrayList()

        btnMua.setOnClickListener {
            arrayTemp.clear()
            for(i in 0..adapter.count-1){
                if(adapter.array.get(i).checked.isChecked){
                    check = true
                    arrayTemp.add(Dish2(adapter.array.get(i).name, adapter.array.get(i).num))
                }
            }
            if(check){
                var intent : Intent = Intent(this@MainActivity, MainActivity2::class.java)
                val array : ArrayList<Dish2> = arrayTemp
                intent.putExtra("data", array)
                startActivity(intent)
            }
        }
    }

}