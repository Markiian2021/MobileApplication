package com.example.spacexsenkiv.ui.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexsenkiv.data.dao.LaunchResponseDao
import com.example.spacexsenkiv.data.entity.ServerModule
import com.example.spacexsenkiv.data.entity.response.launchesResponse.LaunchResponse
import com.example.spacexsenkiv.data.entity.response.rocketsResponse.RocketResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class LaunchViewModel(
    private val serverModule: ServerModule,
    private val launchResponseDao: LaunchResponseDao
) : ViewModel() {

    private val _launchResponseStateFlow = MutableStateFlow<List<LaunchResponse>?>(null)
    val launchResponseStateFlow: StateFlow<List<LaunchResponse>?>
        get() = _launchResponseStateFlow

    private val _rocketDetailStateFlow = MutableStateFlow<List<RocketResponse>?>(null)
    val rocketDetailStateFlow: StateFlow<List<RocketResponse>?>
        get() = _rocketDetailStateFlow

    init {
        viewModelScope.launch {

            val launchResponse = serverModule.getAllLaunches()

            val launchWithNonNullFields = launchResponse.map { launch ->
                launch.copy(
                    name = launch.name ?: "Unknown",
                    rocket = launch.rocket ?: "Unknown"
                )
            }

            _launchResponseStateFlow.value = launchWithNonNullFields

            launchWithNonNullFields.forEach { launch ->
                launchResponseDao.insertLaunch(launch)
            }

            val rocketResponses = launchWithNonNullFields.mapNotNull { launch ->
                val rocketId = launch.rocket
                serverModule.getRocket(rocketId)
            }
            _rocketDetailStateFlow.value = rocketResponses
        }
    }

    suspend fun searchLaunch(query: String): List<LaunchResponse> {
        return launchResponseDao.searchLaunch("%$query%")
    }


    fun deleteLaunch(launchId: String) {
        viewModelScope.launch {
            launchResponseDao.deleteLaunchById(launchId)
            _launchResponseStateFlow.value = _launchResponseStateFlow.value?.filter { it.id != launchId }
        }
    }

}
