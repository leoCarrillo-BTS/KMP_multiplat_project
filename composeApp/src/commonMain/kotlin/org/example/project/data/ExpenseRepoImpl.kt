package org.example.project.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.project.domain.ExpenseRepository
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.example.project.model.NetworkExpense

private const val BASE_URL = "http://192.168.1.66:8080"

class ExpenseRepoImpl(
    private val appDatabase: AppDatabase,
    private val httpClient: HttpClient
): ExpenseRepository {

    private val queries = appDatabase.expenseDbQueries

    override suspend fun getAllExpenses(): List<Expense> {

        return if (queries.selectAll().ExecuteAsList().isEmpty()) {

            val networkResponse = httpClient.get("$BASE_URL/expenses").body<List<NetworkExpense>>()
            if (networkResponse.isEmpty()) return emptyList()

            val expenses = networkResponse.map {
                Expense(
                    id = it.id,
                    amount = it.amount,
                    category = ExpenseCategory.valueOf(it.categoryName),
                    description = it.description
                )
            }

            expenses.forEach {
                queries.insert(it.amount,it.category.name, it.description)
            }

            expenses

        } else {
            queries.selectAll().executeAsList().map {
                Expense(
                    id = it.id,
                    amount = it.amount,
                    category = ExpenseCategory.valueOf(it.categoryName),
                    description = it.description
                )
            }
        }
    }

    override suspend fun addExpense(expense: Expense) {
        queries.transaction {
            queries.insert (
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
        httpClient.post("$BASE_URL/expenses") {
            contentType(ContentType.Application.Json)
            setBody(NetworkExpense(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            ))
        }
    }

    override suspend fun editExpense(expense: Expense) {
        queries.transaction {
            queries.update (
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
        httpClient.put("$BASE_URL/expenses/${expense.id}") {
            contentType(ContentType.Application.Json)
            setBody(NetworkExpense(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            ))
        }
    }

    override suspend fun deleteExpense(id: Long) {
        httpClient.delete("$BASE_URL/expenses/${id}")
        queries.transaction {
            queries.delete(
                id = id
            )
        }
    }

    override fun getCategories(): List<ExpenseCategory> {
        return queries.categories.executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
    }
}

class AppDatabase {

}
