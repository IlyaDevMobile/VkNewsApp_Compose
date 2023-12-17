package com.ilyakoz.vknewsapp.presentation.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ilyakoz.vknewsapp.R
import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.domain.entity.StatisticItem
import com.ilyakoz.vknewsapp.domain.entity.StatisticType


@Composable
fun PostCard(
    modifeir: Modifier = Modifier,
    feedPost: FeedPost,
    onLikeClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,

    ) {
    Card(
        modifier = modifeir
    ) {
        PostHeader(feedPost)
        TextUnderHeading(feedPost)
        PostImage(feedPost)
        Statistics(
            statistics = feedPost.statistics,
            onLikeClickListener = onLikeClickListener,
            onCommentsClickListener = onCommentsClickListener,
            isFavourite = feedPost.isLiked
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onLikeClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,
    isFavourite: Boolean,
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
        IconWithText(
            image = R.drawable.baseline_remove_red_eye_24,
            text = viewsItem.count.toString(),
        )
        val sharesItem = statistics.getItemByType(StatisticType.SHARES)
        IconWithText(
            image = R.drawable.baseline_share_24,
            text = sharesItem.count.toString(),
        )
        val commentsItem = statistics.getItemByType(StatisticType.COMMENTS)
        IconWithText(
            image = R.drawable.baseline_mode_comment_24,
            text = commentsItem.count.toString(),
            onItemClickListener = {
                onCommentsClickListener(commentsItem)
            }
        )
        val likesItem = statistics.getItemByType(StatisticType.LIKES)
        IconWithText(
            image = if (isFavourite) R.drawable.ic_like else R.drawable.ic_dont_like,
            text = formatStatisticCount(likesItem.count),
            onItemClickListener = {
                onLikeClickListener(likesItem)
            },
            tint = if (isFavourite) Color.Red else MaterialTheme.colorScheme.onSecondary
        )

    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalArgumentException()
}

@Composable
private fun IconWithText(
    image: Int,
    text: String,
    onItemClickListener: (() -> Unit)? = null,
    tint: Color = MaterialTheme.colorScheme.onSecondary
) {
    val modifeir = if (onItemClickListener == null) {
        Modifier
    } else {
        Modifier.clickable {
            onItemClickListener()
        }
    }

    Row(
        modifier = modifeir
            .padding(8.dp)
    ) {

        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = image),
            contentDescription = null,
            tint = tint
        )
        Spacer(Modifier.width(4.dp))
        Text(text = text)
    }


}

@Composable
private fun TextUnderHeading(
    feedPost: FeedPost
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = feedPost.contentText)
    }
}

@Composable
private fun PostImage(
    feedPost: FeedPost
) {
    AsyncImage(
        model = feedPost.contentImageUrl,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun PostHeader(

    feedPost: FeedPost
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = feedPost.communityImageUrl,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp),

            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onSecondary
            )

        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_menu_24),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

private fun formatStatisticCount(count: Int): String {
    return if (count > 100_000) {
        String.format("%sK", (count / 1000))
    } else if (count > 1000) {
        String.format("%.1fk", (count / 1000f))
    } else {
        count.toString()
    }
}
