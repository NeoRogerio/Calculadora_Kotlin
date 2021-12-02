package com.example.myapplication

// Importando o layout a partir do nome definido no XML
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.CalculadoraUIBinding

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
            numClick(binding.txtEquacao, "0")
        }

        binding.btn1.setOnClickListener {
            numClick(binding.txtEquacao, "1")
        }

        binding.btn2.setOnClickListener{
            numClick(binding.txtEquacao, "2")
        }

        binding.btn3.setOnClickListener{
            numClick(binding.txtEquacao, "3")
        }

        binding.btn4.setOnClickListener{
            numClick(binding.txtEquacao, "4")
        }

        binding.btn5.setOnClickListener{
            numClick(binding.txtEquacao, "5")
        }

        binding.btn6.setOnClickListener{
            numClick(binding.txtEquacao, "6")
        }


        binding.btn7.setOnClickListener{
            numClick(binding.txtEquacao, "7")
        }

        binding.btn8.setOnClickListener{
            numClick(binding.txtEquacao, "8")
        }

        binding.btn9.setOnClickListener{
            numClick(binding.txtEquacao, "9")
        }

        binding.btnDel.setOnClickListener {
            binding.txtEquacao.text = ""
        }

        binding.btnAdic.setOnClickListener {
            binding.txtEquacao.text = expressionClick(binding.txtEquacao.text.toString(), " + ")
        }

        binding.btnSubt.setOnClickListener {
            binding.txtEquacao.text = expressionClick(binding.txtEquacao.text.toString(), " - ")
        }

        binding.btnMult.setOnClickListener {
            binding.txtEquacao.text = expressionClick(binding.txtEquacao.text.toString(), " * ")
        }


        binding.btnDivi.setOnClickListener {
            binding.txtEquacao.text = expressionClick(binding.txtEquacao.text.toString(), " / ")
        }
    }

    private fun numClick(expression: TextView, number: String) {
        expression.text = expression.text.toString().plus(number)
    }

    private fun expressionClick(expression: String, operator: String): CharSequence {
        return expression.plus(operator)
    }

}