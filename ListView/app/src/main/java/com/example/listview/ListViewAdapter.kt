package com.example.listview

import android.content.Context
import android.graphics.ColorSpace.Model
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.linedish.view.*

class ListViewAdapter(var context : Context, var array : ArrayList<Dish>) : BaseAdapter() {

    private class ViewHolder(row : View){
        var textView : TextView
        var imageView : ImageView
        var checkBox : CheckBox
        var btnAdd : ImageButton
        var btnSub : ImageButton
        var tvNum : TextView
        init {
            textView = row.findViewById(R.id.textview)
            imageView = row.findViewById(R.id.imageview)
            checkBox = row.findViewById(R.id.checkbox)
            btnAdd = row.findViewById(R.id.btnAdd)
            btnSub = row.findViewById(R.id.btnSub)
            tvNum = row.findViewById(R.id.tvNum)

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
            view = layoutFlater.inflate(R.layout.linedish, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = p1;
            viewHolder = p1.tag as ViewHolder
        }
        var line : Dish = getItem(p0) as Dish
        viewHolder.textView.text = line.name
        viewHolder.imageView.setImageResource(line.image)
        viewHolder.checkBox.isChecked = line.checked.isChecked
        viewHolder.tvNum.text = line.num.toString()
        viewHolder.checkBox.setOnClickListener {
            if(line.checked.isChecked){
                line.checked.isChecked = false
            }else{
                line.checked.isChecked = true
            }

        }
        viewHolder.btnAdd.setOnClickListener {
            line.num++;
            notifyDataSetChanged()
        }

        viewHolder.btnSub.setOnClickListener {
            if(line.num > 0){
                line.num--;
            }
            notifyDataSetChanged()
        }

        return view as View
    }

}


