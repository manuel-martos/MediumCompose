package com.mmartosdev.medium

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun HelloWorld() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val transition = rememberInfiniteTransition("HelloWorld")
        val angle by transition.animateFloat(
            initialValue = -10f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse
            ),
        )
        val scale by transition.animateFloat(
            initialValue = 1f,
            targetValue = 1.6f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Reverse
            ),
        )
        Text(
            text = "Hello from KMP!!",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = angle
                    scaleX = scale
                    scaleY = scale
                }
        )
    }
}