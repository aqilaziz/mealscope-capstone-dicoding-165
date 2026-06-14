package id.aqil.mealscope

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("DEPRECATION")
fun Activity.applyEdgeToEdge(
    root: View,
    lightStatusBar: Boolean,
    lightNavigationBar: Boolean = true
) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = Color.TRANSPARENT
    window.navigationBarColor = Color.TRANSPARENT

    WindowCompat.getInsetsController(window, root).apply {
        isAppearanceLightStatusBars = lightStatusBar
        isAppearanceLightNavigationBars = lightNavigationBar
    }

    val initialLeft = root.paddingLeft
    val initialTop = root.paddingTop
    val initialRight = root.paddingRight
    val initialBottom = root.paddingBottom

    ViewCompat.setOnApplyWindowInsetsListener(root) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.setPadding(
            initialLeft + insets.left,
            initialTop + insets.top,
            initialRight + insets.right,
            initialBottom + insets.bottom
        )
        windowInsets
    }
    ViewCompat.requestApplyInsets(root)
}
