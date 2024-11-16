package com.electrofire.myapplication2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.electrofire.myapplication2.data.Country
import com.electrofire.myapplication2.databinding.ItemMedalBinding
import com.squareup.picasso.Picasso

class MedalAdapter(private val countrys: List<Country>, private val selectCountryClickLister: (Country) -> Unit)
    : RecyclerView.Adapter<MedalAdapter.CoutrysViewHolder>(){

    class CoutrysViewHolder (val binding: ItemMedalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoutrysViewHolder {

        val countryBinding = ItemMedalBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CoutrysViewHolder(countryBinding)
    }

    override fun onBindViewHolder(holder: CoutrysViewHolder, position: Int) {

        val country = countrys[position]

        Picasso.get()
            .load(country.flag)
            .into(holder.binding.imageViewBandera)

        holder.binding.tvPosition.text = country.position.toString()
        holder.binding.tvCountry.text = country.name
        holder.binding.tvOro.text = country.goldMedals.toString()
        holder.binding.tvPlate.text = country.silverMedals.toString()
        holder.binding.tvBronce.text = country.bronzeMedals.toString()


        holder.binding.cardMedal.setOnClickListener{
            selectCountryClickLister(country)
        }
    }

    override fun getItemCount(): Int {
        return countrys.size
    }



}