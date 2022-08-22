package com.example.appdate

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.example.appdate.data.User
import com.example.appdate.data.Users
import com.example.appdate.fragment.HomeFragment
import com.example.appdate.fragment.SettingFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val FRAGMENT_HOME = 0
    private val FRAGMENT_SETTING = 1
    private var currentFragment = FRAGMENT_HOME
    private val home = HomeFragment()

    private lateinit var nameOne : TextView
    private lateinit var sbBar : SeekBar
    private lateinit var editTotalDays : ImageButton
    private lateinit var editNameTwo : ImageButton
    private lateinit var editNameOne : ImageButton
    private lateinit var editProgressBar : ImageButton
    private lateinit var editBackground : ImageButton
    private lateinit var card3 : CardView
    private lateinit var card4 : CardView
    private lateinit var backgroud : ImageView
    private lateinit var imageOne : ImageView
    private lateinit var imageTwo : ImageView
    private lateinit var nameTwo: TextView
    private lateinit var tvTotalDays : TextView
    private var click = 1
    private var data1 : Uri? = null
    private var data2 : Uri? = null
    private var data3 : Uri? = null
    private var users : Users? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSave.visibility = View.INVISIBLE
        showNavigation()
        top_frame.visibility = View.INVISIBLE

        replaceFragment(home)

        btnMore.setOnClickListener {
            showMenuMore()
        }



        btnEdit.setOnClickListener {
            home.setVisible(View.VISIBLE)
            btnEdit.visibility = View.INVISIBLE
            btnMore.visibility = View.INVISIBLE
            toolBar.visibility = View.INVISIBLE
            btnSave.visibility = View.VISIBLE
        }

        btnSave.setOnClickListener {
            home.setVisible(View.INVISIBLE)
            btnEdit.visibility = View.VISIBLE
            btnMore.visibility = View.VISIBLE
            toolBar.visibility = View.VISIBLE
            btnSave.visibility = View.INVISIBLE
        }

//        var user1 : User = User("Khánh", "30/07/2001")
//        var user2 : User = User("Ngân", "13/07/2001")
//        var users : Users = Users(user1, user2, "24/09/2018")
//        home.setUsers(users)

    }



    fun showNavigation(){
        setSupportActionBar(findViewById(R.id.toolBar))
        var toggle : ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,
           findViewById(R.id.toolBar), R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    fun showMenuMore(){
        var popupMenu: PopupMenu = PopupMenu(this, btnMore)
        popupMenu.menuInflater.inflate(R.menu.menu_more, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add -> {
                    Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.delete -> {
                    Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
                    true
                }else -> false

            }
        }
        popupMenu.show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if(id == R.id.home){
            if(currentFragment != FRAGMENT_HOME){
                replaceFragment(home)
                currentFragment = FRAGMENT_HOME
            }
        } else if (id == R.id.setting){
            if(currentFragment != FRAGMENT_SETTING){
                replaceFragment(SettingFragment())
                currentFragment = FRAGMENT_SETTING
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return false
    }


    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else{
            super.onBackPressed()
        }
    }

    fun replaceFragment(fragment: Fragment){
        var transition : FragmentTransaction = supportFragmentManager.beginTransaction()
        transition.replace(R.id.content_frame, fragment)
        transition.commit()
    }



}




