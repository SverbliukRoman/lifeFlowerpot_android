package com.sverbusoft.lifeflowerpot.ui.activity.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sverbusoft.lifeflowerpot.R
import com.sverbusoft.lifeflowerpot.databinding.ActivityMainBinding
import com.sverbusoft.lifeflowerpot.ui.activity.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding;
    private lateinit var navController: NavController;

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                navController.navigate(R.id.newsFragment)
                supportActionBar?.show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                navController.navigate(R.id.dashboardFragment)
                supportActionBar?.show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                navController.navigate(R.id.profileFragment)
                supportActionBar?.hide()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
