package org.example.project.data

import org.example.project.domain.ExpenseRepository
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

class ExpenseRepoImpl(
    private val expenseManager: ExpenseManager
): ExpenseRepository {

    override fun getAllExpenses(): List<Expense> {
        return expenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        expenseManager.addNewExpense(
            expense = expense
        )
    }

    override fun editExpense(expense: Expense) {
        expenseManager.editExpense(
            expense = expense
        )
    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        return expenseManager.deleteExpense(
            expense = expense
        )
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expenseManager.getCategories()
    }
}