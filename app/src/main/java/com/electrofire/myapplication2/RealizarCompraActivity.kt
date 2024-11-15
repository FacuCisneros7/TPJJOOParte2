package com.electrofire.myapplication2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import calcularPrecioFinal
import com.electrofire.myapplication2.data.User
import com.electrofire.myapplication2.repositories.EventRepository
import com.electrofire.myapplication2.repositories.PurchaseRepository
import com.electrofire.myapplication2.repositories.UserRepository
import registrarCompra

class RealizarCompraActivity : AppCompatActivity() {

    private lateinit var btn_confirmar : Button
    private lateinit var et_hour : EditText
    private lateinit var et_place : EditText
    private lateinit var et_date : EditText
    private lateinit var et_price : EditText
    val eventRepo = EventRepository
    val purchaseRepo = PurchaseRepository
    val userRepo = UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realizar_compra) // Asegúrate de tener el layout correcto.

        et_hour = findViewById(R.id.et_hora)
        et_place = findViewById(R.id.et_place)
        et_date = findViewById(R.id.et_date)
        et_price = findViewById(R.id.et_price)
        btn_confirmar = findViewById(R.id.btn_confirmar)

        val intermediario = intent.getIntExtra("intermediarioElegido", -1)
        val usuarioActual = intent.getSerializableExtra("usuarioActual") as? User

        val place = intent.getStringExtra("lugarDelEvento")
        val hour = intent.getStringExtra("horaDelEvento")
        val price = intent.getStringExtra("precioDelEvento")
        val date = intent.getStringExtra("diaDelEvento")
        val id = intent.getLongExtra("idDelEvento", -1)
        val asiento = intent.getStringExtra("asientoDelEvento")

        et_place.setText(place)
        et_hour.setText(hour)
        et_date.setText(date)
        et_price.setText(price)

        btn_confirmar.setOnClickListener {

            val precioNumerico = price?.toDoubleOrNull() ?: 0.0
            val eventoActual = eventRepo.getById(id)
            val precioFinal = calcularPrecioFinal(intermediario,precioNumerico)

                if ((usuarioActual?.money ?: 0.0) > precioFinal) {

                    registrarCompra(
                        usuarioActual,
                        eventoActual,
                        precioFinal,
                        date,
                        asiento,
                        purchaseRepo
                    )

                    userRepo.restarPrecioAlSaldo(usuarioActual,precioFinal)

                    Toast.makeText(this, "Compra realizada correctamente", Toast.LENGTH_SHORT).show()

                } else{
                    Toast.makeText(this, "Saldo Insuficiente", Toast.LENGTH_SHORT).show()

                    val mainActivityIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainActivityIntent)
                }

        }

    }

}