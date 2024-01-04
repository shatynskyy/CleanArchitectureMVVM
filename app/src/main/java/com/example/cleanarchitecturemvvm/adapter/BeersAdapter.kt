package com.example.cleanarchitecturemvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleanarchitecturemvvm.R
import com.example.cleanarchitecturemvvm.databinding.ItemBeerBinding
import com.example.cleanarchitecturemvvm.models.BeerModelPresentation

class BeersAdapter(private val listener: (BeerModelPresentation) -> Unit) :
    RecyclerView.Adapter<BeersAdapter.MainHolder>() {

    private var listBeer = listOf<BeerModelPresentation>()

    class MainHolder(val binding: ItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.binding.apply {
            val context = holder.itemView.context
            title.text = listBeer[position].name
            abv.text = context.getString(R.string.abv_percent, listBeer[position].abv)
            firstBrewed.text = listBeer[position].firstBrewed
            Glide.with(context)
                .load(listBeer[position].image)
                .placeholder(R.drawable.default_beer)
                .error(R.drawable.default_beer)
                .into(image)
            removeBeerBtn.setOnClickListener {
                listener.invoke(listBeer[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = listBeer.size

    fun updateList(newList: List<BeerModelPresentation>) {
        val diffResult = DiffUtil.calculateDiff(BeerDiffCallback(listBeer, newList))
        listBeer = newList
        diffResult.dispatchUpdatesTo(this)
    }

    private class BeerDiffCallback(
        private val oldList: List<BeerModelPresentation>,
        private val newList: List<BeerModelPresentation>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}