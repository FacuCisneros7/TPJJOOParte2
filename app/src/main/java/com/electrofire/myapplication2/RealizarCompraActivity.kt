package com.electrofire.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import calcularPrecioFinal
import com.electrofire.myapplication2.data.User
import com.electrofire.myapplication2.repositories.EventRepository
import com.electrofire.myapplication2.repositories.PurchaseRepository
import com.electrofire.myapplication2.repositories.UserRepository
import registrarCompra
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RealizarCompraActivity : AppCompatActivity() {

    private lateinit var btn_confirmar : Button
    private lateinit var et_hour : TextView
    private lateinit var et_place : TextView
    private lateinit var et_date : TextView
    private lateinit var et_price : TextView

    val eventRepo = EventRepository
    val purchaseRepo = PurchaseRepository
    val userRepo = UserRepository
    val day = LocalDate.now()
    val createdDate: String = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realizar_compra)

        et_hour = findViewById(R.id.et_hora)
        et_place = findViewById(R.id.et_place)
        et_date = findViewById(R.id.et_date)
        et_price = findViewById(R.id.et_price)
        btn_confirmar = findViewById(R.id.btn_confirmar)

        val intermediario = intent.getIntExtra("selectedIntermediary", -1)
        val usuarioActual = intent.getSerializableExtra("usuarioActual") as? User

        val place = intent.getStringExtra("lugarDelEvento")
        val hour = intent.getStringExtra("horaDelEvento")
        val price = intent.getDoubleExtra("precioDelEvento",0.0)
        val date = intent.getStringExtra("diaDelEvento")
        val id = intent.getLongExtra("idDelEvento", -1)
        val asiento = intent.getStringExtra("asientoDelEvento")

        et_place.setText(place)
        et_hour.setText(hour)
        et_date.setText(date)
        val precioFinal = calcularPrecioFinal(intermediario,price)
        et_price.setText(precioFinal.toString())

        btn_confirmar.setOnClickListener {

            val eventoActual = eventRepo.getById(id)

            if ((usuarioActual?.money ?: 0.0) > precioFinal) {

                    registrarCompra(usuarioActual, eventoActual, precioFinal, createdDate, asiento, purchaseRepo)

                    userRepo.restarPrecioAlSaldo(usuarioActual!!,precioFinal)

                    Toast.makeText(this, "Compra realizada correctamente", Toast.LENGTH_SHORT).show()
                    val menuActivityIntent = Intent(this, MenuActivity::class.java)
                    menuActivityIntent.putExtra("usuarioActual", usuarioActual)
                    startActivity(menuActivityIntent)

                } else{
                    Toast.makeText(this, "Saldo Insuficiente", Toast.LENGTH_SHORT).show()

                    val menuActivityIntent = Intent(this, MenuActivity::class.java)
                    menuActivityIntent.putExtra("usuarioActual", usuarioActual)
                    startActivity(menuActivityIntent)
                }

        }

    }

}