package org.example.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.example.project.data.CrossConfigDevice
import org.example.project.data.SessionCache
import org.example.project.data.TopBarTitleTypes
import org.example.project.navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.core.context.KoinContext

@Preview
@Composable
fun App(
    configDevice: CrossConfigDevice? = null
) {

    PreComposeApp {

        KoinContext {

            val colors = getColorsTheme()
            SessionCache.configDevice = configDevice

            AppTheme {

                val navigator = rememberNavigator()
                val topBarTitle = getTopBarTitle(navigator)
                val isEditOrAddExpenses = topBarTitle != TopBarTitleTypes.DASHBOARD.value

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            elevation = 10.dp,
                            title = {
                                Text(
                                    text = topBarTitle,
                                    color = colors.TextColor,
                                    fontSize = 25.sp,
                                )
                            },
                            backgroundColor = colors.BackgroundColor,
                            navigationIcon = {
                                if (isEditOrAddExpenses) {
                                    IconButton(
                                        onClick = {
                                            navigator.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            modifier = Modifier.padding(start = 16.dp),
                                            imageVector = Icons.Default.ArrowBackIosNew,
                                            tint = colors.TextColor,
                                            contentDescription = "Back icon"
                                        )
                                    }
                                } else {
                                    Icon(
                                        modifier = Modifier.padding(start = 16.dp),
                                        imageVector = Icons.Default.Apps,
                                        tint = colors.TextColor,
                                        contentDescription = "Dashboard icon"
                                    )
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        if (!isEditOrAddExpenses) {
                            FloatingActionButton(
                                modifier = Modifier.padding(8.dp),
                                onClick = {
                                    navigator.navigate("/addExpenses")
                                },
                                shape = RoundedCornerShape(50),
                                backgroundColor = colors.AddIconColor,
                                contentColor = Color.White
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    tint = Color.White,
                                    contentDescription = "Floatng Icon"
                                )
                            }
                        }
                    }
                ) {
                    Navigation(navigator)
                }
            }
        }
    }
}

/*
Ejemplo {  }
Ejemplo (click = {

})
*/


@Composable
fun getTopBarTitle(navigator: Navigator): String {
    var topBarTitle = TopBarTitleTypes.DASHBOARD

    val isOnAddExpenses =
        navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{id}?")
    if (isOnAddExpenses) {
        topBarTitle = TopBarTitleTypes.ADD
    }

    val isOnEditExpenses = navigator.currentEntry.collectAsState(null).value?.path<Long>("id")
    isOnEditExpenses?.let {
        topBarTitle = TopBarTitleTypes.EDIT
    }

    return topBarTitle.value
}