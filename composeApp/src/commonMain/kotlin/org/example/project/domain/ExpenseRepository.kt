package org.example.project.domain

import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

interface ExpenseRepository {

    fun getAllExpenses(): List<Expense>
    fun addExpenses(expense: Expense)
    fun editExpenses(expense: Expense)
    fun getCategories(): List<ExpenseCategory>
}