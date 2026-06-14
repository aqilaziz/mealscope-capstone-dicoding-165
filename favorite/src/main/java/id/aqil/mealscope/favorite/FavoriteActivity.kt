package id.aqil.mealscope.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.aqil.mealscope.applyEdgeToEdge
import id.aqil.mealscope.favorite.di.favoriteModule
import id.aqil.mealscope.favorite.presentation.FavoriteAdapter
import id.aqil.mealscope.favorite.presentation.FavoriteViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        ensureModuleLoaded()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        applyEdgeToEdge(findViewById(R.id.activityFavoriteRoot), lightStatusBar = false)

        adapter = FavoriteAdapter { favorite ->
            val intent = Intent().setClassName(packageName, "id.aqil.mealscope.DetailActivity")
                .putExtra(EXTRA_MEAL_ID, favorite.id)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.rvFavorites)
        val emptyText: TextView = findViewById(R.id.tvEmpty)
        val backButton: Button = findViewById(R.id.btnBack)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        backButton.setOnClickListener { finish() }

        lifecycleScope.launch {
            viewModel.favorites.collect { favorites ->
                adapter.submitList(favorites)
                emptyText.visibility = if (favorites.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun ensureModuleLoaded() {
        if (!moduleLoaded) {
            loadKoinModules(favoriteModule)
            moduleLoaded = true
        }
    }

    companion object {
        private const val EXTRA_MEAL_ID = "extra_meal_id"
        private var moduleLoaded = false
    }
}
