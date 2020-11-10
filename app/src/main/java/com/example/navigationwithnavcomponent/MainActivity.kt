package com.example.navigationwithnavcomponent

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var appbarconfig:AppBarConfiguration
    lateinit var drawerLayout:DrawerLayout
    lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout=findViewById(R.id.drawerLayout)
        navigationView=findViewById(R.id.navigation_view)
        setUpNavigationController()
    }

    fun setUpNavigationController(){

        val navigationcontroller=findNavController(R.id.host_fragment)

      //  appbarconfig = AppBarConfiguration(setOf(R.id.homeFragment, R.id.signInFragment,R.id.signUpFragment), drawerLayout)

       // setupActionBarWithNavController(navigationcontroller, appbarconfig)
        setupActionBarWithNavController(navigationcontroller)

        navigationView.setupWithNavController(navigationcontroller)

        navigationView.setNavigationItemSelectedListener {menuItem->
            drawerLayout.closeDrawers()
            when(menuItem.itemId){

                R.id.menu_home->navigationcontroller.navigate(R.id.homeFragment)
                R.id.menu_sigin->navigationcontroller.navigate(R.id.signInFragment)
                R.id.menu_sign_Up->navigationcontroller.navigate(R.id.signUpFragment)


            }
             true
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        val navigationController=findNavController(R.id.host_fragment)
        return navigationController.navigateUp()||super.onSupportNavigateUp()
    }

//    override fun onSupportNavigateUp(): Boolean {
//
//        val navigationController=findNavController(R.id.host_fragment)
//        return navigationController.navigateUp(appbarconfig)||super.onSupportNavigateUp()
//    }

    @SuppressLint("WrongConstant")
    override fun onBackPressed() {


        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)){
            drawerLayout.closeDrawer(Gravity.RIGHT)

        }else
        {
            super.onBackPressed()

        }
    }


    // right side menu open
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_right_side,menu)
        return true
    }

    @SuppressLint("WrongConstant")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.btnmenu){

            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                drawerLayout.closeDrawer(Gravity.RIGHT)
            }
            else{
                drawerLayout.openDrawer(Gravity.RIGHT)            }

        }
        return super.onOptionsItemSelected(item)
    }
}