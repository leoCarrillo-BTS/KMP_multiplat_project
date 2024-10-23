import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.waitUntilExactlyOneExists
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.example.project.presentation.ExpensesUIState
import org.example.project.ui.ExpensesScreen
import org.example.project.utils.EXPENSE_DETAIL_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_ERROR_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_ERROR_TEXT_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_LOADING_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_CLICK_ITEM_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_EMPTY_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_TOTAL_TEST_TAG
import kotlin.test.Test

class ExpensesScreenUITest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenUITest() = runComposeUiTest {
        setContent {

            ExpensesScreen(
                uiState = ExpensesUIState.Loading,
                onExpenseClick = {  },
                onDeleteExpense = {  }
            )

            onNodeWithTag(EXPENSE_SCREEN_LOADING_TEST_TAG).assertExists()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenErrorTest() = runComposeUiTest {

        val errorText = "Error de carga"
        val assertErrorText = "Error: ${errorText}"
        val errorUIState = ExpensesUIState.Error(message = errorText)

        setContent {
            ExpensesScreen(
                uiState = errorUIState,
                onExpenseClick = {  },
                onDeleteExpense = {  }
            )

            onNodeWithTag(EXPENSE_SCREEN_ERROR_TEST_TAG).assertExists()
            onNodeWithTag(EXPENSE_SCREEN_ERROR_TEXT_TEST_TAG).assertTextEquals(assertErrorText)
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenSuccessTest() = runComposeUiTest {

        val successUIState = ExpensesUIState.Success(
            expenses = listOf(
                Expense(
                    id = 1,
                    amount = 33.0,
                    category = ExpenseCategory.CAR,
                    description = "Gas"
                ),
                Expense(
                    id = 2,
                    amount = 244.0,
                    category = ExpenseCategory.HOUSE,
                    description = "House"
                ),
            ),
            total = 33.0
        )

        setContent {

            ExpensesScreen(
                uiState = successUIState,
                onExpenseClick = {  },
                onDeleteExpense = {  }
            )

            onNodeWithTag(EXPENSE_SCREEN_SUCCESS_TEST_TAG).assertExists()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenEmptyListSuccessTest() = runComposeUiTest {

        val successUIState = ExpensesUIState.Success(
            emptyList(),
            total = 0.0
        )

        setContent {

            ExpensesScreen(
                uiState = successUIState,
                onExpenseClick = {  },
                onDeleteExpense = {  }
            )

            onNodeWithTag(EXPENSE_SCREEN_SUCCESS_EMPTY_TEST_TAG).assertExists()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenTotalSuccessTest() = runComposeUiTest {

        val successUIState = ExpensesUIState.Success(
            expenses = listOf(
                Expense(
                    id = 1,
                    amount = 33.0,
                    category = ExpenseCategory.CAR,
                    description = "Gas"
                ),
                Expense(
                    id = 2,
                    amount = 244.0,
                    category = ExpenseCategory.HOUSE,
                    description = "House"
                ),
            ),
            total = 33.0
        )

        setContent {

            ExpensesScreen(
                uiState = successUIState,
                onExpenseClick = {  },
                onDeleteExpense = {  }
            )

            onNodeWithTag(EXPENSE_SCREEN_SUCCESS_TEST_TAG).assertExists()
            onNodeWithTag(EXPENSE_SCREEN_SUCCESS_TOTAL_TEST_TAG).assertTextEquals("$33.0")
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseScreenSuccessClickItemTest() = runComposeUiTest {

        val successUIState = ExpensesUIState.Success(
            expenses = listOf(
                Expense(
                    id = 1,
                    amount = 33.0,
                    category = ExpenseCategory.CAR,
                    description = "Gas"
                ),
                Expense(
                    id = 2,
                    amount = 244.0,
                    category = ExpenseCategory.HOUSE,
                    description = "House"
                ),
            ),
            total = 33.0
        )

        setContent {

            ExpensesScreen(
                uiState = successUIState,
                onExpenseClick = {  },
                onDeleteExpense = {  }
            )

            onNodeWithTag(EXPENSE_SCREEN_SUCCESS_CLICK_ITEM_TEST_TAG.plus("_1")).performClick()
            waitUntilExactlyOneExists(hasTestTag(EXPENSE_DETAIL_TEST_TAG))
            onNodeWithTag(EXPENSE_DETAIL_TEST_TAG).assertExists()
        }
    }
}