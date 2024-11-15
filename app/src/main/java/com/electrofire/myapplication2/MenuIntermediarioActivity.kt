package com.electrofire.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.electrofire.myapplication2.data.User

class MenuIntermediarioActivity : AppCompatActivity() {

    private lateinit var btn_elite : ImageButton
    private lateinit var btn_ticketpro : ImageButton
    private lateinit var btn_ultimate : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu_intermediario)

        val usuarioActual = intent.getSerializableExtra("usuarioActual") as? User

        btn_elite = findViewById(R.id.btn_elite)
        btn_ticketpro = findViewById(R.id.btn_ticketpro)
        btn_ultimate = findViewById(R.id.btn_ultimate)


        btn_elite.setOnClickListener{
            val eliteActivityIntent = Intent(this, choiseEventActivity::class.java)
            eliteActivityIntent.putExtra("selectedIntermediary", 1)
            eliteActivityIntent.putExtra("usuarioActual", usuarioActual)
            startActivity(eliteActivityIntent)

        }

        btn_ticketpro.setOnClickListener {
            val ticketProActivityIntent = Intent(this, choiseEventActivity::class.java)
            ticketProActivityIntent.putExtra("selectedIntermediary", 0)
            ticketProActivityIntent.putExtra("usuarioActual", usuarioActual)
            startActivity(ticketProActivityIntent)

        }

        btn_ultimate.setOnClickListener {
            val ultimateActivityIntent = Intent(this, choiseEventActivity::class.java)
            ultimateActivityIntent.putExtra("selectedIntermediary", 2)
            ultimateActivityIntent.putExtra("usuarioActual", usuarioActual)
            startActivity(ultimateActivityIntent)

        }




    }
}