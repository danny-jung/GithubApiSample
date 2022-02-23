package com.dannyjung.githubapi.data.mapper

import com.dannyjung.githubapi.data.model.StarCountResponse
import com.dannyjung.githubapi.domain.model.StarCount

object StarCountMapper {

    fun mapperToStarCounts(starCounts: List<StarCountResponse>): List<StarCount> =
        starCounts.map {
            StarCount(
                id = it.id,
                stargazersCount = it.stargazersCount
            )
        }
}
