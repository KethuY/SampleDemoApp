package com.example.sampledemoapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampledemoapp.R
import com.example.sampledemoapp.uidatamodel.DemoNutUIModel


internal class DemoNutsAdapter(
    private val context: Context,
    private val demoNutUIModels: List<DemoNutUIModel>,
    private val onItemClick: ((demoNut: DemoNutUIModel) -> Unit)? = null
) : RecyclerView.Adapter<DemoNutsAdapter.DemoNutsViewHolder>() {

    class DemoNutsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.findViewById(R.id.demoNutName)
        val image: AppCompatImageView = itemView.findViewById(R.id.demoNutImage)
        val desc: AppCompatTextView = itemView.findViewById(R.id.demoNutDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoNutsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_demo_nut, parent, false)
        return DemoNutsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DemoNutsViewHolder, position: Int) {
        val demoNut = demoNutUIModels[position]
        holder.name.text = demoNut.name
        holder.desc.text = demoNut.desc
        Glide.with(context).load(demoNut.imgURL).placeholder(R.drawable.ic_launcher_background).into(holder.image);
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(demoNut)
        }
    }

    override fun getItemCount(): Int = demoNutUIModels.size
}