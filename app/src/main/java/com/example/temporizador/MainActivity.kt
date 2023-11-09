package com.example.temporizador

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.temporizador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var tiempo: CountDownTimer
    private lateinit var editTextHours: EditText
    private lateinit var editTextMinutes: EditText
    private lateinit var editTextSeconds: EditText
    private lateinit var tvTimeRemaining: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextHours = findViewById(R.id.editTextHours)
        editTextMinutes = findViewById(R.id.editTextMinutes)
        editTextSeconds = findViewById(R.id.editTextSeconds)
        tvTimeRemaining = findViewById(R.id.tvTimeRemaining)
        binding.btndetener.setOnClickListener() { detener() }
    }

    private fun detener() {
        tiempo.cancel()
    }

    fun startTimer(view: View) {
        val hours = editTextHours.text.toString().toLongOrNull() ?: 0
        val minutes = editTextMinutes.text.toString().toLongOrNull() ?: 0
        val seconds = editTextSeconds.text.toString().toLongOrNull() ?: 0

        val totalTimeMillis = (hours * 3600 + minutes * 60 + seconds) * 1000

        tiempo = object : CountDownTimer(totalTimeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainingHours = millisUntilFinished / 3600000
                val remainingMinutes = (millisUntilFinished % 3600000) / 60000
                val remainingSeconds = (millisUntilFinished % 60000) / 1000

                val formattedTime = String.format(
                    "Tiempo restante: %02d:%02d:%02d",
                    remainingHours, remainingMinutes, remainingSeconds
                )

                tvTimeRemaining.text = formattedTime
            }

            override fun onFinish() {
                tvTimeRemaining.text = "¡Tiempo agotado!"
            }
        }
        tiempo.start()
    }

}


