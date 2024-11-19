package com.electrofire.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.electrofire.myapplication2.adapter.EventAdapter
import com.electrofire.myapplication2.data.Event
import com.electrofire.myapplication2.data.User
import com.electrofire.myapplication2.databinding.ActivityChoiseBinding
import com.electrofire.myapplication2.databinding.ActivityMainBinding
import com.electrofire.myapplication2.repositories.EventRepository

class choiseEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChoiseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChoiseBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){

        val selectEventClickLister = { event: Event ->
            val asiento = intent.getStringExtra("asientoDelEvento")
            val intermediarioOpcionElegida = intent.getIntExtra("selectedIntermediary", -1)
            val usuarioActual = intent.getSerializableExtra("usuarioActual") as? User

            Toast.makeText(this, "Seleccionaste al evento en ${event.place}, el dia ${event.date}", Toast.LENGTH_SHORT).show()

            val asientoDisponibleIntent = Intent(this, asientoActivity::class.java)

            asientoDisponibleIntent.putExtra("usuarioActual", usuarioActual)
            asientoDisponibleIntent.putExtra("selectedIntermediary", intermediarioOpcionElegida)
            asientoDisponibleIntent.putExtra("asientoDelEvento", asiento)
            asientoDisponibleIntent.putExtra("idDelEvento", event.id)

            startActivity(asientoDisponibleIntent)
        }

        binding.recyclerView.adapter = EventAdapter(EventRepository.get(), selectEventClickLister)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }


}