package com.umbrella.timer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umbrella.timer.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            viewModel.startTimer()
        }

        binding.buttonPause.setOnClickListener {
            viewModel.pauseTimer()
        }

        binding.buttonStop.setOnClickListener {
            viewModel.stopTimer()
        }

        viewModel.timerLiveData.observe(this) {
            binding.timerValue.text = viewModel.format(it)
        }
    }
}