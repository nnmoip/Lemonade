package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LemonadeBanner()
        LemonadeProducer(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        )
    }

}

@Composable
fun LemonadeBanner(modifier: Modifier = Modifier) {
    Text(text = "Lemonade")
}

@Composable
fun LemonadeProducer(modifier: Modifier = Modifier) {
    var pageToLoad by remember { mutableIntStateOf(1) }
    var toPress by remember { mutableIntStateOf(4) }
    when (pageToLoad) {
        1 -> LemonadePage(
                painterImage = painterResource(id = R.drawable.lemon_tree),
                imageDesc = stringResource(R.string.lemon_tree),
                instructions = stringResource(R.string.instructions_1),
                modifier = modifier,
                imageOnClick = {
                    pageToLoad = 2
                    toPress = (2..4).random()
                }
            )
        2 -> LemonadePage(
                painterImage = painterResource(id = R.drawable.lemon_squeeze),
                imageDesc = stringResource(R.string.lemon),
                instructions = stringResource(R.string.instructions_2),
                modifier = modifier,
                imageOnClick = {
                    if(toPress == 0) pageToLoad = 3
                    else toPress--
                }
            )
        3 -> LemonadePage(
                painterImage = painterResource(id = R.drawable.lemon_drink),
                imageDesc = stringResource(R.string.lemonade_glass),
                instructions = stringResource(R.string.instructions_3),
                modifier = modifier,
                imageOnClick = {
                    pageToLoad = 4
                }
            )
        else -> LemonadePage(
                    painterImage = painterResource(id = R.drawable.lemon_restart),
                    imageDesc = stringResource(R.string.empty_glass),
                    instructions = stringResource(R.string.instructions_4),
                    modifier = modifier,
                    imageOnClick = {
                        pageToLoad = 1
                    }
                )
    }
}

@Composable
fun LemonadePage(painterImage: Painter, imageDesc: String, instructions: String, imageOnClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(
            onClick = imageOnClick,
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.outlinedButtonColors(Color(0xFFB1F3D4))
        ) {
            Image(
                painter = painterImage,
                contentDescription = imageDesc,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = instructions,
            fontSize = 18.sp
        )
    }
}
