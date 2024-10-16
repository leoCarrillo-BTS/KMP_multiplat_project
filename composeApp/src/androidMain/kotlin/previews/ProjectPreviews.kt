package previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.example.project.ui.AllExpensesHeader
import org.example.project.ui.ExpensesItem
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
            expense = Expense(
                id = 1L,
                amount = 100.0,
                category = ExpenseCategory.SNACKS,
                description = "SNACKS",
            ),
            onExpenseClick = {}
        )
    }
}