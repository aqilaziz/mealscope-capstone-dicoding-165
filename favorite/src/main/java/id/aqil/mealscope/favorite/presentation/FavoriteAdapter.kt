package id.aqil.mealscope.favorite.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.aqil.mealscope.favorite.R

class FavoriteAdapter(
    private val onClick: (FavoriteMealUi) -> Unit,
) : ListAdapter<FavoriteMealUi, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_meal, parent, false)
        return FavoriteViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoriteViewHolder(
        itemView: View,
        private val onClick: (FavoriteMealUi) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imgMeal)
        private val title: TextView = itemView.findViewById(R.id.tvMealName)
        private val subtitle: TextView = itemView.findViewById(R.id.tvMealMeta)

        fun bind(item: FavoriteMealUi) {
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteMealUi>() {
            override fun areItemsTheSame(oldItem: FavoriteMealUi, newItem: FavoriteMealUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteMealUi, newItem: FavoriteMealUi): Boolean {
                return oldItem == newItem
            }
        }
    }
}
