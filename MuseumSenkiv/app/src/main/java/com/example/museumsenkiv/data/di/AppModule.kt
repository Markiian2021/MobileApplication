package com.example.museumsenkiv.data.di

import com.example.museumsenkiv.data.db.DataBase
import com.example.museumsenkiv.data.db.IntoDataBase
import com.example.museumsenkiv.data.entity.MuseumService
import com.example.museumsenkiv.data.repository.MuseumRepository
import com.example.museumsenkiv.ui.screens.MuseumViewModel
import com.example.museumsenkiv.ui.screens.ThemeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MuseumService::class.java)
    }
    single { DataBase.getDatabase(androidContext()) }
    single { get<IntoDataBase>().museumResponseDao() }
    single {
        MuseumRepository( get(), get() )
    }
    viewModel {MuseumViewModel(get())
    }
    viewModel { ThemeViewModel() }
}
