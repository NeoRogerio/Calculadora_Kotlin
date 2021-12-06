package com.example.myapplication

// Importando o layout a partir do nome definido no XML
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.CalculadoraUIBinding

class MainActivity : AppCompatActivity() {
    // Definindo a variavel que ira receber o layout da calculadora
    // "lateinit" para declarar a variavel para inicializar mais tarde
    private lateinit var binding: CalculadoraUIBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializando a variavel pre declarada
        binding = CalculadoraUIBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        setContentView(binding.root)

        val model: CalculadoraViewModel by viewModels()

        val expressionObserver = Observer<String> {
            newExpression ->
            binding.txtEquacao.text = newExpression
        }
        val resultObserver = Observer<String> {
            newResult ->
            binding.txtResult.text = newResult
        }
        model.expressionData.observe(this, expressionObserver)
        model.resultData.observe(this, resultObserver)

        //Listeners dos Botões
        binding.btn0.setOnClickListener {
            model.numberClick(binding.txtEquacao, "0",false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btn1.setOnClickListener {
            model.numberClick(binding.txtEquacao, "1",false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btn2.setOnClickListener{
            model.numberClick(binding.txtEquacao, "2",false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btn3.setOnClickListener{
            model.numberClick(binding.txtEquacao, "3",false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btn4.setOnClickListener{
            model.numberClick(binding.txtEquacao, "4",false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btn5.setOnClickListener{
            model.numberClick(binding.txtEquacao, "5",false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btn6.setOnClickListener{
            model.numberClick(binding.txtEquacao, "6",false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }


        binding.btn7.setOnClickListener{
            model.numberClick(binding.txtEquacao, "7",false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btn8.setOnClickListener{
            model.numberClick(binding.txtEquacao, "8",false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btn9.setOnClickListener{
            model.numberClick(binding.txtEquacao, "9", false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btnVirgula.setOnClickListener{
            model.numberClick(binding.txtEquacao, ".", false)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btnDel.setOnClickListener {
            model.numberClick(binding.txtEquacao, "", true)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btnAdic.setOnClickListener {
            model.expressionClick(binding.txtEquacao, "+")
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btnSubt.setOnClickListener {
            model.expressionClick(binding.txtEquacao, "-")
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btnMult.setOnClickListener {
            model.expressionClick(binding.txtEquacao, "*")
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btnDivi.setOnClickListener {
            model.expressionClick(binding.txtEquacao, "/")
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }

        binding.btnIgual.setOnClickListener {
            model.equalClick(binding.txtEquacao, binding.txtResult)
//            model.expressionData.setValue(binding.txtEquacao.text.toString())
//            model.resultData.setValue(binding.txtResult.text.toString())
        }
    }

}