package com.electrofire.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.electrofire.myapplication2.data.User

class MenuActivity : AppCompatActivity() {

    private lateinit var btn_comprar : ImageButton
    private lateinit var btn_historial : ImageButton
    private lateinit var btn_medallero : ImageButton
    private lateinit var btn_salir : ImageButton
    private lateinit var tv_saldo : TextView
    private lateinit var tv_usuario : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)

        btn_comprar = findViewById(R.id.btn_comprar)
        btn_historial = findViewById(R.id.btn_historial)
        btn_medallero = findViewById(R.id.btn_medallero)
        btn_salir = findViewById(R.id.btn_salir)
        tv_usuario = findViewById(R.id.tv_name)
        tv_saldo = findViewById(R.id.tv_saldo_actual)

        val usuarioActual = intent.getSerializableExtra("usuarioActual") as? User

        tv_usuario.setText(usuarioActual!!.nickName)
        tv_saldo.setText(usuarioActual!!.money.toString())

        btn_comprar.setOnClickListener{

            val menuIntermediarioActivityIntent = Intent(this, MenuIntermediarioActivity::class.java)
            menuIntermediarioActivityIntent.putExtra("usuarioActual", usuarioActual)
            startActivity(menuIntermediarioActivityIntent)

        }

        btn_historial.setOnClickListener {

            val historialActivityIntent = Intent(this, HistorialActivity::class.java)
            historialActivityIntent.putExtra("usuarioActual", usuarioActual)
            startActivity(historialActivityIntent)

        }

        btn_medallero.setOnClickListener {

            val medalleroActivityIntent = Intent(this, MedalleroActivity::class.java)
            startActivity(medalleroActivityIntent)

        }

        btn_salir.setOnClickListener {
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        }

    }


}