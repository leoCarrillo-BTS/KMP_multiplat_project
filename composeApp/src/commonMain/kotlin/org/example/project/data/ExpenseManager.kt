package org.example.project.data

import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

object ExpenseManager {
    private var currentid = 1L

    val fakeExpenseList = mutableListOf(
        Expense(
            id = currentid++,
            amount = 70.0,
            category = ExpenseCategory.GROCERIES,
            description = "GROCERIES"
        ),
        Expense(
            id = currentid++,
            amount = 30.0,
            category = ExpenseCategory.SNACKS,
            description = "SNACKS"
        ),
        Expense(
            id = currentid++,
            amount = 10000.0,
            category = ExpenseCategory.CAR,
            description = "CAR"
        ),
        Expense(
            id = currentid++,
            amount = 150.0,
            category = ExpenseCategory.PARTY,
            description = "PARTY"
        ),
        Expense(
            id = currentid++,
            amount = 1234.0,
            category = ExpenseCategory.HOUSE,
            description = "HOUSE"
        ),
        Expense(
            id = currentid++,
            amount = 987.0,
            category = ExpenseCategory.OTHER,
            description = "OTHER"
        )
    )
}