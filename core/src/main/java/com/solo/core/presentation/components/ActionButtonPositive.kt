package com.solo.core.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionButtonPositive(
    isEnabled: Boolean,
    title: String,
    onActionButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onActionButton() },
        enabled = isEnabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 64.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = title)
    }
}