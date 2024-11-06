package com.example.spacexsenkiv.data.entity

import androidx.room.TypeConverter
import com.example.spacexsenkiv.data.entity.response.launchesResponse.LaunchCores
import com.example.spacexsenkiv.data.entity.response.launchesResponse.LaunchLinks
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCoresList(cores: List<LaunchCores>): String {
        return gson.toJson(cores)
    }

    @TypeConverter
    fun toCoresList(data: String): List<LaunchCores> {
        val listType = object : TypeToken<List<LaunchCores>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromLaunchLinks(links: LaunchLinks?): String? {
        return links?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toLaunchLinks(data: String?): LaunchLinks? {
        return data?.let { Gson().fromJson(it, LaunchLinks::class.java) }
    }

}
