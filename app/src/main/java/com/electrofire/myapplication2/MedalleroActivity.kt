package com.electrofire.myapplication2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.electrofire.myapplication2.adapter.MedalAdapter
import com.electrofire.myapplication2.data.Country
import com.electrofire.myapplication2.databinding.ActivityMedalleroBinding
import com.electrofire.myapplication2.repositories.MedalTableRepository

class MedalleroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedalleroBinding
    val medalRepo = MedalTableRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMedalleroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){

        val selectCountryClickLister = { country: Country ->
            Toast.makeText(this, "${country.name}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView3.adapter = MedalAdapter(medalRepo.get(), selectCountryClickLister)
        binding.recyclerView3.layoutManager = LinearLayoutManager(this)

    }


}