package com.blackstoneit.weatherforecasting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blackstoneit.weatherforecasting.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    private var _binding: ActivityMainBinding?=null
    private val binding: ActivityMainBinding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}