package com.example.appvinilos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.appvinilos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_albums, R.id.navigation_artists, R.id.navigation_collectors, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Custom navigation listener to reset stack on tab selection
        binding.navView.setOnItemSelectedListener { item ->
            val builder = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(item.itemId, inclusive = true)

            // Let's navigate with these custom options
            navController.navigate(item.itemId, null, builder.build())
            true
        }
    }

    // This is needed to handle the up arrow correctly with our custom listener
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}