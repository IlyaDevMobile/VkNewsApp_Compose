package com.ilyakoz.vknewsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ilyakoz.vknewsapp.R
import com.ilyakoz.vknewsapp.domain.FeedPost
import com.ilyakoz.vknewsapp.domain.StatisticItem
import com.ilyakoz.vknewsapp.domain.StatisticType


@Composable
fun PostCard(
    modifeir: Modifier = Modifier,
    feedPost: FeedPost,
    onLikeClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,
    onViewsClickListener: (StatisticItem) -> Unit

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
            onShareClickListener = onShareClickListener,
            onViewsClickListener = onViewsClickListener,
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onLikeClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,
    onViewsClickListener: (StatisticItem) -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
        IconWithText(
            image = R.drawable.baseline_remove_red_eye_24,
            text = viewsItem.count.toString(),
            onItemClickListener = {
                onViewsClickListener(viewsItem)
            }
        )
        val sharesItem = statistics.getItemByType(StatisticType.SHARES)
        IconWithText(
            image = R.drawable.baseline_share_24,
            text = sharesItem.count.toString(),
            onItemClickListener = {
                onShareClickListener(sharesItem)
            }
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
            image = R.drawable.baseline_favorite_border_24,
            text = likesItem.count.toString(),
            onItemClickListener = {
                onLikeClickListener(likesItem)
            }
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
    onItemClickListener: () -> Unit
) {
    Row(modifier = Modifier
        .padding(8.dp)
        .clickable { onItemClickListener() }
    ) {

        Icon(
            painter = painterResource(id = image),
            contentDescription = null
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
    Image(
        painter = painterResource(id = feedPost.contentImageResId),
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
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp),

            painter = painterResource(id = feedPost.avatarResId),
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
