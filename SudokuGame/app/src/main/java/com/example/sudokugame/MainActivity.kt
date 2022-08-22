package com.example.sudokugame

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNew.setOnClickListener {
            clickOpenBottomLayout()
        }
    }

    private fun clickOpenBottomLayout() {
        val viewDialog : View = layoutInflater.inflate(R.layout.layout_bottom, null)

        val bottomSheetDialog : BottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(viewDialog)
        bottomSheetDialog.show()
        val intent : Intent = Intent(this, MainActivity2::class.java)

        bottomSheetDialog.btnEasy.setOnClickListener {
            startActivity(intent)
        }

        bottomSheetDialog.btnMedium.setOnClickListener {

            intent.putExtra("level", 9)
            startActivity(intent)
        }

        bottomSheetDialog.btnHard.setOnClickListener {


            startActivity(intent)
        }

        bottomSheetDialog.btnSHard.setOnClickListener {
            startActivity(intent)
        }

    }
}