package com.itc.teamsmarties.util.dataMapper

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PostDataMapperImplTest {

    private lateinit var postDataMapper: PostDataMapperImpl

    @Before
    fun setUp() {
        postDataMapper = PostDataMapperImpl()
    }

    @Test
    fun `mapTechnologyData returns correct PostData`() {
        val mockResponse = TestDataUtil.getTechnologyData()
        val result = postDataMapper.mapTechnologyData(mockResponse)
        assertEquals(2, result.size)

        // Check the first post
        val firstPost = result[0]
        assertEquals("The Race to Quantum Supremacy: Breakthroughs and Challenges", firstPost.title)
        assertEquals("r/technology", firstPost.subredditNamePrefixed)
        assertEquals("https://example.com/path_to_quantum_thumbnail.jpg", firstPost.thumbnail)
        assertEquals("QuantumFanatic123", firstPost.author)
        assertEquals(15, firstPost.ups)
        assertEquals(3, firstPost.downs)
        assertEquals("NextGen Tech", firstPost.link_flair_text)
        assertEquals(150, firstPost.numComments)
        assertEquals(1.69145054E9, firstPost.created_utc)


    }

    @Test
    fun `mapSportsData returns correct PostData`() {
        val mockResponse = TestDataUtil.getSportsData()
        val result = postDataMapper.mapSportsData(mockResponse)
        assertEquals(2, result.size)

        // Check the first post
        val firstPost = result[0]
        assertEquals("Olympics 2023: A Look at Upcoming Stars", firstPost.title)
        assertEquals("r/sports", firstPost.subredditNamePrefixed)
        assertEquals("https://example.com/path_to_olympics_thumbnail.jpg", firstPost.thumbnail)
        assertEquals("SportsGuru", firstPost.author)
        assertEquals(120, firstPost.ups)
        assertEquals(5, firstPost.downs)
        assertEquals("Olympics", firstPost.link_flair_text)
        assertEquals(200, firstPost.numComments)
        assertEquals(1.69165054E9, firstPost.created_utc)

        // Similarly, you can add assertions for other fields and the second post.
    }

    @Test
    fun `mapCryptoData returns correct PostData`() {
        val mockResponse = TestDataUtil.getCryptoData()
        val result = postDataMapper.mapCryptoData(mockResponse)
        assertEquals(2, result.size)

        // Check the first post
        val firstPost = result[0]
        assertEquals("Bitcoin reaches all-time high", firstPost.title)
        assertEquals("r/crypto", firstPost.subredditNamePrefixed)
        assertEquals("https://example.com/path_to_bitcoin_thumbnail.jpg", firstPost.thumbnail)
        assertEquals("CryptoExpert001", firstPost.author)
        assertEquals(500, firstPost.ups)
        assertEquals(10, firstPost.downs)
        assertEquals("Bitcoin News", firstPost.link_flair_text)
        assertEquals(1000, firstPost.numComments)
        assertEquals(1.69165055E9, firstPost.created_utc)
    }

    @Test
    fun `mapPostByIdData returns correct PostData`() {
        val mockResponse = TestDataUtil.getPostsByIdData()
        val result = postDataMapper.mapPostsByIdData(mockResponse)
        assertEquals(1, result.size)

        // Check the first post's details
        val firstPostDetails = result[0]
        assertEquals("Deep Dive into Kotlin Coroutines", firstPostDetails.title)
        assertEquals("r/Kotlin", firstPostDetails.subredditNamePrefixed)
        assertEquals(
            "https://example.com/path_to_coroutines_thumbnail.jpg", firstPostDetails.thumbnail
        )
        assertEquals("KotlinDevMaster", firstPostDetails.author)
        assertEquals(250, firstPostDetails.ups)
        assertEquals(5, firstPostDetails.downs)
        assertEquals("Coroutine Tutorials", firstPostDetails.link_flair_text)
        assertEquals(400, firstPostDetails.numComments)
        assertEquals(1.69175057E9, firstPostDetails.created_utc)


    }

}