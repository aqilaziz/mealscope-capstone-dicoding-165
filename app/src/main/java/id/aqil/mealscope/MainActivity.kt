package id.aqil.mealscope

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.aqil.mealscope.presentation.home.HomeViewModel
import id.aqil.mealscope.presentation.home.MealAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applyEdgeToEdge(findViewById(R.id.activityMainRoot), lightStatusBar = false)

        adapter = MealAdapter { meal ->
            startActivity(DetailActivity.createIntent(this, meal.id))
        }

        val recyclerView: RecyclerView = findViewById(R.id.rvMeals)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val errorContainer: LinearLayout = findViewById(R.id.errorContainer)
        val messageText: TextView = findViewById(R.id.tvMessage)
        val retryButton: Button = findViewById(R.id.btnRetry)
        val favoriteButton: Button = findViewById(R.id.btnFavorite)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        retryButton.setOnClickListener { viewModel.loadMeals() }
        favoriteButton.setOnClickListener { openFavoriteFeature() }

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                adapter.submitList(state.meals)
                val message = state.message
                errorContainer.visibility = if (!state.isLoading && message != null) View.VISIBLE else View.GONE
                messageText.text = message.orEmpty()
            }
        }
    }

    private fun openFavoriteFeature() {
        val intent = Intent().setClassName(packageName, "id.aqil.mealscope.favorite.FavoriteActivity")
        try {
            startActivity(intent)
        } catch (exception: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.favorite_unavailable), Toast.LENGTH_SHORT).show()
        }
    }
}
