package com.example.myapplication

// Importando o layout a partir do nome definido no XML
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
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

        val model: CalculadoraViewModel by viewModels()

        // Inicializando a variavel pre declarada
        binding = CalculadoraUIBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Listeners dos Botões
        binding.btn0.setOnClickListener {
            model.numberClick(binding.txtEquacao, "0",false)
        }

        binding.btn1.setOnClickListener {
            model.numberClick(binding.txtEquacao, "1",false)
        }

        binding.btn2.setOnClickListener{
            model.numberClick(binding.txtEquacao, "2",false)
        }

        binding.btn3.setOnClickListener{
            model.numberClick(binding.txtEquacao, "3",false)
        }

        binding.btn4.setOnClickListener{
            model.numberClick(binding.txtEquacao, "4",false)
        }

        binding.btn5.setOnClickListener{
            model.numberClick(binding.txtEquacao, "5",false)
        }

        binding.btn6.setOnClickListener{
            model.numberClick(binding.txtEquacao, "6",false)
        }


        binding.btn7.setOnClickListener{
            model.numberClick(binding.txtEquacao, "7",false)
        }

        binding.btn8.setOnClickListener{
            model.numberClick(binding.txtEquacao, "8",false)
        }

        binding.btn9.setOnClickListener{
            model.numberClick(binding.txtEquacao, "9", false)
        }

        binding.btnVirgula.setOnClickListener{
            if (!hasComa) {
                if (currentNumber.isEmpty()) {
                    model.numberClick(binding.txtEquacao, "0.", false)
                    hasComa = true
                } else {
                    model.numberClick(binding.txtEquacao, ".", false)
                    hasComa = true
                }
            }
        }

        binding.btnDel.setOnClickListener {
            model.numberClick(binding.txtEquacao, "", true)
        }

        binding.btnAdic.setOnClickListener {
            model.expressionClick(binding.txtEquacao, "+")
        }

        binding.btnSubt.setOnClickListener {
            model.expressionClick(binding.txtEquacao, "-")
        }

        binding.btnMult.setOnClickListener {
            model.expressionClick(binding.txtEquacao, "*")
        }

        binding.btnDivi.setOnClickListener {
            model.expressionClick(binding.txtEquacao, "/")
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

}