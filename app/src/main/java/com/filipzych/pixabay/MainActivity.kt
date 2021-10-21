package com.filipzych.pixabay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
    }

    override fun onBackPressed() {
        findNavController(R.id.nav_host_fragment).popBackStack()
    }
}