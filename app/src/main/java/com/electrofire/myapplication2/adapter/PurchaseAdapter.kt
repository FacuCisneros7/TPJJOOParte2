package com.electrofire.myapplication2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.electrofire.myapplication2.data.Purchase
import com.electrofire.myapplication2.databinding.ItemComprasBinding

class PurchaseAdapter(private val purchases: List<Purchase>, private val selectPurchaseClickLister: (Purchase) -> Unit)
    : RecyclerView.Adapter<PurchaseAdapter.PurchasesViewHolder>(){

    class PurchasesViewHolder (val binding: ItemComprasBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasesViewHolder {

        val purchaseBinding = ItemComprasBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PurchasesViewHolder(purchaseBinding)
    }

    override fun onBindViewHolder(holder: PurchasesViewHolder, position: Int) {

        val purchase = purchases[position]

        holder.binding.createdCompra.text = purchase.createdDate
        holder.binding.amountCompra.text = purchase.amount.toString()
        holder.binding.idCompra.text = purchase.id.toString()


        holder.binding.cardCompra.setOnClickListener{
            selectPurchaseClickLister(purchase)
        }
    }

    override fun getItemCount(): Int {
        return purchases.size
    }



}