package com.electrofire.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.electrofire.myapplication2.data.User
import com.electrofire.myapplication2.repositories.PurchaseRepository

class asientoActivity : AppCompatActivity() {

    private lateinit var btn_asientoDisponible : Button
    private lateinit var et_asiento : EditText
    val purchaseRepo = PurchaseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_asiento)

        btn_asientoDisponible = findViewById(R.id.btn_asientoDisponible)
        et_asiento = findViewById(R.id.et_asiento)

        val intermediario = intent.getStringExtra("intermediarioElegido")
        val usuarioActual = intent.getSerializableExtra("usuarioActual") as? User

        val place = intent.getStringExtra("lugarDelEvento")
        val hour = intent.getStringExtra("horaDelEvento")
        val price = intent.getStringExtra("precioDelEvento")
        val date = intent.getStringExtra("diaDelEvento")
        val id = intent.getLongExtra("idDelEvento", -1)


        btn_asientoDisponible.setOnClickListener {

            val asiento = et_asiento.text.toString()

            if(purchaseRepo.estaOcupado(id, asiento) == false){
                Toast.makeText(this, "Asiento valido", Toast.LENGTH_SHORT).show()

                val realizarCompraIntent = Intent(this, RealizarCompraActivity::class.java)

                realizarCompraIntent.putExtra("usuarioActual", usuarioActual)
                realizarCompraIntent.putExtra("intermediarioElegido", intermediario)
                realizarCompraIntent.putExtra("diaDelEvento", date)
                realizarCompraIntent.putExtra("lugarDelEvento", place)
                realizarCompraIntent.putExtra("horaDelEvento", hour)
                realizarCompraIntent.putExtra("precioDelEvento", price)
                realizarCompraIntent.putExtra("idDelEvento", id)
                realizarCompraIntent.putExtra("asientoDelEvento", asiento)

                startActivity(realizarCompraIntent)

            } else{
                Toast.makeText(this, "Asiento ocupado", Toast.LENGTH_SHORT).show()
            }
        }


    }
}