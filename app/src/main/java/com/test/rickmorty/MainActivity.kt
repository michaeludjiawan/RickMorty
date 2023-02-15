package com.test.rickmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.rickmorty.databinding.ActivityMainBinding
import com.test.rickmorty.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_main_container, HomeFragment())
            .addToBackStack(null)
            .commit()
    }
}