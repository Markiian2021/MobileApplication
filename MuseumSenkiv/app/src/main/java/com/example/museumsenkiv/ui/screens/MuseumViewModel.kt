package com.example.museumsenkiv.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumsenkiv.data.entity.response.artobjectResponse.ArtObject
import com.example.museumsenkiv.data.entity.response.departmentsResponse.Department
import com.example.museumsenkiv.data.repository.MuseumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MuseumViewModel(
    private val museumRepository: MuseumRepository
) : ViewModel() {

    private val _departmentsState = MutableStateFlow<List<Department>>(emptyList())
    val departmentsState: StateFlow<List<Department>> get() = _departmentsState

    private val _artListState = MutableStateFlow<List<ArtObject>>(emptyList())
    val artListState: StateFlow<List<ArtObject>> get() = _artListState

    private val _artDetailsState = MutableStateFlow<ArtObject?>(null)
    val artDetailsState: StateFlow<ArtObject?> get() = _artDetailsState

    fun fetchDepartments() {
        viewModelScope.launch {
            val departments = museumRepository.getDepartments()
            _departmentsState.value = departments
        }
    }

    fun fetchArtList(query: String, departmentId: Int) {
        viewModelScope.launch {
            try {
                _artListState.value = museumRepository.getArtList(query, departmentId)
            } catch (e: Exception) {
                _artListState.value = emptyList()
            }
        }
    }

    fun fetchArtDetails(objectID: Int) {
        viewModelScope.launch {
            _artDetailsState.value = museumRepository.getArtDetails(objectID)
        }
    }
}
