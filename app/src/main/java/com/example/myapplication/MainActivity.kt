package com.example.myapplication

// Importando o layout a partir do nome definido no XML
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.CalculadoraUIBinding

var blockOperator:Boolean = true
var hasComa:Boolean = false
var currentNumber:String = ""
var listExpression = mutableListOf<Any>()

class MainActivity : AppCompatActivity() {
    // Definindo a variavel que ira receber o layout da calculadora
    // "lateinit" para declarar a variavel para inicializar mais tarde
    private lateinit var binding: CalculadoraUIBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializando a variavel pre declarada
        binding = CalculadoraUIBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn0.setOnClickListener {
            numberClick(binding.txtEquacao, "0")
        }

        binding.btn1.setOnClickListener {
            numberClick(binding.txtEquacao, "1")
        }

        binding.btn2.setOnClickListener{
            numberClick(binding.txtEquacao, "2")
        }

        binding.btn3.setOnClickListener{
            numberClick(binding.txtEquacao, "3")
        }

        binding.btn4.setOnClickListener{
            numberClick(binding.txtEquacao, "4")
        }

        binding.btn5.setOnClickListener{
            numberClick(binding.txtEquacao, "5")
        }

        binding.btn6.setOnClickListener{
            numberClick(binding.txtEquacao, "6")
        }


        binding.btn7.setOnClickListener{
            numberClick(binding.txtEquacao, "7")
        }

        binding.btn8.setOnClickListener{
            numberClick(binding.txtEquacao, "8")
        }

        binding.btn9.setOnClickListener{
            numberClick(binding.txtEquacao, "9")
        }

        binding.btnVirgula.setOnClickListener{
            if (!hasComa) {
                if (currentNumber.isEmpty()) {
                    numberClick(binding.txtEquacao, "0.")
                    hasComa = true
                } else {
                    numberClick(binding.txtEquacao, ".")
                    hasComa = true
                }
            }
        }

        binding.btnDel.setOnClickListener {
            binding.txtEquacao.text = ""
            binding.txtResult.text = ""
            currentNumber = ""
            blockOperator = true
            hasComa = false
        }

        binding.btnAdic.setOnClickListener {
            expressionClick(binding.txtEquacao, "+")
        }

        binding.btnSubt.setOnClickListener {
            expressionClick(binding.txtEquacao, "-")
        }

        binding.btnMult.setOnClickListener {
            expressionClick(binding.txtEquacao, "*")
        }

        binding.btnDivi.setOnClickListener {
            expressionClick(binding.txtEquacao, "/")
        }

        binding.btnIgual.setOnClickListener {
            if (!blockOperator) {
                listExpression.add(currentNumber.toFloat())
                binding.txtResult.text = Calculadora.getCalculo(listExpression)
                binding.txtEquacao.text = ""
                blockOperator = true
                currentNumber = ""
                listExpression.clear()
            }
        }
    }

    private fun numberClick(expression: TextView, number: String) {
        if (blockOperator)
            blockOperator = false
        expression.text = expression.text.toString().plus(number)
        currentNumber = currentNumber.plus(number)
    }

    private fun expressionClick(expression: TextView, operator: String) {
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