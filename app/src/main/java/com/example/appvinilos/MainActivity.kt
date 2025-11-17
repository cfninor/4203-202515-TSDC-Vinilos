package com.example.appvinilos

import android.content.ComponentCallbacks2
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import coil.imageLoader
import com.example.appvinilos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ComponentCallbacks2 {

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

            navController.navigate(item.itemId, null, builder.build())
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        // This method is called when the system is low on memory.
        // We can react by clearing caches.
        if (level >= TRIM_MEMORY_COMPLETE) {
            // For example, clear Coil's memory cache
            this.imageLoader.memoryCache?.clear()
        }
    }

    // This is needed to handle the up arrow correctly with our custom listener
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}