package com.electrofire.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import repositories.UserRepository

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
               Toast.makeText(this,"Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Datos correctos", Toast.LENGTH_LONG).show()
            }

        }

    }



}