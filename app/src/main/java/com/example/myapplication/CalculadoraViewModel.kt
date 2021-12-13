package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.where

class CalculadoraViewModel : ViewModel() {

    private val _expressionData = MutableLiveData<String>()
    private val _resultData = MutableLiveData<String>()
    private val _historico = MutableLiveData<ArrayList<String>>()

    val expressionData: LiveData<String>
        get() = _expressionData
    val resultData: LiveData<String>
        get() = _resultData
    val historico: LiveData<ArrayList<String>>
        get() = _historico

    private var blockOperator:Boolean = true
    private var hasComa:Boolean = false
    private var currentNumber:String = ""
    private var listExpression = mutableListOf<Any>()

    private val realmName: String = "Resultados"
    private val config = RealmConfiguration.Builder()
        .name(realmName)
        .allowWritesOnUiThread(true)
        .build()
    private val backgroundThreadRealm: Realm = Realm.getInstance(config)

    private val calculadoraModel : ExpressionsList = ExpressionsList()
    private var listaHistorico: RealmResults<ExpressionsList> = backgroundThreadRealm.where<ExpressionsList>().findAll()
    var arrayHistorico = ArrayList<String>()

    init {
        _expressionData.value = ""
        _resultData.value = ""
        for (lista in listaHistorico) {
            arrayHistorico.add(lista.expressionResult)
        }
        _historico.value = arrayHistorico
    }

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
            calculadoraModel.expressionResult = _expressionData.value.plus(" = " + _resultData.value)
            backgroundThreadRealm.executeTransaction    { transitionRealm ->
                transitionRealm.insertOrUpdate(calculadoraModel)
            }
            _expressionData.value = ""
            blockOperator = true
            currentNumber = ""
            listExpression.clear()
            listaHistorico = backgroundThreadRealm.where<ExpressionsList>().findAll()
            arrayHistorico.clear()
            for (lista in listaHistorico) {
                arrayHistorico.add(lista.expressionResult)
            }
            _historico.value = arrayHistorico
        }
    }



}