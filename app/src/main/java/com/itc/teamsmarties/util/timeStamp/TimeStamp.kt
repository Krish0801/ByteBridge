package com.itc.teamsmarties.util.timeStamp

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getTimeStampText(timestamp: Double): String {
    val instant = Instant.ofEpochSecond(timestamp.toLong())
    val currentTime = Instant.now()
    val duration = Duration.between(instant, currentTime)

    val daysAgo = duration.toDays()
    val hoursAgo = duration.toHours()

    return when {
        daysAgo >= 100 -> {
            val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
            dateFormatter.format(date)
        }

        daysAgo > 5 -> "5 days ago"
        daysAgo > 0 -> "$daysAgo day ago"
        hoursAgo > 0 -> "$hoursAgo hours ago"
        else -> "Less than hour"
    }
}

