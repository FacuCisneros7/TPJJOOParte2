package com.electrofire.myapplication2

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
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
    private lateinit var et_sport : TextView

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
        et_sport = findViewById(R.id.et_sport)

        val intermediario = intent.getIntExtra("selectedIntermediary", -1)
        val usuarioActual = intent.getSerializableExtra("usuarioActual") as? User
        val asiento = intent.getStringExtra("asientoDelEvento")
        val id = intent.getLongExtra("idDelEvento", -1)

        val eventoActual = eventRepo.getById(id)

        val place = eventoActual!!.place
        val hour = eventoActual.hour
        val price = eventoActual.price
        val date = eventoActual.date
        val sport = eventoActual.sport.name


        et_place.setText(place)
        et_hour.setText(hour)
        et_date.setText(date)
        et_sport.setText(sport)

        val precioFinal = calcularPrecioFinal(intermediario,price)
        et_price.setText(precioFinal.toString())


        btn_confirmar.setOnClickListener {

            if ((usuarioActual?.money ?: 0.0) > precioFinal) {

                    registrarCompra(usuarioActual, eventoActual, precioFinal, createdDate, asiento, purchaseRepo)
                    userRepo.restarPrecioAlSaldo(usuarioActual!!,precioFinal)

                    mostrarImagen(R.drawable.compraexitosa){
                        val menuActivityIntent = Intent(this, MenuActivity::class.java)
                        menuActivityIntent.putExtra("usuarioActual", usuarioActual)
                        startActivity(menuActivityIntent)
                        finish()
                    }

                } else{

                    mostrarImagen(R.drawable.comprarechazada){
                        val menuActivityIntent = Intent(this, MenuActivity::class.java)
                        menuActivityIntent.putExtra("usuarioActual", usuarioActual)
                        startActivity(menuActivityIntent)
                        finish()
                    }

                }

        }

    }

    private fun mostrarImagen(imagenResId: Int, onDismiss: () -> Unit) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_imagen)
        dialog.findViewById<ImageView>(R.id.ivResultado).setImageResource(imagenResId)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
            onDismiss()
        }, 2000)
    }

}