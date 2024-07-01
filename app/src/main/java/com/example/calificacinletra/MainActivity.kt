package com.example.calificacinletra

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var nota: EditText
    private lateinit var verificado: TextView
    private lateinit var barra: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        nota = findViewById(R.id.nota)
        verificado = findViewById(R.id.verificado)
        barra = findViewById(R.id.barra)

        val boton: Button = findViewById(R.id.boton)
        boton.setOnClickListener {
            val notaString = nota.text.toString()

            if (notaString.isNotEmpty()) {
                val notaValue = notaString.toInt()
                actualizarSeekBar(notaValue)
                mostrarResultado(notaValue)
            }
        }

        barra.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                nota.setText(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        nota.setOnEditorActionListener { _, _, _ ->
            val notaString = nota.text.toString()

            if (notaString.isNotEmpty()) {
                val notaValue = notaString.toInt()
                actualizarSeekBar(notaValue)
                mostrarResultado(notaValue)
            }

            true
        }
    }

    private fun actualizarSeekBar(valor: Int) {
        barra.progress = valor
    }

    private fun mostrarResultado(nota: Int) {
        val calificacionLetra = when (nota) {
            in 90..100 -> {
                verificado.setTextColor(Color.GREEN)
                "A"
            }
            in 80..89 -> {
                verificado.setTextColor(Color.GREEN)
                "B"
            }
            in 70..79 -> {
                verificado.setTextColor(Color.YELLOW)
                "C"
            }
            in 60..69 -> {
                verificado.setTextColor(Color.RED)
                "D"
            }
            else -> {
                verificado.setTextColor(Color.RED)
                "F"
            }
        }

        verificado.text = "Calificación: $calificacionLetra"
    }
}
