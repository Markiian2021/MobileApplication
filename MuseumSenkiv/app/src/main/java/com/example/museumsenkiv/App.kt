    package com.example.museumsenkiv

    import android.app.Application
    import com.example.museumsenkiv.data.di.appModule
    import org.koin.android.ext.koin.androidContext
    import org.koin.core.context.startKoin

    class App : Application() {
        override fun onCreate() {
            super.onCreate()
            startKoin {
                androidContext(this@App)
                modules(appModule)
            }
        }
    }
