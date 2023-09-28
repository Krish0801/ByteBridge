package com.itc.teamsmarties.domain


import androidx.lifecycle.SavedStateHandle
import com.itc.teamsmarties.data.repository.RedditRepository
import com.itc.teamsmarties.data.repository.RedditRepository.RepositoryCryptoResult
import com.itc.teamsmarties.util.dataMapper.PostData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class EndPointsViewModelTest {

    @Mock
    private lateinit var repository: RedditRepository

    private lateinit var viewModel: EndPointsViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = EndPointsViewModel(repository, SavedStateHandle(), testDispatcher)
    }

    @Test
    fun testGetCryptoCurrencySubRedditData_emptyResponse() = runTest {
        // Arrange
        val emptyCryptoResult = RepositoryCryptoResult.Success(
            posts = emptyList()
        )
//
//        // Mock the repository behavior
        Mockito.`when`(repository.getCryptoCurrencySubRedditData()).thenReturn(emptyCryptoResult)

//        // Call the function
        viewModel.getCryptoCurrencySubRedditData()
//        // Call the function that uses the repository to get the products
//
        testDispatcher.scheduler.advanceUntilIdle()
//        // Check the state change
        val currentState = viewModel.state.value
        assertTrue(currentState is ViewModelState.Success) // Check if the state is Success
        assertEquals(
            emptyList<PostData>(),
            (currentState as ViewModelState.Success).data
        ) // Check if the data is empty
//
        assertEquals(0, currentState.data.size)
    }

    //
    @Test
    fun `getCryptoCurrencySubRedditData with valid response`() = runTest {
        // Arrange
        val cryptoResult = RepositoryCryptoResult.Success(
            posts = TestDataEndPointsUtil.getCryptoCurrencySubRedditData()
        )

        // Mock the repository behavior
        Mockito.`when`(repository.getCryptoCurrencySubRedditData()).thenReturn(cryptoResult)

        // Call the function
        viewModel.getCryptoCurrencySubRedditData()
        // Call the function that uses the repository to get the data
//
        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Check the state change
        val currentState = viewModel.state.value
        val cryptoResponse = (currentState as ViewModelState.Success)
        assertTrue(currentState is ViewModelState.Success) // Check if the state is Success
        assertEquals(
            cryptoResult.posts,
            cryptoResponse.data
        ) // Check if the data matches cryptoResult.posts

        assertEquals(2, cryptoResponse.data.size)
//
//        /// Check the first post
        val firstPost = cryptoResponse.data[0]
        assertEquals("Bitcoin Price Surges", firstPost.title)
        assertEquals("r/CryptoCurrency", firstPost.subredditNamePrefixed)
        assertEquals("https://example.com/bitcoin_thumbnail.jpg", firstPost.thumbnail)
        assertEquals("CryptoLover123", firstPost.author)
        assertEquals(1000, firstPost.ups)
        assertEquals(10, firstPost.downs)
        assertEquals("Market News", firstPost.link_flair_text)
        assertEquals(800, firstPost.numComments)
        assertEquals(1.69165055E9, firstPost.created_utc)
    }

    //
    @Test
    fun testGetSportsSubRedditData_emptyResponse() = runTest {
        // Arrange
        val emptySportsResult = RedditRepository.RepositorySportsResult.Success(
            posts = emptyList()
        )

        // Mock the repository behavior
        Mockito.`when`(repository.getSportsSubRedditData()).thenReturn(emptySportsResult)

        // Call the function
        viewModel.getSportsSubRedditData()

        // Advance the dispatcher to process the coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        // Check the state change
        val currentState = viewModel.state.value
        assertTrue(currentState is ViewModelState.Success) // Check if the state is Success
        assertEquals(
            emptyList<PostData>(),
            (currentState as ViewModelState.Success).data
        ) // Check if the data is empty

        assertEquals(0, currentState.data.size)
    }

    //
    @Test
    fun `getSportsSubRedditData with valid response`() = runTest {
        // Arrange
        val sportsResult = RedditRepository.RepositorySportsResult.Success(
            posts = TestDataEndPointsUtil.getSportsSubRedditData()
        )

        // Mock the repository behavior
        Mockito.`when`(repository.getSportsSubRedditData(null))
            .thenReturn(sportsResult) // Pass null for the 'after' parameter

        // Call the function
        viewModel.getSportsSubRedditData()

        // Advance the dispatcher to process the coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        // Check the state change
        val currentState = viewModel.state.value
        val sportsResponse = (currentState as ViewModelState.Success)
        assertTrue(currentState is ViewModelState.Success) // Check if the state is Success
        assertEquals(
            sportsResult.posts,
            sportsResponse.data
        ) // Check if the data matches sportsResult.posts

        assertEquals(2, sportsResponse.data.size)

        // Check the first post
        val firstPost = sportsResponse.data[0]
        assertEquals("NBA Finals: Exciting Matchup", firstPost.title)
        assertEquals("r/sports", firstPost.subredditNamePrefixed)
        assertEquals("https://example.com/nba_thumbnail.jpg", firstPost.thumbnail)
        assertEquals("SportsFanatic789", firstPost.author)
        assertEquals(1200, firstPost.ups)
        assertEquals(5, firstPost.downs)
        assertEquals("Basketball News", firstPost.link_flair_text)
        assertEquals(1000, firstPost.numComments)
        assertEquals(1.69165055E9, firstPost.created_utc)
    }

    //
    @Test
    fun testGetTechnologySubRedditData_emptyResponse() = runTest {
        // Arrange
        val emptyTechnologyResult = RedditRepository.RepositoryTechnologyResult.Success(
            posts = emptyList()
        )

        // Mock the repository behavior
        Mockito.`when`(repository.getTechnologySubRedditData(null))
            .thenReturn(emptyTechnologyResult) // Pass null for the 'after' parameter

        // Call the function
        viewModel.getTechnologySubRedditData()

        // Advance the dispatcher to process the coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        // Check the state change
        val currentState = viewModel.state.value
        assertTrue(currentState is ViewModelState.Success) // Check if the state is Success
        assertEquals(
            emptyList<PostData>(),
            (currentState as ViewModelState.Success).data
        ) // Check if the data is empty

        assertEquals(0, currentState.data.size)
    }

    //
//
    @Test
    fun `getTechnologySubRedditData with valid response`() = runTest {
        // Arrange
        val technologyResult = RedditRepository.RepositoryTechnologyResult.Success(
            posts = TestDataEndPointsUtil.getTechnologySubRedditData()
        )

        // Mock the repository behavior
        Mockito.`when`(repository.getTechnologySubRedditData()).thenReturn(technologyResult)

        // Call the function
        viewModel.getTechnologySubRedditData()

        testDispatcher.scheduler.advanceUntilIdle()

        // Check the state change
        val currentState = viewModel.state.value
        val technologyResponse = (currentState as ViewModelState.Success)
        assertTrue(currentState is ViewModelState.Success) // Check if the state is Success
        assertEquals(
            technologyResult.posts,
            technologyResponse.data
        ) // Check if the data matches technologyResult.posts

        assertEquals(2, technologyResponse.data.size)

        // Check the first post
        val firstPost = technologyResponse.data[0]
        assertEquals("New Tech Gadgets Unveiled", firstPost.title)
        assertEquals("r/technology", firstPost.subredditNamePrefixed)
        assertEquals("https://example.com/tech_thumbnail.jpg", firstPost.thumbnail)
        assertEquals("TechEnthusiast789", firstPost.author)
        assertEquals(1500, firstPost.ups)
        assertEquals(8, firstPost.downs)
        assertEquals("Tech News", firstPost.link_flair_text)
        assertEquals(1200, firstPost.numComments)
        assertEquals(1.69165055E9, firstPost.created_utc)
    }

    @Test
    fun testGetHomeSubRedditData_emptyResponse() = runTest {
        // Arrange
        val emptyScienceResult = HomeScreenData.Success(
            listOfPosts = emptyList()
        )

        // Mock the repository behavior
        Mockito.`when`(repository.getCombinedData()).thenReturn(emptyScienceResult)

        // Call the function
        viewModel.getHomeScreenData()

        // Advance the dispatcher to process the coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        // Check the state change
        val currentState = viewModel.state.value
        assertTrue(currentState is ViewModelState.Success) // Check if the state is Success
        assertEquals(
            emptyList<PostData>(),
            (currentState as ViewModelState.Success).data
        ) // Check if the data is empty

        assertEquals(0, currentState.data.size)
    }

    @Test
    fun testHomeSubRedditData_validResponse() = runTest {
        // Arrange
        val homeResult = HomeScreenData.Success(
            listOfPosts = TestDataEndPointsUtil.getHomeSubRedditData()
        )

        // Mock the repository behavior
        Mockito.`when`(repository.getCombinedData()).thenReturn(homeResult)

        // Call the function
        viewModel.getHomeScreenData()

        // Advance the dispatcher to process the coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        // Check the state change
        val currentState = viewModel.state.value
        assertTrue(currentState is ViewModelState.Success) // Check if the state is Success
        assertEquals(
            homeResult.listOfPosts,
            (currentState as ViewModelState.Success).data
        ) // Check if the data matches spaceResult.posts

        assertEquals(2, currentState.data.size)

        // Check the first post
        val firstPost = currentState.data[0]
        assertEquals("Exciting New Discoveries in Science", firstPost.title)
        assertEquals("r/science", firstPost.subredditNamePrefixed)
        assertEquals("https://example.com/science_thumbnail.jpg", firstPost.thumbnail)
        assertEquals("ScienceEnthusiast123", firstPost.author)
        assertEquals(2500, firstPost.ups)
        assertEquals(15, firstPost.downs)
        assertEquals("Scientific Breakthroughs", firstPost.link_flair_text)
        assertEquals(1800, firstPost.numComments)
        assertEquals(1.77934567E9, firstPost.created_utc)
    }


}
