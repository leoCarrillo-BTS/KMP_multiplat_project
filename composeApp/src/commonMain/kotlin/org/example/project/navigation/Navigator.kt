package org.example.project.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import org.example.project.data.ExpenseManager
import org.example.project.data.ExpenseRepoImpl
import org.example.project.getColorsTheme
import org.example.project.presentation.ExpensesViewModel
import org.example.project.ui.ExpensesScreen

@Composable
fun Navigation(
    navigator: Navigator
) {
    val colors = getColorsTheme()
    val viewModel = viewModel(modelClass = ExpensesViewModel::class) {
        ExpensesViewModel(ExpenseRepoImpl(ExpenseManager))
    }

    NavHost(
        modifier = Modifier.background(colors.BackgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ) {
        scene(route = "/home") {

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState) {
                navigator.navigate("/addExpenses/${it.id}")
            }
        }

        scene(route = "/addExpenses/{id}") {
            val idFromPath = it.path<Long>("id")
            val isAddExpense = idFromPath?.let { id -> viewModel.getExpenseWithId(id)}
        }
    }
}