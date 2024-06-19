package com.mmartosdev.medium

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.LottieConstants
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import mediumcompose.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Immutable
private enum class EmojiType {
    EmojiHugFace,
    EmojiPartyingFace,
    EmojiWink,
    EmojiWinkTongue,
}

@Composable
fun Emojis() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = spacedBy(16.dp, alignment = Alignment.CenterHorizontally),
    ) {
        Emoji(EmojiType.EmojiHugFace, modifier = Modifier.size(100.dp))
        Emoji(EmojiType.EmojiPartyingFace, modifier = Modifier.size(100.dp))
        Emoji(EmojiType.EmojiWink, modifier = Modifier.size(100.dp))
        Emoji(EmojiType.EmojiWinkTongue, modifier = Modifier.size(100.dp))
    }
}

@Composable
@OptIn(ExperimentalResourceApi::class)
private fun Emoji(
    emojiType: EmojiType,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        var emojiContent by remember(emojiType) { mutableStateOf<String?>(null) }
        LaunchedEffect(emojiType) {
            val byteArray: ByteArray = Res.readBytes(emojiType.toResource())
            emojiContent = byteArray.decodeToString()
        }
        emojiContent?.run {
            val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(this))
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
            composition?.run {
                LottieAnimation(
                    progress = { progress },
                    composition = composition,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

private fun EmojiType.toResource(): String =
    when (this) {
        EmojiType.EmojiHugFace -> "files/hug_face.json"
        EmojiType.EmojiPartyingFace -> "files/partying_face.json"
        EmojiType.EmojiWink -> "files/wink.json"
        EmojiType.EmojiWinkTongue -> "files/wink_tongue.json"
    }
