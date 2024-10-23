package org.example.project.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.example.project.domain.ExpenseRepository
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory


sealed class ExpensesUIState {
    object Loading : ExpensesUIState()
    data class Success(val expenses: List<Expense>, val total: Double) : ExpensesUIState()
    data class Error(val message: String) : ExpensesUIState()
}

class ExpensesViewModel(
    private val repo: ExpenseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpensesUIState>(ExpensesUIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getExpenseList()
    }

    private fun getExpenseList() {
        viewModelScope.launch {
            try {

                while (true) {
                    delay(2000)
                    val expenses = repo.getAllExpenses()
                    _uiState.value = ExpensesUIState.Success(expenses, expenses.sumOf { it.amount })
                }

            } catch (e: Exception) {
                _uiState.value = ExpensesUIState.Error(e.message ?: "Error")
            }
        }
    }

    private suspend fun updateExpenseList() {
        try {
            val expenses = repo.getAllExpenses()
            _uiState.value = ExpensesUIState.Success(expenses, expenses.sumOf { it.amount })
        } catch (e: Exception) {
            _uiState.value = ExpensesUIState.Error(e.message ?: "Error")
        }
    }

    fun addExpense(
        expense: Expense
    ) {
        viewModelScope.launch {
            try {
                repo.addExpense(expense)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUIState.Error(e.message ?: "Error")
            }
        }
    }

    fun editExpense(
        expense: Expense
    ) {
        viewModelScope.launch {
            try {
                repo.editExpense(expense)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUIState.Error(e.message ?: "Error")

            }
        }
    }

    fun deleteExpense(
        id: Long
    ) {
        viewModelScope.launch {
            try {
                repo.deleteExpense(id)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUIState.Error(e.message ?: "Error")

            }
        }
    }

    fun getExpenseWithId(id: Long): Expense? {
        return (_uiState.value as? ExpensesUIState.Success)?.expenses?.firstOrNull { it.id == id }
    }

    fun getCategories(): List<ExpenseCategory> {
        return repo.getCategories()
    }
}