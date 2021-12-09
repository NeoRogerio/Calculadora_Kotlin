package com.example.myapplication

// Importando o layout a partir do nome definido no XML
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.CalculadoraUIBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    // Definindo a variavel que ira receber o layout da calculadora
    // "lateinit" para declarar a variavel para inicializar mais tarde
    private lateinit var binding: CalculadoraUIBinding
    private val viewModel: CalculadoraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Realm.init(this) // Ininicializar biblioteca do Realm apenas uma vez, passando um contexto

        // Inicializando a variavel pre declarada
        binding = CalculadoraUIBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        viewModel.expressionData.observe(this, {
            binding.txtEquacao.text = viewModel.expressionData.value
        })
        viewModel.resultData.observe(this, {
            binding.txtResult.text = viewModel.resultData.value
        })
        viewModel.historico.observe(this, {
            binding.historico.text = viewModel.historico.value
        })

        //Listeners dos Botões
        binding.btn0.setOnClickListener {
            viewModel.numberClick("0",false)
        }

        binding.btn1.setOnClickListener {
            viewModel.numberClick("1",false)
        }

        binding.btn2.setOnClickListener{
            viewModel.numberClick("2",false)
        }

        binding.btn3.setOnClickListener{
            viewModel.numberClick("3",false)
        }

        binding.btn4.setOnClickListener{
            viewModel.numberClick("4",false)
        }

        binding.btn5.setOnClickListener{
            viewModel.numberClick("5",false)
        }

        binding.btn6.setOnClickListener{
            viewModel.numberClick("6",false)
        }


        binding.btn7.setOnClickListener{
            viewModel.numberClick("7",false)
        }

        binding.btn8.setOnClickListener{
            viewModel.numberClick("8",false)
        }

        binding.btn9.setOnClickListener{
            viewModel.numberClick("9", false)
        }

        binding.btnVirgula.setOnClickListener{
            viewModel.numberClick(".", false)
        }

        binding.btnDel.setOnClickListener {
            viewModel.numberClick("", true)
        }

        binding.btnAdic.setOnClickListener {
            viewModel.expressionClick("+")
        }

        binding.btnSubt.setOnClickListener {
            viewModel.expressionClick("-")
        }

        binding.btnMult.setOnClickListener {
            viewModel.expressionClick("*")
        }

        binding.btnDivi.setOnClickListener {
            viewModel.expressionClick("/")
        }

        binding.btnIgual.setOnClickListener {
            viewModel.equalClick()
        }
    }

}