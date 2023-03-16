package com.fict.myapplication.chessclock

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.sp

@Composable
fun ClockButton(size : Float, rotateAngle : Float, checkIt : Boolean){

    Button(onClick = { /*TODO*/ },

        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(size)) {

        Column(

            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            if(checkIt) {

                Text(
                    text = "10:00",
                    modifier = Modifier.rotate(rotateAngle),
                    fontSize = 50.sp
                )

                Text(
                    text = "Moves: ",
                    modifier = Modifier.rotate(rotateAngle),
                )

            }else{

                Text(
                    text = "Moves: ",
                    modifier = Modifier.rotate(rotateAngle),
                )

                Text(
                    text = "10:00",
                    modifier = Modifier.rotate(rotateAngle),
                    fontSize = 50.sp
                )

            }
        }
    }
}

@Composable
fun PlayButton(){
    Button(onClick = { /*TODO*/ }) {

        //Icon(painter = painterResource(id = R.drawable.play_button), contentDescription = "play")
    }
}


@Composable
fun ResetButton(){
    Button(onClick = { /*TODO*/ }) {

    }
}

@Composable
fun TimeButton(){
    Button(onClick = { /*TODO*/ }) {

    }
}

@Composable
fun SoundButton(){
    Button(onClick = { /*TODO*/ }) {

    }
}
