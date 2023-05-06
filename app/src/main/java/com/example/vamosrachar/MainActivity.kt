package com.example.vamosrachar

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.util.Locale

class MainActivity : ComponentActivity() {

    private var valorPorPessoa: Double = 0.0
    lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dinheiro: EditText = findViewById(R.id.dinheiro)
        val qtdPessoas: EditText = findViewById(R.id.qtd_pessoas)
        val resultado: TextView = findViewById(R.id.resultado)
        val botaoCompartilhar: Button = findViewById(R.id.shareButton)
        val botaoTTS: Button = findViewById(R.id.speech)

        qtdPessoas.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val dDinheiro = dinheiro.text.toString().toDoubleOrNull()
                val dQtdPessoas = qtdPessoas.text.toString().toIntOrNull()

                if (dQtdPessoas != null && dQtdPessoas != 0 && dDinheiro != null) {
                    valorPorPessoa = dDinheiro / dQtdPessoas
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
                    valorPorPessoa = dDinheiro / dQtdPessoas
                    resultado.text = "Valor por pessoa: R$ ${"%.2f".format(valorPorPessoa)}"

                    } else {
                    resultado.text = "Informe a quantidade de pessoas."
                }
            }
        })

        textToSpeech = TextToSpeech(this) {
            textToSpeech.setLanguage(Locale("pt", "BR")
            )}

        botaoCompartilhar.setOnClickListener {
            val textoCompartilhado = "O valor que cada pessoa vai pagar é ${"%.2f".format(valorPorPessoa)}"

            val intent = Intent(Intent(ACTION_SEND))
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, textoCompartilhado)

            val chooser = Intent.createChooser(intent, "Compartilhar com:")

            startActivity(chooser)

        }

        botaoTTS.setOnClickListener {
            val text = resultado.text.toString()
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }

        }
    }
