package org.example.project.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.getColorsTheme
import org.example.project.model.Expense
import org.example.project.presentation.ExpensesUIState
import org.example.project.utils.EXPENSE_SCREEN_ERROR_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_ERROR_TEXT_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_LOADING_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_CLICK_ITEM_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_EMPTY_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_TOTAL_TEST_TAG
import org.example.project.utils.SwipeToDeleteContainer
import kotlin.math.exp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesScreen(
    uiState: ExpensesUIState,
    onExpenseClick: (expense: Expense) -> Unit,
    onDeleteExpense: (expense: Expense) -> Unit,
) {

    val colors = getColorsTheme()

    when (uiState) {
        is ExpensesUIState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize().testTag(EXPENSE_SCREEN_LOADING_TEST_TAG),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ExpensesUIState.Success -> {

            if (uiState.expenses.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize().testTag(EXPENSE_SCREEN_SUCCESS_EMPTY_TEST_TAG),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No expenses found, please add an expense",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1
                    )
                }

            } else {
                LazyColumn(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ).testTag(EXPENSE_SCREEN_SUCCESS_TEST_TAG),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    stickyHeader {
                        Column(
                            modifier = Modifier.background(colors.BackgroundColor)
                        ) {
                            // Composables
                            ExpensesTotalHeader(uiState.total)
                            AllExpensesHeader()
                        }
                    }
                    items(
                        items = uiState.expenses,
                        key = { it.id }
                    ) { expense: Expense ->
                        // Composables
                        SwipeToDeleteContainer(
                            item = expense,
                            onDelete = onDeleteExpense
                        ) {
                            ExpensesItem(
                                expense = expense,
                                onExpenseClick = onExpenseClick
                            )
                        }
                    }
                }
            }
        }

        is ExpensesUIState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize().testTag(EXPENSE_SCREEN_ERROR_TEST_TAG),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Error: ${uiState.message}",
                    modifier = Modifier.testTag(EXPENSE_SCREEN_ERROR_TEXT_TEST_TAG),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
fun ExpensesTotalHeader(
    total: Double
) {

    Card(
        shape = RoundedCornerShape(30),
        backgroundColor = Color.Black,
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(130.dp).padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                "$" + total,
                modifier = Modifier.testTag(EXPENSE_SCREEN_SUCCESS_TOTAL_TEST_TAG),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "USD",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun AllExpensesHeader() {

    val colors = getColorsTheme()

    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "All Expenses",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = colors.TextColor
        )
        Button(
            shape = RoundedCornerShape(50),
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            enabled = false
        ) {
            Text("View All")
        }
    }
}


@Composable
fun ExpensesItem(
    expense: Expense,
    onExpenseClick: (expense: Expense) -> Unit
) {
    val colors = getColorsTheme()

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp).clickable {
            onExpenseClick(expense)
        }.testTag(EXPENSE_SCREEN_SUCCESS_CLICK_ITEM_TEST_TAG.plus("_${expense.id}")),
        backgroundColor = colors.ColorExpenseItem,
        shape = RoundedCornerShape(30)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(30),
                color = colors.Purple
            ) {
                Image(
                    modifier = Modifier.padding(10.dp),
                    imageVector = expense.icon,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentScale = ContentScale.Crop,
                    contentDescription = "icono ExpensesItem"
                )
            }

            Column(
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text(
                    expense.category.name,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = colors.TextColor
                )

                Text(
                    expense.description,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }

            Text(
                "$${expense.amount}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = colors.TextColor
            )
        }
    }
}