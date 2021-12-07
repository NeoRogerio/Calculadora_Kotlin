package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculadoraViewModel : ViewModel() {

    private val _expressionData = MutableLiveData<String>()
    private val _resultData = MutableLiveData<String>()

    val expressionData: LiveData<String>
        get() = _expressionData
    val resultData: LiveData<String>
        get() = _resultData

    init {
        _expressionData.value = ""
        _resultData.value = ""
    }

    private var blockOperator:Boolean = true
    private var hasComa:Boolean = false
    private var currentNumber:String = ""
    private var listExpression = mutableListOf<Any>()

    fun numberClick(number: String, del: Boolean) {
        if (del) {
            _expressionData.value = ""
            _resultData.value = ""
            currentNumber = ""
            blockOperator = true
            hasComa = false
        } else {
            if (blockOperator)
                blockOperator = false
            if (number == ".") {
                if (!hasComa) {
                    if (currentNumber.isEmpty()) {
                        _expressionData.value = _expressionData.value.plus("0.")
                        currentNumber = currentNumber.plus("0.")
                        hasComa = true
                    } else {
                        _expressionData.value = _expressionData.value.toString().plus(number)
                        currentNumber = currentNumber.plus(number)
                        hasComa = true
                    }
                }
            } else {
                _expressionData.value = _expressionData.value.toString().plus(number)
                currentNumber = currentNumber.plus(number)
            }
        }
    }

    fun expressionClick(operator: String) {
        if (!blockOperator) {
            _expressionData.value = _expressionData.value.toString().plus(operator)
            listExpression.add(currentNumber.toFloat())
            listExpression.add(operator)
            blockOperator = true
            hasComa = false
            currentNumber = ""
        }
    }

    fun equalClick() {
        if (!blockOperator) {
            listExpression.add(currentNumber.toFloat())
            _resultData.value = Calculadora.getCalculo(listExpression)
            _expressionData.value = ""
            blockOperator = true
            currentNumber = ""
            listExpression.clear()
        }
    }
}