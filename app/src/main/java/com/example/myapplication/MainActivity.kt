package com.example.myapplication

// Importando o layout a partir do nome definido no XML
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.CalculadoraUIBinding
import io.realm.Realm
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    // Definindo a variavel que ira receber o layout da calculadora
    // "lateinit" para declarar a variavel para inicializar mais tarde
    private lateinit var binding: CalculadoraUIBinding
    private lateinit var realm : Realm
    private val viewModel: CalculadoraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        realm = Realm.getDefaultInstance()
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
            binding.linearHistorico.removeAllViews()
            for (resultado in viewModel.historico.value!!) {
                val tvHistorico = TextView(this)
                tvHistorico.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                tvHistorico.textAlignment = ConstraintLayout.TEXT_ALIGNMENT_TEXT_END
                tvHistorico.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
                tvHistorico.text = resultado
                binding.linearHistorico.addView(tvHistorico)
            }
            binding.scrollHistorico.post {
                binding.scrollHistorico.fullScroll(ScrollView.FOCUS_DOWN)
            }
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
            addResultToDb()
        }
    }

    private fun addResultToDb() {
        try {
            realm.beginTransaction()
            val calculadoraModel = ExpressionsList()
            calculadoraModel.expressionResult = viewModel.expressionData.value.plus(" = " + viewModel.resultData)
            realm.copyToRealmOrUpdate(calculadoraModel)
            realm.commitTransaction()

            Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show()
        } catch (e : Exception) {
            Toast.makeText(this, "Erro $e", Toast.LENGTH_LONG).show()
        }
    }

}