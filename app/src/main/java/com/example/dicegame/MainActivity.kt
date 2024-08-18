package com.example.dicegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dicegame.ui.theme.DiceGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceGameApp(innerPadding)
                }
            }
        }
    }
}

@Composable
fun DiceGameApp(innerPadding : PaddingValues = PaddingValues()) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routs.mainScreen
    ) {
        composable(Routs.mainScreen) {
            App(navController = navController)
        }
        composable(Routs.congratsScreen) {
            CongratsScreen(navController = navController, scorePlayer1 = 0, scorePlayer2 = 0)
        }
    }
}
