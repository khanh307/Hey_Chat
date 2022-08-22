package com.example.appdate.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appdate.R
import com.example.appdate.data.Users
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_home.*

import java.util.*


class HomeFragment() : Fragment(){

    private lateinit var nameOne : TextView
    private lateinit var sbBar : SeekBar
    private lateinit var editTotalDays : ImageButton
    private lateinit var editNameTwo : ImageButton
    lateinit var editNameOne : ImageButton
    private lateinit var editProgressBar : ImageButton
    private lateinit var editBackground : ImageButton
    private lateinit var card3 : CardView
    private lateinit var card4 : CardView
    private lateinit var backgroud : ImageView
    private lateinit var imageOne : ImageView
    private lateinit var imageTwo : ImageView
    private lateinit var nameTwo: TextView
    private lateinit var tvTotalDays : TextView
    private lateinit var fragment : FrameLayout

    private var click = 1
    private var data1 : Uri? = null
    private var data2 : Uri? = null
    private var data3 : Uri? = null
    private var users : Users? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_home, container, false)
        nameOne = view.findViewById(R.id.nameOne)
        nameTwo = view.findViewById(R.id.nameTwo)
        sbBar = view.findViewById(R.id.sbPercent)
        editTotalDays = view.findViewById(R.id.editTotalDays)
        editNameTwo = view.findViewById(R.id.editNameTwo)
        editNameOne = view.findViewById(R.id.editNameOne)
        editProgressBar = view.findViewById(R.id.editProgressbar)
        editBackground = view.findViewById(R.id.edit_backgroud)
        card3 = view.findViewById(R.id.card3)
        card4 = view.findViewById(R.id.card4)
        backgroud = view.findViewById(R.id.backgroud)
        imageOne = view.findViewById(R.id.image_one)
        imageTwo = view.findViewById(R.id.image_two)
        tvTotalDays = view.findViewById(R.id.tvTotalDays)
        fragment = view.findViewById(R.id.fragment)



        if(users == null){
            users = Users()
        }

        if(data1 == null){
            backgroud.setImageResource(R.drawable.background)
        } else{
            backgroud.setImageURI(data1)
        }

        if(data2 == null){
            imageOne.setImageResource(R.drawable.background)
        } else {
            imageOne.setImageURI(data2)
        }
        if(data3 == null){
            imageTwo.setImageResource(R.drawable.background)
        } else {
            imageTwo.setImageURI(data3)
        }
        var date : String = users!!.getDate().format(Calendar.getInstance().time)
        tvTotalDays.text = date

        nameOne.text = users!!.getUser1().getName()
        nameTwo.text = users!!.getUser2().getName()

        editBackground.setOnClickListener {
           choseImage()
            click = 1
        }

        card3.setOnClickListener{
            choseImage()
            click = 2
        }

        card4.setOnClickListener{
            choseImage()
            click = 3
        }

        editNameOne.setOnClickListener {
//            var viewDialog : View = layoutInflater.inflate(R.layout.dialog_edit_name, null)
//            var bottomSheetDialog : BottomSheetDialog = BottomSheetDialog(this.requireContext())
//            bottomSheetDialog.setContentView(viewDialog)
//            bottomSheetDialog.show()
//
//
//            var editNameUser : ImageButton = viewDialog.findViewById(R.id.editNameUser)
//            editNameUser.setOnClickListener {
//                var transition : FragmentTransaction = requireFragmentManager().beginTransaction()
//                transition.replace(R.id.frame, EditNameFragment())
//                transition.commit()
//                Toast.makeText(this.context, "editNameUser", Toast.LENGTH_SHORT).show()
//            }

            var transition : FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.fragment, EditNameFragment())
            transition.commit()
        }

        setVisible(View.INVISIBLE)
        sbBar.isEnabled = false
        return view
    }


    fun setUsers(users: Users){
        this.users = users
    }


        private fun choseImage(){
            if(VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(requireActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    var permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else{
                    pickImageFromGallery()
                }
            } else{
                pickImageFromGallery()
            }
        }

        private fun pickImageFromGallery() {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }


    fun setVisible(bol : Int){
        fragment.visibility = bol
        editTotalDays.visibility = bol
        editNameTwo.visibility = bol
        editProgressBar.visibility = bol
        editNameOne.visibility = bol
        editBackground.visibility = bol
        card3.visibility = bol
        card4.visibility = bol
    }

    companion object{
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE ->{
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                } else{
                    Toast.makeText(this.context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            if(click == 1){
                this.data1 = data?.data!!
                backgroud.setImageURI(data?.data)
            } else if(click == 2){
                this.data2 = data?.data!!
                imageOne.setImageURI(data?.data)
            } else if(click == 3){
                this.data3 = data?.data!!
                imageTwo.setImageURI(data?.data)
            }
        }
    }

}


