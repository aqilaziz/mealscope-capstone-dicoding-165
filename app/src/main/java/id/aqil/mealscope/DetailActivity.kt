package id.aqil.mealscope

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import id.aqil.mealscope.presentation.detail.DetailViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val mealId = intent.getStringExtra(EXTRA_MEAL_ID).orEmpty()
        val image: ImageView = findViewById(R.id.imgMeal)
        val title: TextView = findViewById(R.id.tvMealName)
        val meta: TextView = findViewById(R.id.tvMealMeta)
        val instructions: TextView = findViewById(R.id.tvInstructions)
        val favoriteButton: Button = findViewById(R.id.btnFavorite)
        val backButton: Button = findViewById(R.id.btnBack)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        backButton.setOnClickListener { finish() }
        favoriteButton.setOnClickListener { viewModel.toggleFavorite() }

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                favoriteButton.text = if (state.isFavorite) {
                    getString(R.string.remove_favorite)
                } else {
                    getString(R.string.add_favorite)
                }

                val meal = state.mealUi
                title.text = meal?.title.orEmpty()
                meta.text = meal?.subtitle.orEmpty()
                instructions.text = state.message ?: meal?.description.orEmpty()
                image.load(meal?.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.bg_image)
                    error(R.drawable.bg_image)
                }
            }
        }

        viewModel.loadMeal(mealId)
    }

    companion object {
        private const val EXTRA_MEAL_ID = "extra_meal_id"

        fun createIntent(context: Context, mealId: String): Intent {
            return Intent(context, DetailActivity::class.java).putExtra(EXTRA_MEAL_ID, mealId)
        }
    }
}
