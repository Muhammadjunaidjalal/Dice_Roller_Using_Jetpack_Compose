package com.example.dicegame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dicegame.ui.theme.GradientColor1
import com.example.dicegame.ui.theme.GradientColor2
import kotlin.random.Random


@Composable
fun App(navController: NavController, innerPadding: PaddingValues = PaddingValues()) {
    var scorePlayer1 by remember { mutableStateOf(0) }
    var scorePlayer2 by remember { mutableStateOf(0) }

    var isPlayer1Turn by remember { mutableStateOf(true) }
    var currentImage by remember { mutableStateOf(1) }

    val images = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )
    if (scorePlayer2 >= 20 || scorePlayer1 >= 20) {
        navController.navigate(Routs.congratsScreen)

    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isPlayer1Turn) "Player 1 Turn" else "Player 2 Turn",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.size(16.dp))

            Image(
                painter = painterResource(images[currentImage - 1]),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.size(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                Text(
                    text = "P1: $scorePlayer1",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = GradientColor1
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "P2: $scorePlayer2",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = GradientColor1
                )
            }

            Spacer(modifier = Modifier.size(32.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                GradientButton(
                    text = "Roll P1",
                    onClick = {
                        val random = Random.nextInt(6) + 1
                        currentImage = random
                        scorePlayer1 += random
                        isPlayer1Turn = !isPlayer1Turn
                    },
                    enabled = isPlayer1Turn
                )
                Spacer(modifier = Modifier.width(16.dp))
                GradientButton(
                    text = "Roll P2",
                    onClick = {
                        val random = Random.nextInt(6) + 1
                        currentImage = random
                        scorePlayer2 += random
                        isPlayer1Turn = !isPlayer1Turn
                    },
                    enabled = !isPlayer1Turn
                )
            }
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ){
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(GradientColor1, GradientColor2)
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp) ,
                contentAlignment = Alignment.Center
        ){
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

    }
}
