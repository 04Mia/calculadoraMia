package com.solidusystems.calcu

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtNum1 = findViewById<EditText>(R.id.edt_num_1)
        val edtNum2 = findViewById<EditText>(R.id.edt_num_2)
        val spinner = findViewById<Spinner>(R.id.spn_operations)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val txtResultado = findViewById<TextView>(R.id.txt_resultado)

        val operaciones = arrayOf("Sumar", "Restar", "Multiplicar", "Dividir", "Potencia", "Numero Aleatorio")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operaciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        btnCalcular.setOnClickListener {
            val num1 = edtNum1.text.toString().toDoubleOrNull()
            val num2 = edtNum2.text.toString().toDoubleOrNull()

            if (num1 == null || num2 == null) {
                Toast.makeText(this, "Introduce números válidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultado = when (spinner.selectedItem.toString()) {
                "Sumar" -> num1 + num2
                "Restar" -> num1 - num2
                "Multiplicar" -> num1 * num2
                "Dividir" -> if (num2 != 0.0) num1 / num2 else "Error: División por 0"
                "Potencia" -> num1.pow(x = num2)
                "Numero Aleatorio" -> if (num1 != num2) getRandomDouble(num1,num2)
                else "Error los números son iguales"
                else -> "Operación inválida"

            }

            txtResultado.text = "Resultado: $resultado"
        }

    }
    fun getRandomDouble(min: Double, max: Double): Double
    {
        val randomValue = Math.random()
        return randomValue * (max - min) + min
    }
}