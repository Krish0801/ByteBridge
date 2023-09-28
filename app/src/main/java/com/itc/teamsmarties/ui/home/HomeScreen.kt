package com.itc.teamsmarties.ui.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.data.UiToolingDataApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.itc.teamsmarties.R
import com.itc.teamsmarties.domain.EndPointsViewModel
import com.itc.teamsmarties.domain.ViewModelState
import com.itc.teamsmarties.navigation.Screen
import com.itc.teamsmarties.util.dataMapper.PostData
import com.itc.teamsmarties.util.timeStamp.getTimeStampText


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(
    viewModel: EndPointsViewModel,
    currentEndPoint: String,
    navController: NavHostController,
) {

    LaunchedEffect(Unit) {

        when (currentEndPoint) {
            "Home" -> {
                viewModel.getHomeScreenData()
            }

            "Sports" -> {
                viewModel.getSportsSubRedditData()
            }

            "Technology" -> {
                viewModel.getTechnologySubRedditData()
            }

            "Food" -> {
                viewModel.getFoodSubRedditData()
            }
        }
    }
    val homeScreenDataState by viewModel.state.collectAsState()

    when (homeScreenDataState) {
        is ViewModelState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            }
        }

        is ViewModelState.Success -> {

            val homeScreenData = (homeScreenDataState as ViewModelState.Success).data
            PostsPage(homeScreenData, navController)

        }

        is ViewModelState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Check the Internet connection",
                        style = TextStyle(fontSize = 18.sp, color = Color.Red),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        viewModel.getHomeScreenData()
                    }) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}

@OptIn(UiToolingDataApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostsPage(homeScreenData: List<PostData>, navController: NavHostController) {
    val shuffledData = homeScreenData.shuffled()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {
            items(shuffledData) { post ->
                val randomImageResId = remember { mutableStateOf(0) }
                val postName = post.name.toString()
                val postId = postName.substring(postName.indexOf("_") + 1)
                postId.trim()
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 1.dp)
                        .clickable {
                            navController.navigate("${Screen.DetailsScreen.route}/$postId") {
                                launchSingleTop = true

                            }
                        },
                    backgroundColor = MaterialTheme.colorScheme.onSecondary,
                    elevation = 0.dp,
                    shape = RoundedCornerShape(0.dp)

                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val imageUrl = post.linkFlairRichtext?.firstOrNull()?.u
                            val subredditIcon = when (post.subredditNamePrefixed.toString()) {
                                "r/sports" -> imageUrl
                                "r/technology" -> R.drawable.ic_technology
                                "r/food" -> R.drawable.ic_crypto
                                else -> null
                            }

                            subredditIcon?.let { iconResId ->
                                Image(
                                    painter = rememberAsyncImagePainter(model = iconResId),
                                    contentDescription = "icon",
                                    modifier = Modifier
                                        .width(18.dp)
                                        .height(18.dp),
                                    contentScale = ContentScale.Crop,
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                            }

                            Text(
                                text = "${post.subredditNamePrefixed}",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            val createdUtcSeconds = post.created_utc ?: 0
                            val formattedTime = getTimeStampText(createdUtcSeconds as Double)

                            Text(
                                text = formattedTime,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        post.title?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        val randomImgMap =
                            listOf(
                                R.drawable.img_food1,
                                R.drawable.img_food2,
                                R.drawable.img_food3,
                                R.drawable.img_food4
                            )
                        if (post.preview == null && post.sportsPreview == null && post.subredditNamePrefixed.toString() != "r/food") {
                            if (randomImageResId.value == 0) {
                                randomImageResId.value = randomImgMap.random()
                            }

                            Image(
                                painter = painterResource(id = randomImageResId.value),
                                contentDescription = "photos",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                contentScale = ContentScale.Crop,
                            )
                        } else {


                            val data: String? = when (post.subredditNamePrefixed.toString()) {
                                "r/sports" -> {
                                    if (post.sportsPreview?.images?.isNotEmpty() == true) {
                                        post.sportsPreview.images[0]?.source?.url?.replace(
                                            "amp;s",
                                            "s"
                                        )
                                    } else {
                                        null
                                    }
                                }

                                "r/technology" -> {
                                    if (post.preview?.images?.isNotEmpty() == true) {
                                        post.preview.images[0]?.source?.url?.replace("amp;s", "s")
                                    } else {
                                        null
                                    }
                                }

                                "r/food" -> {
                                    if (post.urlOverriddenByDest?.isNotEmpty() == true) {
                                        post.urlOverriddenByDest?.replace("amp;s", "s")
                                    } else {
                                        null
                                    }
                                }

                                else -> null
                            }



                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(
                                        LocalContext.current
                                    ).data(data)
                                        .error(if (isSystemInDarkTheme()) R.drawable.baseline_image_not_supported_24_white else R.drawable.baseline_image_not_supported_24)
                                        .build()
                                ),
                                contentDescription = "photos",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                contentScale = ContentScale.Crop,
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            var isThumbsUpClicked by remember { mutableStateOf(false) }
                            var isThumbsDownClicked by remember { mutableStateOf(false) }

                            val originalUps = post.ups ?: 0
                            val originalDowns = post.downs ?: 0

                            var upsCount by remember { mutableStateOf(originalUps) }
                            var downsCount by remember { mutableStateOf(originalDowns) }

                            val thumbsUpIcon = if (isThumbsUpClicked) {
                                R.drawable.ic_thumbs_up_solid
                            } else {
                                R.drawable.ic_thump_up
                            }

                            Icon(
                                painter = painterResource(thumbsUpIcon),
                                contentDescription = "Thumbs up",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable {
                                        isThumbsUpClicked = !isThumbsUpClicked
                                        if (isThumbsUpClicked) {
                                            isThumbsDownClicked = false
                                            upsCount++
                                            downsCount = originalDowns
                                        } else {
                                            upsCount = originalUps
                                        }
                                    }
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = upsCount.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            val thumbsDownIcon = if (isThumbsDownClicked) {
                                R.drawable.ic_thumbs_down_solid
                            } else {
                                R.drawable.ic_thumbs_down
                            }

                            Icon(
                                painter = painterResource(thumbsDownIcon),
                                contentDescription = "Thumbs down",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable {
                                        isThumbsDownClicked = !isThumbsDownClicked
                                        if (isThumbsDownClicked) {
                                            isThumbsUpClicked = false
                                            downsCount++
                                            upsCount = originalUps
                                        } else {
                                            downsCount = originalDowns
                                        }
                                    }
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = downsCount.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Icon(
                                painter = painterResource(R.drawable.ic_message),
                                contentDescription = "Comments Icon",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = post.numComments.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    }
                }
            }
        }
    }
}



