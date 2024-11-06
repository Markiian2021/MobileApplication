package com.example.spacexsenkiv.ui.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexsenkiv.data.entity.ServerModule
import com.example.spacexsenkiv.data.entity.response.newsResponse.SpaceXHistoryResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val serverModule: ServerModule) : ViewModel() {

    private val _articles = MutableStateFlow<List<SpaceXHistoryResponse>>(emptyList())
    val articles: StateFlow<List<SpaceXHistoryResponse>> = _articles.asStateFlow()

    init {
        viewModelScope.launch {
            val history = serverModule.getSpaceXHistory()
            _articles.value = history
        }
    }
}





