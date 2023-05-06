package com.example.vamosrachar

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vamosrachar.ui.theme.VamosRacharTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dinheiro: EditText = findViewById(R.id.dinheiro)
        val qtdPessoas: EditText = findViewById(R.id.qtd_pessoas)
        val resultado: TextView = findViewById(R.id.resultado)

        qtdPessoas.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val dDinheiro = dinheiro.text.toString().toDoubleOrNull()
                val dQtdPessoas = qtdPessoas.text.toString().toIntOrNull()

                if (dQtdPessoas != null && dQtdPessoas != 0 && dDinheiro != null) {
                    val valorPorPessoa = dDinheiro / dQtdPessoas
                    resultado.text = "Valor por pessoa: R$ ${"%.2f".format(valorPorPessoa)}"
                } else {
                    resultado.text = "Informe o valor a ser dividido."
                }
            }
        })

        dinheiro.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val dDinheiro = dinheiro.text.toString().toDoubleOrNull()
                val dQtdPessoas = qtdPessoas.text.toString().toIntOrNull()

                if (dQtdPessoas != null && dQtdPessoas != 0 && dDinheiro != null) {
                    val valorPorPessoa = dDinheiro / dQtdPessoas
                    resultado.text = "Valor por pessoa: R$ ${"%.2f".format(valorPorPessoa)}"
                } else {
                    resultado.text = "Informe a quantidade de pessoas."
                }
            }
        })
    }
}