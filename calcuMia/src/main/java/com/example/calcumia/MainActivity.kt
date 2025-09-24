package com.example.calcumia


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

 private lateinit var tvPantalla: TextView

 private var input: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPantalla = findViewById(R.id.txtPantalla)

        val button = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnPunto,
            R.id.btnSumar, R.id.btnRestar, R.id.btnMultiplicar, R.id.btnDividir
        )

        for(id in button){
            findViewById<Button>(id).setOnClickListener {
                input += (it as Button).text
                tvPantalla.text= input
            }
        }
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            if (input.isNotEmpty()){
                input = input.dropLast(1)
                tvPantalla.text= if(input.isEmpty())"0" else input
            } else{
                tvPantalla.text = "Syntax Error"
            }
        }
        findViewById<Button>(R.id.btnClearAll).setOnClickListener {
            input =" "
            tvPantalla.text ="0"
        }
        findViewById<Button>(R.id.btnIgual).setOnClickListener {
            try {
                val expression = ExpressionBuilder(input).build()
                val result= expression.evaluate()
                val resultadoNormalizado = normalizarResultado(result)
                if(resultadoNormalizado % 1.0 == 0.0){
                    tvPantalla.text = resultadoNormalizado.toInt().toString()
                    input= resultadoNormalizado.toInt().toString()
                }
                else{
                    tvPantalla.text = resultadoNormalizado.toString()
                    input= resultadoNormalizado.toString()
                }
            }catch (e: Exception){
                tvPantalla.text = "Syntax Error"
                input =""
            }
        }
    }
    private fun normalizarResultado( valor: Double): Double{
        return if (abs( valor) <1e-10) 0.0 else valor
    }
}
