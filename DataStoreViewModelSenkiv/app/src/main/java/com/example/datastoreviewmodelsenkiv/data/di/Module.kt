package com.example.datastoreviewmodelsenkiv.data.di


import android.content.Context
import androidx.room.Room
import com.example.datastoreviewmodelsenkiv.data.db.IntoDataBase
import com.example.datastoreviewmodelsenkiv.ui.screens.subjectsDetails.SubjectsDetailsViewModel
import com.example.datastoreviewmodelsenkiv.ui.screens.subjectsList.SubjectsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<IntoDataBase> {
        Room.databaseBuilder(
            get<Context>(),
            IntoDataBase::class.java, "IntoDataBase"
        ).build()
    }

    viewModel { SubjectsListViewModel(get<IntoDataBase>()) }
    viewModel { SubjectsDetailsViewModel(get<IntoDataBase>()) }
}