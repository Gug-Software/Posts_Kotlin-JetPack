package com.gug.example.posts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gug.example.posts.R
import com.gug.example.posts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding
    lateinit var host: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.postsAll, R.id.postsFavorites),
            null
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavView?.setupWithNavController(navController)

        setOnDestinationChangedListener()

    }

    private fun setOnDestinationChangedListener() {

        host.navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.splashFragment, R.id.aboutFragment -> {
                    binding.bottomNavView.visibility = View.GONE
                    binding.toolbar.visibility = View.GONE
                }
                R.id.postDetailFragment -> {
                    binding.bottomNavView.visibility = View.GONE
                    binding.toolbar.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavView.visibility = View.VISIBLE
                    binding.toolbar.visibility = View.VISIBLE
                }
            }

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        // Allows NavigationUI to support proper up navigation or the drawer layout
        // drawer menu, depending on the situation
        return host.navController.navigateUp(appBarConfiguration)
    }
}
