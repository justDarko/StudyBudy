package com.solo.studybudy

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundTheme() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(0f, height * 0.4f) // Start point for the first curve

            // First smaller curve (left side)
            cubicTo(
                width * 0.1f, height * 0.2f,   // Control point 1
                width * 0.3f, height * 0.6f,   // Control point 2
                width * 0.5f, height * 0.4f    // End point
            )

            // Second larger curve (right side)
            cubicTo(
                width * 0.7f, height * 0.2f,   // Control point 1
                width * 0.9f, height * 0.6f,   // Control point 2
                width, height * 0.3f           // End point (right edge)
            )

            lineTo(width, 0f) // Line to top right corner
            lineTo(0f, 0f)    // Line to top left corner
            close()           // Close the path
        }

        // Draw the path with a light blue color
        drawPath(path = path, color = Color(0xFF30628C))
    }
}