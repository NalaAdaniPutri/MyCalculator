package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var lastNumeric: Boolean = false
    private var stateError: Boolean = false
    private var lastDot: Boolean = false
    private var operator: String = ""
    private var valueOne: Double = 0.0
    private var valueTwo: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResult = findViewById(R.id.tvResult)

        // Mengubah warna status bar
        window.statusBarColor = Color.parseColor("#FFB6C1")
    }

    fun onDigit(view: View) {
        if (stateError) {
            tvResult.text = (view as Button).text
            stateError = false
        } else {
            tvResult.append((view as Button).text)
        }
        lastNumeric = true
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !stateError && !lastDot) {
            tvResult.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            valueOne = tvResult.text.toString().toDouble()
            operator = (view as Button).text.toString()
            tvResult.append(operator)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric && !stateError) {
            valueTwo = tvResult.text.toString().substringAfter(operator).toDouble()
            val result = when (operator) {
                "+" -> valueOne + valueTwo
                "-" -> valueOne - valueTwo
                "*" -> valueOne * valueTwo
                "/" -> valueOne / valueTwo
                else -> 0.0
            }
            tvResult.text = result.toString()
            lastNumeric = false
            stateError = false
            lastDot = false
        }
    }

    fun onClear(view: View) {
        tvResult.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }
}