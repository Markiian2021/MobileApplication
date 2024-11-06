package com.example.spacexsenkiv.data.entity.response.newsResponse

data class SpaceXHistoryResponse(
    val title: String,
    val event_date_utc: String,
    val details: String,
    val links: SpaceXHistoryLink,
)