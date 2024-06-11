package com.jetbrains.greeting

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}
@Composable
fun CalcView(){
    val displayText = mutableStateOf("0")
    Column(modifier = Modifier.background(Color.LightGray)) {
        Row{ CalcDisplay(mutableStateOf("0"))}
        Row{}
    }
}
@Composable
fun CalcRow(startNum: Int, numButtons: Int, display: MutableState<String>){
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)){
        for (i in startNum<= ..<endNum){
            CalcNumericButton(number = i, display = mutableStateOf(i.toString()))}
    }
}
@Composable
fun CalcDisplay(display: MutableState<String>){
    Text(display.value, modifier = Modifier
        .height(50.dp)
        .padding(5.dp)
        .fillMaxWidth())
}
@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>){
    Button(
        modifier = Modifier.padding(4.dp), onClick = { display.value += number.toString()}
    ){
        Text(text = number.toString())
    }
}
@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>){
    Button(onClick = { /*empty value*/ }, modifier = Modifier.padding(4.dp)) {
        Text(text = operation)
    }
}


@Composable
fun CalcEqualsButton(display: MutableState<String>){
    val displayState = remember { mutableStateOf("=")}
    Button(onClick = {displayState.value = "0" }, modifier = Modifier.padding(4.dp)){
        Text(text = displayState.value)
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    App()
}