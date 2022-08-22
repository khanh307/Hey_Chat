package com.example.sudokuapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.view.View

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.custom_bottom.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btnNew.setOnClickListener {
            clickOpenBottomLayout()
        }
    }

    private fun clickOpenBottomLayout() {
        val viewDialog : View = layoutInflater.inflate(R.layout.custom_bottom, null)

        val bottomSheetDialog : BottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(viewDialog)
        bottomSheetDialog.show()
        val intent : Intent = Intent(this, MainActivity::class.java)

        bottomSheetDialog.btnEasy.setOnClickListener {
            intent.putExtra("level", 9.1F)
            startActivity(intent)
        }

        bottomSheetDialog.btnMedium.setOnClickListener {
            intent.putExtra("level", 9.2F)
            startActivity(intent)
        }

        bottomSheetDialog.btnHard.setOnClickListener {
            intent.putExtra("level", 9.3F)
            startActivity(intent)
        }

        bottomSheetDialog.btnSHard.setOnClickListener {
            intent.putExtra("level", 9.4F)
            startActivity(intent)
        }

    }
}