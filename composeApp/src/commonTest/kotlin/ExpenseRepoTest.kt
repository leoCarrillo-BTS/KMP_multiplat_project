/*
import org.example.project.data.ExpenseManager
import org.example.project.data.ExpenseRepoImpl
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class ExpenseRepoTest {

    private val expenseManager = ExpenseManager
    private val repo = ExpenseRepoImpl(expenseManager)

    @Test
    fun expense_list_is_not_empty() {
        val expenseList = mutableListOf<Expense>()
        expenseList.addAll(repo.getAllExpenses())
        assertTrue { expenseList.isNotEmpty() }
    }

    @Test
    fun add_new_expense() {
        val expenseList = repo.getAllExpenses()

        repo.addExpense(
            Expense(
                id = 1,
                amount = 4.5,
                category = ExpenseCategory.OTHER,
                description = "Combustible"
            )
        )

        assertContains(expenseList, expenseList.find { it.id == 7L })
    }

    @Test
    fun edit_expense() {
        val expenseListBefore = repo.getAllExpenses()

        val newExpenseId = 7L
        repo.addExpense(
            Expense(
                id = 1,
                amount = 4.5,
                category = ExpenseCategory.OTHER,
                description = "Combustible"
            )
        )

        assertNotNull(expenseListBefore.find{ it.id == newExpenseId })

        val updatedExpense = Expense(
            id = newExpenseId,
            amount = 8.0,
            category = ExpenseCategory.OTHER,
            description = "Ropa"
        )
        repo.editExpense(updatedExpense)

        val expenseListAfter = repo.getAllExpenses()
        assertEquals(updatedExpense, expenseListAfter.find { it.id == newExpenseId })
    }

    @Test
    fun get_all_categories() {

        val categoryList = mutableListOf<ExpenseCategory>()
        categoryList.addAll(repo.getCategories())
        assertTrue(categoryList.isNotEmpty())
    }

    @Test
    fun check_all_categories() {

        val allCategories = listOf(
            ExpenseCategory.GROCERIES,
            ExpenseCategory.HOUSE,
            ExpenseCategory.CAR,
            ExpenseCategory.PARTY,
            ExpenseCategory.OTHER,
            ExpenseCategory.SNACKS
        )

        val repoCategories = repo.getCategories()

        assertEquals(allCategories, repoCategories)
    }
}
*/