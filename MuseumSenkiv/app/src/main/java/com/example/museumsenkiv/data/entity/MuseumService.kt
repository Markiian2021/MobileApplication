package com.example.museumsenkiv.data.entity


import com.example.museumsenkiv.data.entity.response.artobjectResponse.ArtObject
import com.example.museumsenkiv.data.entity.response.departmentsResponse.DepartmentData
import com.example.museumsenkiv.data.entity.response.departmentsResponse.DepartmentIDs
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MuseumService {
    @GET("objects/{id}")
    suspend fun getObjectDetails(@Path("id") id: Int): ArtObject

    @GET("search")
    suspend fun searchArt(
        @Query("q") q: String,
        @Query("departmentId") departmentId: Int
    ): DepartmentIDs

    @GET("departments")
    suspend fun getDepartmentsData(): DepartmentData
}







