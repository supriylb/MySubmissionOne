package com.example.mysubmissionone

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysubmissionone.databinding.ItemListFoodBinding

class FoodAdapter(private val listFood: ArrayList<Food>) :
    RecyclerView.Adapter<FoodAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemListFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listFood[position])
    }

    inner class ListViewHolder(private val binding: ItemListFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            with(binding) {
                sivImage.setImageResource(food.image)
                tvName.text = food.name
                tvDescription.text = food.description

                actionShare.setOnClickListener {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT, "${food.name}\n${food.location}\n${food.description}"
                        )
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    root.context.startActivity(shareIntent)
                }

                itemView.setOnClickListener {
                    val detailActivity = Intent(root.context, DetailActivity::class.java)
                    detailActivity.putExtra(DetailActivity.EXTRA_FOOD, food)
                    root.context.startActivity(detailActivity)
                }
            }
        }
    }
}
