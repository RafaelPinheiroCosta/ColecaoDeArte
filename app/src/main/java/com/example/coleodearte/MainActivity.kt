package com.example.coleodearte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coleodearte.ui.theme.ColeçãoDeArteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColeçãoDeArteTheme {
                    Espositor()
            }
        }
    }
}

@Preview
@Composable
fun Espositor() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        var imagemTexto by remember { mutableStateOf(1) }

        val idImagem = when (imagemTexto) {
            1 -> R.drawable.foto1
            2 -> R.drawable.foto2
            3 -> R.drawable.foto3
            4 -> R.drawable.foto4
            5 -> R.drawable.foto5
            6 -> R.drawable.foto6
            else -> R.drawable.foto1
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

//------------------------------TÍTULO----------------------------------
            Text(
                "Album de Fotos",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
//----------------------------MOLDURA DA IMAGEM-----------------------------------
            MoldudaImagem(
                    idImagem = idImagem
            ){
                when {
                    imagemTexto < 6 -> imagemTexto++
                    else -> imagemTexto = 1
                }
            }
//---------------------------DESCRIÇÃO DA IMAGEM------------------------------------
            DescricaoImagem()


        }
    //---------------------------RODAPÉ DE NAVEGAÇÃO------------------------------------
    Row(
        verticalAlignment =  Alignment.Bottom,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        if(imagemTexto > 1){
            BotoesNavegacao(
                "Anterior",
                Alignment.Start,
                Modifier
            ) { imagemTexto-- }
        }
        if(imagemTexto < 6){
            BotoesNavegacao(
                "Próximo",
                Alignment.End,
                Modifier.fillMaxWidth()
            ) { imagemTexto++ }
        }
    }
//---------------------------------------------------------------
    }
}

@Composable
fun BotoesNavegacao(
    textoBotao: String,
    horizontal: Alignment.Horizontal,
    modifier: Modifier,
    onClickBotao:()->Unit
){
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = state,
        enter = slideInHorizontally()
    ) {

        Column(
            horizontalAlignment = horizontal,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Button(
                onClick = onClickBotao,
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                shape =  RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color.DarkGray),
                elevation = ButtonDefaults.buttonElevation(4.dp),
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = textoBotao,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun DescricaoImagem(){

    Surface(
        tonalElevation = 30.dp,
        shadowElevation = 10.dp,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .border(BorderStroke(1.dp, Color.DarkGray))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Título",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp, start = 10.dp)
            )

            Divider(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 10.dp, end = 10.dp)
            )
            Text(
                text = "Descrição:",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun MoldudaImagem(
    idImagem:Int,
    onClickImagem:()->Unit
){
    Surface(
        tonalElevation = 30.dp,
        shadowElevation = 10.dp,
        color = Color.White,
        modifier = Modifier
            .padding(10.dp)
            .border(BorderStroke(1.dp, Color.DarkGray))
    ) {
        Image(
            painter = painterResource(id = idImagem),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .padding(20.dp)
                .clickable(onClick =  onClickImagem)
                .border(BorderStroke(1.dp, Color.DarkGray))
        )
    }
}

