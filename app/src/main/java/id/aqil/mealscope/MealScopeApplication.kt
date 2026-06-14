package id.aqil.mealscope

import android.app.Application
import id.aqil.mealscope.core.di.databaseModule
import id.aqil.mealscope.core.di.networkModule
import id.aqil.mealscope.core.di.repositoryModule
import id.aqil.mealscope.core.di.useCaseModule
import id.aqil.mealscope.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MealScopeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@MealScopeApplication)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                useCaseModule,
                appModule,
            )
        }
    }
}
