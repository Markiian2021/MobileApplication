package com.example.spacexsenkiv.data.di

import com.example.spacexsenkiv.data.db.IntoDataBase
import com.example.spacexsenkiv.data.entity.DataBase
import com.example.spacexsenkiv.data.entity.ServerModule
import com.example.spacexsenkiv.ui.screens.LaunchViewModel
import com.example.spacexsenkiv.ui.screens.NewsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.spacexdata.com/"

val appModule = module {
    single<ServerModule> {
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder: OkHttpClient.Builder = client.newBuilder().addInterceptor(interceptor)

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
            .create(ServerModule::class.java)
    }

    single { DataBase.getDatabase(androidContext()) }
    single { get<IntoDataBase>().launchResponseDao() }

    viewModel { LaunchViewModel(get(), get()) }
    viewModel { NewsViewModel(get()) }
}
