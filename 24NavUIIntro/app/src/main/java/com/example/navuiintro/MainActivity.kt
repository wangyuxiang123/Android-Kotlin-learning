package com.example.navuiintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        navController = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val info = "导航到了 ${destination.label}"
            Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
        }
    }

    // 返回按钮具体功能
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}