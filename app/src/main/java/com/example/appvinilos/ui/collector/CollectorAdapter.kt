package com.example.appvinilos.ui.collector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appvinilos.R
import com.example.appvinilos.databinding.CollectorItemBinding
import com.example.appvinilos.models.Collector

class CollectorAdapter(private var collectors: List<Collector>) : RecyclerView.Adapter<CollectorAdapter.CollectorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val binding = CollectorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        val collector = collectors[position]
        holder.bind(collector)
        holder.itemView.setOnClickListener {
            val action = CollectorFragmentDirections.actionNavigationCollectorsToCollectorDetailFragment(collector.id)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = collectors.size

    fun updateCollectors(newCollectors: List<Collector>) {
        collectors = newCollectors
        notifyDataSetChanged()
    }

    class CollectorViewHolder(private val binding: CollectorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(collector: Collector) {
            binding.collectorName.text = collector.name
            // Since Collector model has no image, we use a placeholder
            binding.collectorImage.setImageResource(R.drawable.ic_launcher_background)
        }
    }
}