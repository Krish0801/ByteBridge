package com.itc.teamsmarties.util.dataMapper


import com.itc.teamsmarties.data.model.food.FoodModel
import com.itc.teamsmarties.data.model.postsById.PostsByIdModel
import com.itc.teamsmarties.data.model.sports.SportsModel
import com.itc.teamsmarties.data.model.technology.TechnologyModel


class PostDataMapperImpl : PostDataMapper {
    override fun mapTechnologyData(technologyResponse: TechnologyModel): List<PostData> {
        val technologyData = ArrayList<PostData>()

        for ((data, _) in technologyResponse.data?.children!!) {
            if (data != null) {
                technologyData.add(
                    PostData(
                        subredditNamePrefixed = data.subredditNamePrefixed,
                        title = data.title,
                        thumbnail = data.thumbnail,
                        preview = data.preview,
                        author = data.author,
                        ups = data.ups,
                        downs = data.downs,
                        link_flair_text = data.linkFlairText,
                        numComments = data.numComments,
                        created_utc = data.createdUtc,
                        name = data.name
                    )
                )
            }

        }
        return technologyData

    }

    override fun mapSportsData(sportsResponse: SportsModel): List<PostData> {
        val sportsData = ArrayList<PostData>()

        for ((data, _) in sportsResponse.data?.children!!) {
            if (data != null) {
                sportsData.add(
                    PostData(
                        subredditNamePrefixed = data.subredditNamePrefixed,
                        title = data.title,
                        thumbnail = data.thumbnail,
                        author = data.author,
                        ups = data.ups,
                        downs = data.downs,
                        link_flair_text = data.linkFlairText,
                        sportsPreview = data.preview,
                        linkFlairRichtext = data.linkFlairRichtext,
                        numComments = data.numComments,
                        created_utc = data.createdUtc,
                        name = data.name


                    )
                )
            }

        }
        return sportsData

    }

    override fun mapFoodData(foodResponse: FoodModel): List<PostData> {
        val foodData = ArrayList<PostData>()

        for ((data, _) in foodResponse.data?.children!!) {
            if (data != null) {
                foodData.add(
                    PostData(
                        subredditNamePrefixed = data.subredditNamePrefixed,
                        title = data.title,
                        thumbnail = data.thumbnail,
                        author = data.author,
                        ups = data.ups,
                        downs = data.downs,
                        link_flair_text = data.linkFlairText,
                        televisionAllAwardings = data.allAwardings,
                        numComments = data.numComments,
                        created_utc = data.createdUtc,
                        name = data.name,
                        urlOverriddenByDest = data.urlOverriddenByDest

                    )
                )
            }

        }
        return foodData

    }

    override fun mapPostsByIdData(postsByIdResponse: PostsByIdModel): List<PostData> {
        val postsByIdData = ArrayList<PostData>()

        for ((data, _) in postsByIdResponse[0].data.children) {
            postsByIdData.add(
                PostData(
                    subredditNamePrefixed = data.subredditNamePrefixed,
                    title = data.title,
                    thumbnail = data.thumbnail,
                    author = data.author,
                    ups = data.ups,
                    downs = data.downs,
                    link_flair_text = data.linkFlairText,
                    numComments = data.numComments,
                    created_utc = data.createdUtc,
                    name = data.name,
                    urlOverriddenByDest = data.urlOverriddenByDest


                )
            )

        }
        for ((data, _) in postsByIdResponse[1].data.children) {
            postsByIdData.add(
                PostData(
                    body = data.body,
                    commentsAuthor = data.author
                )
            )
        }
        return postsByIdData
    }
}