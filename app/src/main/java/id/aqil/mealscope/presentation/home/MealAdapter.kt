package id.aqil.mealscope.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.aqil.mealscope.R
import id.aqil.mealscope.presentation.model.MealUi

class MealAdapter(
    private val onClick: (MealUi) -> Unit,
) : ListAdapter<MealUi, MealAdapter.MealViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MealViewHolder(
        itemView: View,
        private val onClick: (MealUi) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imgMeal)
        private val title: TextView = itemView.findViewById(R.id.tvMealName)
        private val subtitle: TextView = itemView.findViewById(R.id.tvMealMeta)

        fun bind(item: MealUi) {
            title.text = item.title
            subtitle.text = item.subtitle
            image.load(item.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.bg_image)
                error(R.drawable.bg_image)
            }
            itemView.setOnClickListener { onClick(item) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MealUi>() {
            override fun areItemsTheSame(oldItem: MealUi, newItem: MealUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MealUi, newItem: MealUi): Boolean {
                return oldItem == newItem
            }
        }
    }
}
