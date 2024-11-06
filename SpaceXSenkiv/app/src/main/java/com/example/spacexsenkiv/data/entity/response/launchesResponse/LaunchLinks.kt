package com.example.spacexsenkiv.data.entity.response.launchesResponse

import androidx.room.Embedded

data class LaunchLinks(
    @Embedded val patch: LaunchPatch?,
    @Embedded val flickr: LaunchFlickr?
)