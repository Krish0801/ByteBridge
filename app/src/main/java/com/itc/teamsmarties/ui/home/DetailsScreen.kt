package com.itc.teamsmarties.ui.home

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.itc.teamsmarties.R
import com.itc.teamsmarties.domain.DetailsScreenViewModel
import com.itc.teamsmarties.domain.ViewModelState
import com.itc.teamsmarties.util.dataMapper.PostData
import com.itc.teamsmarties.util.timeStamp.getTimeStampText


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(postId: String) {
    val viewModel: DetailsScreenViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getPostsById(postId)
    }


    val detailScreenPostState by viewModel.state.collectAsState()
    when (detailScreenPostState) {
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
            val detailScreenPostData = (detailScreenPostState as ViewModelState.Success).data
            DetailsScreenPage(detailScreenPostData)
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
                        viewModel.getPostsById(postId)
                    }) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreenPage(detailScreenPostData: List<PostData>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 60.dp, start = 16.dp, top = 16.dp, end = 16.dp)

        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val subredditIcon =
                    when (detailScreenPostData[0].subredditNamePrefixed.toString()) {
                        "r/sports" -> R.drawable.ic_sport
                        "r/technology" -> R.drawable.ic_technology
                        "r/food" -> R.drawable.ic_food
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
                }

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "${detailScreenPostData[0].subredditNamePrefixed}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                val createdUtcSeconds = detailScreenPostData[0].created_utc ?: 0
                val formattedTime = getTimeStampText(createdUtcSeconds as Double)

                Text(
                    text = formattedTime,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            detailScreenPostData[0].link_flair_text?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            detailScreenPostData[0].title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            val randomImageResId = remember { mutableStateOf(0) }
            val randomImgMap =
                listOf(
                    R.drawable.img_s1,
                    R.drawable.img_s2,
                    R.drawable.img_s3,
                    R.drawable.img_s4
                )
            if (detailScreenPostData[0].preview == null && detailScreenPostData[0].sportsPreview == null && detailScreenPostData[0].subredditNamePrefixed.toString() != "r/food") {
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


                val data: String? = when (detailScreenPostData[0].subredditNamePrefixed.toString()) {
                    "r/sports" -> {
                        if (detailScreenPostData[0].sportsPreview?.images?.isNotEmpty() == true) {
                            detailScreenPostData[0].sportsPreview?.images?.get(0)?.source?.url?.replace("amp;s", "s")
                        } else {
                            null
                        }
                    }
                    "r/technology" -> {
                        if (detailScreenPostData[0].preview?.images?.isNotEmpty() == true) {
                            detailScreenPostData[0].preview?.images?.get(0)?.source?.url?.replace("amp;s", "s")
                        } else {
                            null
                        }
                    }
                    "r/food" -> {
                        if (detailScreenPostData[0].urlOverriddenByDest?.isNotEmpty() == true) {
                            detailScreenPostData[0].urlOverriddenByDest?.replace("amp;s", "s")
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
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                var isThumbsUpClicked by remember { mutableStateOf(false) }
                var isThumbsDownClicked by remember { mutableStateOf(false) }

                val originalUps = detailScreenPostData[0].ups ?: 0
                val originalDowns = detailScreenPostData[0].downs ?: 0

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
                    text = detailScreenPostData[0].numComments.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Comments:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))


            for ((index, detail) in detailScreenPostData.withIndex()) {
                if (index > 0) {
                    Spacer(modifier = Modifier.height(2.dp))

                    detail.body?.let {
                        androidx.compose.material.Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            backgroundColor = MaterialTheme.colorScheme.onSecondary,
                            elevation = 0.dp,
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.reddit_splash),
                                        contentDescription = "Avatar",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = detail.commentsAuthor.toString(),
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}





