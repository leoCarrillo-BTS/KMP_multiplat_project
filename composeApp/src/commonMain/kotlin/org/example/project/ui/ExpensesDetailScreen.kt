package org.example.project.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import org.example.project.getColorsTheme
import org.example.project.model.Expense

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpensesDetailScreen(
    expenseToEdit: Expense? = null,
    addExpenseAndNavigateBack: (Expense) -> Unit
) {

    val colors = getColorsTheme()
    val price by remember { mutableStateOf(expenseToEdit?.amount ?: 0.0) }
    val description by remember { mutableStateOf(expenseToEdit?.description ?: "") }
    val expenseCategory by remember { mutableStateOf(expenseToEdit?.category?.name ?: "") }
    val selectedCategory by remember { mutableStateOf(expenseToEdit?.category?.name ?: "Select a category") }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(sheetState.targetValue) {
        if (sheetState.targetValue == ModalBottomSheetValue.Expanded) {
            keyboardController?.hide()
        }
    }
}