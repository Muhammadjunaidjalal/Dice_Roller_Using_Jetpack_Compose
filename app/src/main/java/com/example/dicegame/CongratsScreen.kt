package com.example.dicegame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dicegame.ui.theme.GradientColor1

@Composable
fun CongratsScreen(navController: NavController ,innerPadding:PaddingValues = PaddingValues(),  scorePlayer1:Int, scorePlayer2:Int ){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            val winnerText = if (scorePlayer1 > scorePlayer2) "Player 1 Won!" else "Player 2 Won!"
            Text(
                text = winnerText,
                fontSize = 40.sp,
                color = GradientColor1,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.size(40.dp))
        GradientButton(text = "Play Again", onClick = {
                navController.navigate(Routs.mainScreen){
                    popUpTo(Routs.mainScreen){
                        inclusive = true
                    }
                }
        }, enabled = true)
    }
}