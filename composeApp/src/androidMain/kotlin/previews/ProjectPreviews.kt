package previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.data.ExpenseManager
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.example.project.presentation.ExpensesUIState
import org.example.project.ui.AllExpensesHeader
import org.example.project.ui.ExpenseAmount
import org.example.project.ui.ExpensesItem
import org.example.project.ui.ExpensesScreen
import org.example.project.ui.ExpensesTotalHeader

@Preview(
    showBackground = true
)
@Composable
fun ExpensesTotalHeaderPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        ExpensesTotalHeader(
            total = 12345.56
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AllExpensesHeaderPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        AllExpensesHeader()
    }
}


@Preview(
    showBackground = false
)
@Composable
fun ExpensesItemPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        ExpensesItem(
            expense = ExpenseManager.fakeExpenseList[0],
            onExpenseClick = {}
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ExpensesScreenPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        ExpensesScreen(
            uiState = ExpensesUIState.Success(
                expenses = ExpenseManager.fakeExpenseList,
                total = 1234.56
            ),
            onExpenseClick = { },
            onDeleteExpense = { }
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ExpenseAmountPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        ExpenseAmount(
            priceContent = 12.0,
            onPriceChanged = {  },
            keyboardController = LocalSoftwareKeyboardController.current
        )
    }
}

