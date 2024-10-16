package org.example.project.data

import org.example.project.domain.ExpenseRepository
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

class ExpenseRepoImpl: ExpenseRepository {

    override fun getAllExpenses(): List<Expense> {
        return ExpenseManager.fakeExpenseList
    }

    override fun addExpenses(expense: Expense) {
        ExpenseManager.addNewExpense(
            expense = expense
        )
    }

    override fun editExpenses(expense: Expense) {
        ExpenseManager.editExpense(
            expense = expense
        )
    }

    override fun getCategories(): List<ExpenseCategory> {
        return ExpenseManager.getCategories()
    }

}