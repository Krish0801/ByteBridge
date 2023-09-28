package com.itc.teamsmarties.util.dataMapper

import com.itc.teamsmarties.data.model.crypto.CryptoModel
import com.itc.teamsmarties.data.model.postsById.Children
import com.itc.teamsmarties.data.model.postsById.Data
import com.itc.teamsmarties.data.model.postsById.DataX
import com.itc.teamsmarties.data.model.postsById.PostsByIdModel
import com.itc.teamsmarties.data.model.postsById.PostsByIdModelItem
import com.itc.teamsmarties.data.model.sports.SportsModel
import com.itc.teamsmarties.data.model.technology.TechnologyModel
import com.itc.teamsmarties.data.model.crypto.ChildrenModel as CryptoChildrenModel
import com.itc.teamsmarties.data.model.crypto.DataModel as CryptoDataModel
import com.itc.teamsmarties.data.model.crypto.DataModelX as CryptoDataModelX
import com.itc.teamsmarties.data.model.sports.ChildrenModel as SportsChildrenModel
import com.itc.teamsmarties.data.model.sports.DataModel as SportsDataModel
import com.itc.teamsmarties.data.model.sports.DataModelX as SportsDataModelX
import com.itc.teamsmarties.data.model.technology.ChildrenModel as TechChildrenModel
import com.itc.teamsmarties.data.model.technology.DataModel as TechDataModel
import com.itc.teamsmarties.data.model.technology.DataModelX as TechDataModelX


object TestDataUtil {
    fun getTechnologyData(): TechnologyModel {
        return TechnologyModel(
            TechDataModel(
                "t3_15j47p5", null, listOf(
                    TechChildrenModel(
                        data = TechDataModelX(
                            title = "The Race to Quantum Supremacy: Breakthroughs and Challenges",
                            subredditNamePrefixed = "r/technology",
                            thumbnail = "https://example.com/path_to_quantum_thumbnail.jpg",
                            preview = null,
                            author = "QuantumFanatic123",
                            ups = 15,
                            downs = 3,
                            linkFlairText = "NextGen Tech",
                            numComments = 150,
                            createdUtc = 1.69145054E9,
                            name = "t3_xyzabc123"
                        )
                    ), TechChildrenModel(
                        data = TechDataModelX(
                            title = "CRISPR and the Future of Personalized Medicine",
                            subredditNamePrefixed = "r/technology",
                            thumbnail = "https://example.com/path_to_crispr_thumbnail.jpg",
                            preview = null,
                            author = "BioTechEnthusiast42",
                            ups = 25,
                            downs = 2,
                            linkFlairText = "Biotech Evolution",
                            numComments = 180,
                            createdUtc = 1.69156054E9,
                            name = "t3_lmnoqrs456"
                        )
                    )
                )
            )
        )
    }

    fun getSportsData(): SportsModel {
        return SportsModel(
            SportsDataModel(
                "t3_sports123", null, listOf(
                    SportsChildrenModel(
                        data = SportsDataModelX(
                            title = "Olympics 2023: A Look at Upcoming Stars",
                            subredditNamePrefixed = "r/sports",
                            thumbnail = "https://example.com/path_to_olympics_thumbnail.jpg",
                            preview = null,
                            author = "SportsGuru",
                            ups = 120,
                            downs = 5,
                            linkFlairText = "Olympics",
                            numComments = 200,
                            createdUtc = 1.69165054E9,
                            name = "t3_sportsabc123"
                        )
                    ), SportsChildrenModel(
                        data = SportsDataModelX(
                            title = "Football: Top Teams This Season",
                            subredditNamePrefixed = "r/sports",
                            thumbnail = "https://example.com/path_to_football_thumbnail.jpg",
                            preview = null,
                            author = "FootballFan99",
                            ups = 90,
                            downs = 3,
                            linkFlairText = "Football Fever",
                            numComments = 170,
                            createdUtc = 1.69175054E9,
                            name = "t3_sportsdef456"
                        )
                    )
                )
            )
        )
    }

    fun getCryptoData(): CryptoModel {
        return CryptoModel(
            CryptoDataModel(
                "t3_crypto123", null, listOf(
                    CryptoChildrenModel(
                        data = CryptoDataModelX(
                            title = "Bitcoin reaches all-time high",
                            subredditNamePrefixed = "r/crypto",
                            thumbnail = "https://example.com/path_to_bitcoin_thumbnail.jpg",
                            author = "CryptoExpert001",
                            ups = 500,
                            downs = 10,
                            linkFlairText = "Bitcoin News",
                            numComments = 1000,
                            createdUtc = 1.69165055E9,
                            name = "t3_cryptoabc123"
                        )
                    ), CryptoChildrenModel(
                        data = CryptoDataModelX(
                            title = "Ethereum's new update and its implications",
                            subredditNamePrefixed = "r/crypto",
                            thumbnail = "https://example.com/path_to_ethereum_thumbnail.jpg",
                            author = "EtherMaster52",
                            ups = 300,
                            downs = 5,
                            linkFlairText = "Ethereum Updates",
                            numComments = 700,
                            createdUtc = 1.69165056E9,
                            name = "t3_cryptodef456"
                        )
                    )
                )
            )
        )
    }

    fun getPostsByIdData(): PostsByIdModel {
        val post1 = PostsByIdModelItem(
            data = Data(
                after = "",
                before = "",
                children = listOf(
                    Children(
                        data = DataX(
                            title = "Deep Dive into Kotlin Coroutines",
                            subredditNamePrefixed = "r/Kotlin",
                            thumbnail = "https://example.com/path_to_coroutines_thumbnail.jpg",
                            author = "KotlinDevMaster",
                            ups = 250,
                            downs = 5,
                            linkFlairText = "Coroutine Tutorials",
                            numComments = 400,
                            createdUtc = 1.69175057E9,
                            name = "t3_kotlinabc123",
                            allowLiveComments = false
                        ),
                        kind = "t3"
                    )
                ),
                dist = 1,
                geoFilter = "",
                modhash = ""
            ),
            kind = "t3"
        )

        val post2 = PostsByIdModelItem(
            data = Data(
                after = "",
                before = "",
                children = listOf(
                    Children(
                        data = DataX(
                            title = "Understanding Kotlin Flow",
                            subredditNamePrefixed = "r/Kotlin",
                            thumbnail = "https://example.com/path_to_kotlin_flow_thumbnail.jpg",
                            author = "KotlinFlowExpert",
                            ups = 350,
                            downs = 7,
                            linkFlairText = "Flow in Kotlin",
                            numComments = 500,
                            createdUtc = 1.69185057E9,
                            name = "t3_kotlinxyz456"
                        ),
                        kind = "t3"
                    )
                ),
                dist = 1,
                geoFilter = "",
                modhash = ""
            ),
            kind = "t3"
        )

// Create an instance of PostsByIdModel
        val postsByIdModel = PostsByIdModel().apply {
            add(post1)
            add(post2)
            // Add more items as needed
        }
        // Return the PostsByIdModel with the posts list
        return postsByIdModel
    }


}
