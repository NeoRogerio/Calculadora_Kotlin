package com.example.myapplication

import android.util.Log

class Calculadora {
    companion object{
        fun getCalculo(expressao:MutableList<Any>):String {
            if (expressao.isEmpty()) return ""

            val resultMultiDivi = getMultiDivi(expressao)
            if (resultMultiDivi.isEmpty()) return ""

            val result = getAdicSub(resultMultiDivi)
            return result.toString()
        }

        private fun getAdicSub(expressao: MutableList<Any>): Float {
            var result = expressao[0] as Float

            for (i in expressao.indices) {
                if (expressao[i] is String && i != expressao.lastIndex) {
                    val operator = expressao[i]
                    val nextDigit = expressao[i + 1] as Float
                    if (operator == "+")
                        result += nextDigit
                    if (operator == "-")
                        result -= nextDigit
                }
            }

            return result
        }

        private fun getMultiDivi(expressao: MutableList<Any>): MutableList<Any> {
            var list = expressao
            while (list.contains("*") || list.contains("/")) {
                list = calcMultiDivi(list)
            }
            return list
        }

        private fun calcMultiDivi(list: MutableList<Any>): MutableList<Any> {
            val newList = mutableListOf<Any>()
            var restarIndex = list.size

            for (i in list.indices) {
                if (list[i] is String && i != list.lastIndex && i < restarIndex) {
                    val operator = list[i]
                    val leftDigit = list[i - 1] as Float
                    val rightDigit = list[i + 1] as Float
                    when (operator) {
                        "*" -> {
                            newList.add(leftDigit * rightDigit)
                            restarIndex = i + 1
                        }
                        "/" -> {
                            newList.add(leftDigit / rightDigit)
                            restarIndex = i + 1
                        }
                        else -> {
                            newList.add(leftDigit)
                            newList.add(operator)
                        }
                    }
                }
                if (i > restarIndex)
                    newList.add(list[i])
            }
            return newList
        }
    }
}