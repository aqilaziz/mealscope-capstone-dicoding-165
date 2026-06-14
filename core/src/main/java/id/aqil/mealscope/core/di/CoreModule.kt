package id.aqil.mealscope.core.di

import androidx.room.Room
import id.aqil.mealscope.core.data.MealRepository
import id.aqil.mealscope.core.data.local.room.MealDatabase
import id.aqil.mealscope.core.data.remote.network.ApiService
import id.aqil.mealscope.core.domain.repository.IMealRepository
import id.aqil.mealscope.core.domain.usecase.MealInteractor
import id.aqil.mealscope.core.domain.usecase.MealUseCase
import id.aqil.mealscope.core.security.DatabasePassphraseProvider
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        CertificatePinner.Builder()
            .add(
                "www.themealdb.com",
                "sha256/ITk4HFdg55ARmrhfYeL9UgfuymNk/yCN8I+AUOqjwaQ=",
                "sha256/kIdp6NNEd8wsugYyyIYFsi1ylMCED3hZbSR8ZFsa/A4=",
            )
            .build()
    }
    single {
        OkHttpClient.Builder()
            .certificatePinner(get())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MealDatabase::class.java,
            "mealscope_secure.db",
        ).openHelperFactory(DatabasePassphraseProvider.createFactory())
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<MealDatabase>().mealDao() }
}

val repositoryModule = module {
    single<IMealRepository> { MealRepository(get(), get()) }
}

val useCaseModule = module {
    factory<MealUseCase> { MealInteractor(get()) }
}
