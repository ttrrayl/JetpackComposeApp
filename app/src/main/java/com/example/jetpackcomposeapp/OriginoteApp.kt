package com.example.jetpackcomposeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposeapp.ui.ScreenNavigation
import com.example.jetpackcomposeapp.ui.detail.DetailScreen
import com.example.jetpackcomposeapp.ui.home.HomeScreen
import com.example.jetpackcomposeapp.ui.profile.ProfileScreen
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@ExperimentalMaterial3Api
@Composable
fun OriginoteApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    val navBactStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBactStackEntry?.destination?.route

    Scaffold(topBar = {
        if (currentRoute == ScreenNavigation.Home.route){
            TopBar(
                navController = navController,
                modifier = modifier.padding(start = 8.dp) )
        }
    },
        content = { innerPadding ->
            NavHost(navController = navController,
                    startDestination = ScreenNavigation.Home.route,
                    modifier = Modifier.padding(innerPadding)) {
                composable(ScreenNavigation.Home.route){
                    HomeScreen(navigateToDetail = {originoteId ->
                        navController.navigate(ScreenNavigation.Detail.createRoute(originoteId))
                    })
                }
                composable(ScreenNavigation.Profile.route) {
                    ProfileScreen(navigateBack = { navController.navigateUp() })
                }
                composable(
                    route = ScreenNavigation.Detail.route,
                    arguments = listOf(navArgument("originoteId") { type = NavType.LongType})) {
                    val id = it.arguments?.getLong("originoteId") ?: -1L
                    DetailScreen(originoteId = id, navigateBack = {navController.navigateUp()})
                }
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar(
    navController: NavHostController,
    modifier: Modifier,
) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = "Originote App",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineMedium)

    },  actions = {
        IconButton(onClick = {
            navController.navigate(ScreenNavigation.Profile.route){
                popUpTo(navController.graph.findStartDestination().id){
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "about",
                modifier = modifier.size(32.dp))
        }
    })
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun OriginoteAppPreview(){
    JetpackComposeAppTheme {
        OriginoteApp()
    }
}