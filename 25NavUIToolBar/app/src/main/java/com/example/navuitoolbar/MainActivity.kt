package com.example.navuitoolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.firstFragment -> navController.navigate(R.id.firstFragment)
            R.id.secondFragment -> navController.navigate(R.id.secondFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        // onStart(): Activity 中获取，需要 Fragment 装载完毕才可以
        navController = findNavController(R.id.fragmentContainerView)
        toolbar.setupWithNavController(navController)

        //  若三个 Fragment 没有主次之分
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.firstFragment,
                R.id.secondFragment
            )
        )
        toolbar.setupWithNavController(navController, appBarConfiguration)

    }

}