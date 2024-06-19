package com.jetbrains.greeting

import App
import Calculator
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
            //App()
            CalcView()
        }
    }
}
@Composable
fun CalcView(){
    val displayText = remember {mutableStateOf("0")
    }
    var leftNumber by rememberSaveable { mutableStateOf(0) }
    var rightNumber by rememberSaveable{ mutableStateOf(0)}
    var operation by rememberSaveable{mutableStateOf("")}
    var complete by rememberSaveable{mutableStateOf(false)}
    if(complete && operation !=""){var answer by remember{ mutableStateOf(0) }
        when(operation){
            "+" -> answer =Calculator().add(leftNumber, rightNumber)
            "-" -> answer =Calculator().subtract(leftNumber, rightNumber)
            "*" -> answer =Calculator().multiply(leftNumber, rightNumber)
            "/" -> answer =Calculator().divide(leftNumber, rightNumber)

        }
        displayText.value =answer.toString()
    Column(modifier = Modifier.background(Color.LightGray)) {
        Row{ CalcDisplay(displayText)}
        Row{
            Column {
                for (i in 7 downTo 1 step 3){
                    CalcRow(startNum = i, numButtons = 3, display = displayText )
                }
                Row{
                    CalcNumericButton(number = 0, display = displayText )
                    CalcEqualsButton(display = displayText)
                }

            }
            Column {
                CalcOperationButton(operation = "+", display = displayText )
                CalcOperationButton(operation = "-", display = displayText )
                CalcOperationButton(operation = "*", display = displayText )
                CalcOperationButton(operation = "/", display = displayText )
            }
        }
    }
}
@Composable
fun CalcRow(startNum: Int, numButtons: Int, display: MutableState<String>){
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)){
        for (i in startNum ..<endNum){
            CalcNumericButton(number = i, display)}
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
    Button(onClick = {displayState.value = "=" }, modifier = Modifier.padding(4.dp)){
        Text(text = displayState.value)
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    App()
}