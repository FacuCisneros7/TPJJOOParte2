package com.electrofire.myapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.electrofire.myapplication2.repositories.UserRepository

class MainActivity : AppCompatActivity() {

    private lateinit var btn_login : Button
    private lateinit var et_usuario : EditText
    private lateinit var et_contraseña : EditText
    val userRepo = UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btn_login = findViewById(R.id.btn_login)
        et_usuario = findViewById(R.id.et_usuario)
        et_contraseña = findViewById(R.id.et_password)

        btn_login.setOnClickListener{

            val contraseña = et_contraseña.text.toString()
            val usuario = et_usuario.text.toString()
            var usuarioActual = userRepo.login(usuario, contraseña)

            if(usuarioActual == null){
                Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Datos Correctos", Toast.LENGTH_SHORT).show()

                val menuActivityIntent = Intent(this, MenuActivity::class.java)
                menuActivityIntent.putExtra("usuarioActual", usuarioActual)
                startActivity(menuActivityIntent)

            }

        }

    }



}