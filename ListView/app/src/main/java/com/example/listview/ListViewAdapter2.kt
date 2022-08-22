package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ListViewAdapter2(var context : Context, var array : ArrayList<Dish2>) : BaseAdapter() {
    private class ViewHolder(row : View){
        var textView : TextView
        var tvNum : TextView
        init {
            textView = row.findViewById(R.id.textview2)
            tvNum = row.findViewById(R.id.tvNum2)
        }


    }


    override fun getCount(): Int {
       return array.size
    }

    override fun getItem(p0: Int): Any {
       return array.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view : View?
        var viewHolder : ViewHolder
        if(p1 == null){
            var layoutFlater : LayoutInflater = LayoutInflater.from(context)
            view = layoutFlater.inflate(R.layout.line_dish, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = p1;
            viewHolder = p1.tag as ViewHolder
        }
        var line : Dish2 = getItem(p0) as Dish2
        viewHolder.textView.text = line.name
        viewHolder.tvNum.text = line.num.toString()

        return view as View
    }
}