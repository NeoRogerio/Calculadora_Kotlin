package com.example.myapplication

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculadoraViewModel() : ViewModel() {

    private val _expressionData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val _resultData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val expressionData: LiveData<String>
        get() = _expressionData
    val resultData: LiveData<String>
        get() = _resultData

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
        } else {
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
        }
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

    fun equalClick(expression: TextView, ResultTextView: TextView) {
        if (!blockOperator) {
            listExpression.add(currentNumber.toFloat())
            ResultTextView.text = Calculadora.getCalculo(listExpression)
            expression.text = ""
            blockOperator = true
            currentNumber = ""
            listExpression.clear()
        }
    }
}