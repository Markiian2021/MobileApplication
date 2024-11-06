package com.example.spacexsenkiv.data.entity


import com.example.spacexsenkiv.data.entity.response.launchesResponse.LaunchResponse
import com.example.spacexsenkiv.data.entity.response.newsResponse.SpaceXHistoryResponse
import com.example.spacexsenkiv.data.entity.response.rocketsResponse.RocketResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerModule {
    @GET("v4/launches")
    suspend fun getAllLaunches(): List<LaunchResponse>

    @GET("v4/rockets/{id}")
    suspend fun getRocket(
        @Path("id") id: String
    ): RocketResponse

    @GET("v4/history")
    suspend fun getSpaceXHistory(): List<SpaceXHistoryResponse>

}
