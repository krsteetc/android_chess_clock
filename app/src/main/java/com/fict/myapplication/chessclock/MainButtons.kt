package com.fict.myapplication.chessclock

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClockButton(rotateAngle : Float, checkIt : Boolean){

    Button(onClick = {
                   //Modif  countTime.background(DarkGreen)
                     },

        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){

        Column(

            modifier = Modifier.weight(45f),
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
    Image(
        painterResource(R.drawable.play_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable { /*TODO*/ }
    )

}


@Composable
fun ResetButton(){
    Image(
        painterResource(R.drawable.replay_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable { /*TODO*/ }
    )
}

@Composable
fun TimeButton(){
    Image(
        painterResource(R.drawable.time_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable { /*TODO*/ }
    )
}

@Composable
fun SoundButton(){
    Image(
        painterResource(R.drawable.sound_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable { /*TODO*/ }
    )
}
