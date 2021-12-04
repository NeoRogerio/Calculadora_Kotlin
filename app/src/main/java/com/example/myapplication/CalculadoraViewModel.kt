package com.example.myapplication

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel

class CalculadoraViewModel : ViewModel() {
    private var blockOperator:Boolean = true
    private var hasComa:Boolean = false
    private var currentNumber:String = ""
    private var listExpression = mutableListOf<Any>()

    fun numberClick(expression: TextView, number: String, del: Boolean) {
        if (del) {
            expression.text = ""
            currentNumber = ""
            blockOperator = true
            hasComa = false
        }
        if (blockOperator)
            blockOperator = false
        if (number == ".") {
            if (!hasComa) {
                if (currentNumber.isEmpty()) {
                    expression.text = expression.text.toString().plus("0.")
                    currentNumber = currentNumber.plus("0.")
                    hasComa = true
                } else {
                    expression.text = expression.text.toString().plus(number)
                    currentNumber = currentNumber.plus(number)
                    hasComa = true
                }
            }
        } else {
            expression.text = expression.text.toString().plus(number)
            currentNumber = currentNumber.plus(number)
        }
        Log.i("test", currentNumber)
    }

    fun expressionClick(expression: TextView, operator: String) {
        if (!blockOperator) {
            expression.text = expression.text.toString().plus(operator)
            listExpression.add(currentNumber.toFloat())
            listExpression.add(operator)
            blockOperator = true
            hasComa = false
            currentNumber = ""
        }
    }
}