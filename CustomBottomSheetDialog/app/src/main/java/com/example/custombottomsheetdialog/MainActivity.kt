package com.example.custombottomsheetdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpen.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogThemes)
            val bottomSheetView = LayoutInflater.from(applicationContext).inflate(R.layout.layout_bottom_sheet, findViewById(R.id.bottomSheetContainer))
            val btnShow = bottomSheetView.findViewById<Button>(R.id.btnShow)
            btnShow.setOnClickListener {
                Toast.makeText(this, "Show" , Toast.LENGTH_SHORT).show()
            }
            val btnClose = bottomSheetView.findViewById<Button>(R.id.btnClose)
            btnClose.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }
}