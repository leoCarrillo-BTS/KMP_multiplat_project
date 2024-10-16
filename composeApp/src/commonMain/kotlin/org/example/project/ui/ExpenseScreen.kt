package org.example.project.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.getColorsTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesScreen() {

    val colors = getColorsTheme()

    LazyColumn(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader {
            Column (
                modifier = Modifier.background(colors.BackgroundColor)
            ) {
                // Composables

            }
        }
        items(emptyList<String>()){
            // Composables

        }
    }
}