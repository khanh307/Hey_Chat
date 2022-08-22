package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent = intent
        val arrayList : ArrayList<Dish2> = intent.getSerializableExtra("data") as ArrayList<Dish2>
        var adapter : ListViewAdapter2 = ListViewAdapter2(this@MainActivity2, arrayList)
        listview2.adapter = adapter
        var total : Int = 0
        for(i in 0..adapter.count-1){
            total += adapter.array[i].num
        }
        tvtotal.text = total.toString()
    }
}