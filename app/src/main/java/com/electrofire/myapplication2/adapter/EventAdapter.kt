package com.electrofire.myapplication2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.electrofire.myapplication2.data.Event
import com.electrofire.myapplication2.databinding.ItemEventBinding

class EventAdapter(private val events: List<Event>, private val selectEventClickLister: (Event) -> Unit)
    : RecyclerView.Adapter<EventAdapter.EventsViewHolder>(){

        class EventsViewHolder (val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {

        val eventBinding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return EventsViewHolder(eventBinding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {

        val event = events[position]

        holder.binding.date.text = event.date
        holder.binding.price.text = event.price.toString()
        holder.binding.hour.text = event.hour
        holder.binding.place.text = event.place

        holder.binding.card.setOnClickListener{
            selectEventClickLister(event)
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }



}