package com.dannyjung.githubapi.data.mapper

import com.dannyjung.githubapi.data.model.StarCount

object StarCountMapper {

    fun mapperToStarCounts(
        starCounts: List<StarCount>
    ): List<com.dannyjung.githubapi.domain.model.StarCount> =
        starCounts.map {
            com.dannyjung.githubapi.domain.model.StarCount(
                id = it.id,
                stargazersCount = it.stargazersCount
            )
        }
}
