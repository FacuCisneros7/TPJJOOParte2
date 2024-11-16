package com.electrofire.myapplication2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.electrofire.myapplication2.adapter.PurchaseAdapter
import com.electrofire.myapplication2.data.Purchase
import com.electrofire.myapplication2.data.User
import com.electrofire.myapplication2.databinding.ActivityHistorialBinding
import com.electrofire.myapplication2.repositories.PurchaseRepository

class HistorialActivity : AppCompatActivity() {

    val purchaseRepo = PurchaseRepository
    private lateinit var binding: ActivityHistorialBinding
    var idUsuario = 0L

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuarioActual = intent.getSerializableExtra("usuarioActual") as User
        idUsuario = usuarioActual.id

        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){

        val selectEventClickLister = { purchase: Purchase ->
            Toast.makeText(this, "Seleccionaste la compra: ${purchase.id}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView2.adapter = PurchaseAdapter(purchaseRepo.getByUserId(idUsuario), selectEventClickLister)
        binding.recyclerView2.layoutManager = LinearLayoutManager(this)

    }
}