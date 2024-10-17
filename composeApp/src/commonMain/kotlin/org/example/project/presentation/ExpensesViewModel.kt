package org.example.project.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.example.project.domain.ExpenseRepository
import org.example.project.model.Expense

data class ExpenseUIState(
    val expenses: List<Expense> = emptyList(),
    val total: Double = 0.0
)

class ExpensesViewModel(
    private val repo: ExpenseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseUIState())
    val uiState = _uiState.asStateFlow()
    private val allExpense = repo.getAllExpenses()

    init {
        getAllExpenses()
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(
                expenses = allExpense,
                total = allExpense.sumOf { it.amount }
            )
        }
    }


    private fun getAllExpenses() {
        viewModelScope.launch {
            updateState()
        }
    }

    private fun addExpense(
        expense: Expense
    ) {
        viewModelScope.launch {
            repo.addExpense(expense)
            updateState()
        }
    }

    private fun editExpense(
        expense: Expense
    ) {
        viewModelScope.launch {
            repo.editExpense(expense)
            updateState()
        }
    }

    private fun deleteExpense(
        expense: Expense
    ) {
        viewModelScope.launch {
            repo.deleteExpense(expense)
            updateState()
        }
    }

    fun getExpenseWithId(id:Long): Expense {
        return allExpense.first{ it.id == id }
    }
}