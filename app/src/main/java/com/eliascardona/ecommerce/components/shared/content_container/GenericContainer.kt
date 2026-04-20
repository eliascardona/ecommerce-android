package com.eliascardona.ecommerce.components.shared.content_container

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GenericContainer(
    modifier: Modifier = Modifier,
    children: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        children()
    }
}