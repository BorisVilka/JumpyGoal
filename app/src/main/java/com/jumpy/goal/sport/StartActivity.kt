package com.jumpy.goal.sport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jumpy.goal.sport.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}