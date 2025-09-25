package com.example.weektwoassignment

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.weektwoassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   // private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle login button
        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                Snackbar.make(binding.root, "Login Success!", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(binding.root, "Please fill in all fields", Snackbar.LENGTH_SHORT).show()
            }
        }

        */
    }
}