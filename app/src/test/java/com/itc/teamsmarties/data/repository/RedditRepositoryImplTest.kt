package com.itc.teamsmarties.data.repository

import com.itc.teamsmarties.data.model.crypto.CryptoModel
import com.itc.teamsmarties.data.model.sports.SportsModel
import com.itc.teamsmarties.data.model.technology.TechnologyModel
import com.itc.teamsmarties.data.remote.RedditService
import com.itc.teamsmarties.util.dataMapper.PostDataMapper
import com.itc.teamsmarties.util.dataMapper.TestDataUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class RedditRepositoryImplTest {
    @Mock
    lateinit var redditService: RedditService

    @Mock
    private lateinit var postDataMapper: PostDataMapper

    private lateinit var redditRepository: RedditRepository


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        redditRepository = RedditRepositoryImpl(redditService, postDataMapper)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testCryptoCurrencySubRedditData_EmptyList() = /**/runTest {
        // Arrange
        val emptyCryptoResult = CryptoModel()

        // Mock the repository behavior
        Mockito.`when`(redditService.getCryptoCurrencySubRedditData())
            .thenReturn(emptyCryptoResult)

        val result = redditService.getCryptoCurrencySubRedditData()

        assertEquals(0, result.data?.children!!.size)

    }

    @Test
    fun testSportsSubRedditData_EmptyList() = runTest {
        //Arrange
        val emptySportsResult = SportsModel()
        //Mocking the repository behavior
        Mockito.`when`(redditService.getSportsSubRedditData()).thenReturn(emptySportsResult)
        val result = redditService.getSportsSubRedditData()
        assertEquals(0, result.data?.children?.size)
    }

    @Test
    fun testTechnologySubRedditData_EmptyList() = runTest {
        //Arrange
        val emptyTechnologyResult = TechnologyModel()
        //Mocking the repository behavior
        Mockito.`when`(redditService.getTechnologySubRedditData()).thenReturn(emptyTechnologyResult)
        val result = redditService.getTechnologySubRedditData()
        assertEquals(0, result.data?.children?.size)
    }

    @Test
    fun testCryptoSubredditData_Success() = runTest {
        //Arranging the test data
        val cryptoResult = TestDataUtil.getCryptoData()
        //Mock the repository behavior
        Mockito.`when`(redditService.getCryptoCurrencySubRedditData())
            .thenReturn(cryptoResult)

        val result = redditService.getCryptoCurrencySubRedditData()
        assertEquals(2, result.data?.children?.size)

        // Check the first post
        val firstCryptoPost = result.data?.children?.get(0)
        if (firstCryptoPost != null) {
            assertEquals("Bitcoin reaches all-time high", firstCryptoPost.data?.title)
            assertEquals("r/crypto", firstCryptoPost.data?.subredditNamePrefixed)
            assertEquals(
                "https://example.com/path_to_bitcoin_thumbnail.jpg",
                firstCryptoPost.data?.thumbnail
            )
            assertEquals("CryptoExpert001", firstCryptoPost.data?.author)
            assertEquals(500, firstCryptoPost.data?.ups)
            assertEquals(10, firstCryptoPost.data?.downs)
        }
    }

    @Test
    fun testSportsSubredditData_Success() = runTest {
        //Arranging the test data
        val sportsResult = TestDataUtil.getSportsData()

        //Mocking the repository behavior
        Mockito.`when`(redditService.getSportsSubRedditData()).thenReturn(sportsResult)
        val result = redditService.getSportsSubRedditData()
        assertEquals(2, result.data?.children?.size)

        //checking the first post
        val firstSportsPost = result.data?.children?.get(0)

        if (firstSportsPost != null) {
            assertEquals("Olympics 2023: A Look at Upcoming Stars", firstSportsPost.data?.title)
            assertEquals("r/sports", firstSportsPost.data?.subredditNamePrefixed)
            assertEquals(

                "SportsGuru", firstSportsPost.data?.author
            )
            assertEquals("Olympics", firstSportsPost.data?.linkFlairText)
            assertEquals(120, firstSportsPost.data?.ups)
            assertEquals(5, firstSportsPost.data?.downs)
        }


    }

    @Test
    fun testTechnologySubredditData_Success() = runTest {
        //Arranging the test data
        val technologyResult = TestDataUtil.getTechnologyData()

        //Mocking the repository behavior
        Mockito.`when`(redditService.getTechnologySubRedditData()).thenReturn(technologyResult)
        val result = redditService.getTechnologySubRedditData()
        assertEquals(2, result.data?.children?.size)

        //checking the first post
        val firstTechnologyPost = result.data?.children?.get(0)

        if (firstTechnologyPost != null) {
            assertEquals(
                "The Race to Quantum Supremacy: Breakthroughs and Challenges",
                firstTechnologyPost.data?.title
            )
            assertEquals("r/technology", firstTechnologyPost.data?.subredditNamePrefixed)
            assertEquals(

                "QuantumFanatic123", firstTechnologyPost.data?.author
            )
            assertEquals("NextGen Tech", firstTechnologyPost.data?.linkFlairText)
            assertEquals(15, firstTechnologyPost.data?.ups)
            assertEquals(3, firstTechnologyPost.data?.downs)
        }


    }
}

