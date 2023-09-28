package com.itc.teamsmarties.domain

import com.itc.teamsmarties.util.dataMapper.PostData

object TestDataEndPointsUtil {
    fun getCryptoCurrencySubRedditData(): List<PostData> {
        return listOf(
            PostData(
                title = "Bitcoin Price Surges",
                subredditNamePrefixed = "r/CryptoCurrency",
                thumbnail = "https://example.com/bitcoin_thumbnail.jpg",
                author = "CryptoLover123",
                ups = 1000,
                downs = 10,
                link_flair_text = "Market News",
                numComments = 800,
                created_utc = 1.69165055E9,
                name = "t3_bitcoinqwe123"
            ), PostData(
                title = "Ethereum Hits All-Time High",
                subredditNamePrefixed = "r/CryptoCurrency",
                thumbnail = "https://example.com/ethereum_thumbnail.jpg",
                author = "CryptoEnthusiast456",
                ups = 800,
                downs = 20,
                link_flair_text = "Altcoin Updates",
                numComments = 600,
                created_utc = 1.69165055E9,
                name = "t3_ethereumabc456"
            )
        )

    }

    fun getSportsSubRedditData(): List<PostData> {
        return listOf(
            PostData(
                title = "NBA Finals: Exciting Matchup",
                subredditNamePrefixed = "r/sports",
                thumbnail = "https://example.com/nba_thumbnail.jpg",
                author = "SportsFanatic789",
                ups = 1200,
                downs = 5,
                link_flair_text = "Basketball News",
                numComments = 1000,
                created_utc = 1.69165055E9,
                name = "t3_nba123"
            ),
            PostData(
                title = "Soccer World Cup Quarterfinals",
                subredditNamePrefixed = "r/sports",
                thumbnail = "https://example.com/soccer_thumbnail.jpg",
                author = "SoccerFan123",
                ups = 900,
                downs = 15,
                link_flair_text = "Football Updates",
                numComments = 800,
                created_utc = 1.69165055E9,
                name = "t3_soccer456"
            )
        )
    }

    fun getTechnologySubRedditData(): List<PostData> {
        return listOf(
            PostData(
                title = "New Tech Gadgets Unveiled",
                subredditNamePrefixed = "r/technology",
                thumbnail = "https://example.com/tech_thumbnail.jpg",
                author = "TechEnthusiast789",
                ups = 1500,
                downs = 8,
                link_flair_text = "Tech News",
                numComments = 1200,
                created_utc = 1.69165055E9,
                name = "t3_tech123"
            ),
            PostData(
                title = "AI and Machine Learning Advancements",
                subredditNamePrefixed = "r/technology",
                thumbnail = "https://example.com/ai_thumbnail.jpg",
                author = "AIResearcher456",
                ups = 1100,
                downs = 20,
                link_flair_text = "AI and ML Updates",
                numComments = 900,
                created_utc = 1.69165055E9,
                name = "t3_ai456"
            )
        )
    }

    fun getHomeSubRedditData(): List<PostData> {
        return listOf(
            PostData(
                title = "Exciting New Discoveries in Science",
                subredditNamePrefixed = "r/science",
                thumbnail = "https://example.com/science_thumbnail.jpg",
                author = "ScienceEnthusiast123",
                ups = 2500,
                downs = 15,
                link_flair_text = "Scientific Breakthroughs",
                numComments = 1800,
                created_utc = 1.77934567E9,
                name = "t3_science789"
            ),

            PostData(
                title = "Exploring the Future of Space Travel",
                subredditNamePrefixed = "r/space",
                thumbnail = "https://example.com/space_thumbnail.jpg",
                author = "SpaceExplorer789",
                ups = 1800,
                downs = 30,
                link_flair_text = "Space Exploration",
                numComments = 1500,
                created_utc = 1.77934567E9,
                name = "t3_space567"
            )

        )
    }

}