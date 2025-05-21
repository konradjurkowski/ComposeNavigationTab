package com.konradjurkowski.composenavigationtab

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

data class NavigationTabOptions(
    val index: Long,
    val title: String,
)

abstract class NavigationTab {

    abstract val options: NavigationTabOptions @Composable get
    @Composable abstract fun Content()

    val route: String get() = this::class.simpleName.orEmpty()
}

@Composable
fun ComposeNavigationTab(
    tabNavigator: NavHostController,
    tabList: List<NavigationTab>,
    bottomBar: @Composable ((tabs: List<NavigationTab>, navigator: NavHostController) -> Unit),
) {
    Scaffold(
        bottomBar = { bottomBar(tabList, tabNavigator) },
        content = { innerPadding ->
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = innerPadding.calculateBottomPadding()),
                navController = tabNavigator,
                startDestination = tabList.first().route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None },
            ) {
                tabList.forEach { tab ->
                    composable(tab.route) { tab.Content() }
                }
            }
        },
    )
}
