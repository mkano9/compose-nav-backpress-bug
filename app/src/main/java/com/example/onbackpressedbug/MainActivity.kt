package com.example.onbackpressedbug

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.example.onbackpressedbug.ui.theme.OnBackPressedBugTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnBackPressedBugTheme {
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = "one_nav_graph",
                ) {
                    navGraphOne(navController)
                    navGraphTwo(navController)
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.navGraphOne(navController: NavController) {
    navigation(
        route = "one_nav_graph",
        startDestination = "one",
    ) {
        composable("one") {
            val nestedNavController = rememberAnimatedNavController()
            AnimatedNavHost(
                navController = nestedNavController,
                startDestination = "nested_1",
            ) {
                composable("nested_1") {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue)
                    ) {
                        Button(
                            onClick = {
                                nestedNavController.navigate("nested_2")
                            },
                        ) {
                            Text(text = "nested_1")
                        }
                    }
                }
                composable("nested_2") {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue)
                    ) {
                        Button(
                            onClick = {
                                navController.navigate("two_nav_graph")
                            },
                        ) {
                            Text(text = "nested_2")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.navGraphTwo(navController: NavController) {
    navigation(
        route = "two_nav_graph",
        startDestination = "two",
    ) {
        composable("two") {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
            ) {
                Button(
                    onClick = {
                        navController.navigate("two_nav_graph")
                    },
                ) {
                    Text(text = "two")
                }
            }
        }
    }
}
