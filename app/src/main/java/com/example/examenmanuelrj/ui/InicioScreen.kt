package com.example.examenmanuelrj.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ExamenAndroidDAMTheme.data.DataSource.loterias
import com.example.ExamenAndroidDAMTheme.data.LoteriaTipo
import com.example.examenmanuelrj.data.ApostarUIState
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    viewModel: ApostarViewModel,
    uiState: ApostarUIState,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Bienvenido a apuestas Manuel Ruiz Jimenez")
        LazyRow(modifier = modifier) {
            items(loterias) { loteria ->
                LoteriaCard(
                    loteria = loteria,
                    modifier = Modifier.padding(8.dp),
                    ApostarEnTarjeta={viewModel.setApuestaEnTarjeta(uiState.CantidadAApostar,loteria.premio)}
                )
            }
        }

        Row(
            modifier=modifier
                .fillMaxWidth()
        ) {

            TextField(
                value = uiState.LoteriaAApostar,
                onValueChange = {
                    viewModel.setLoteriaAApostar(it)
                },
                singleLine = true,
                label = { Text("Apostar") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = modifier
                    .weight(1f)
                    .padding(10.dp)
            )

            TextField(
                value = uiState.CantidadAApostar,
                onValueChange = {
                    viewModel.setCantidadAApostar(it)
                },
                singleLine = true,
                label = { Text("Dinero Apostado") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = modifier
                    .weight(1f)
                    .padding(10.dp)
            )
        }

        Button(
            onClick = {viewModel.setApuestaEscrita()},
            modifier = modifier.fillMaxWidth()
        ) {

            Text(text = "Jugar loteria escrita")
        }
        
        Column(
            modifier= modifier
                .fillMaxWidth()
                .padding(80.dp)
        ) {
            if (uiState.VecesApostada==0){
                Text(text = "No has ugado ninguna loteria")
            }else if (uiState.Ganado){
                Text(text = "has ganado")
            }else{
                Text(text = "has perdido")
            }

            Text(text = "has jugado ${uiState.VecesApostada} veces en loteria")
            Text(text = "has gastado ${uiState.dineroGastado} euros en loteria")
            Text(text = "has ganado ${uiState.dineroGanado} euros en loteria")
        }
        
        Button(
            onClick = onNextButtonClicked,
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Cambiar pantalla")
        }
    }
}

@Composable
fun LoteriaCard(
    loteria: LoteriaTipo,
    modifier: Modifier = Modifier,
    ApostarEnTarjeta:()->Unit,
    ) {
    Card(modifier = modifier) {
        Column {
            Text(
                text = "Nombre: "+loteria.nombre,
                modifier = Modifier.padding(20.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Premio: "+loteria.premio,
                modifier = Modifier.padding(20.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Button(
                onClick =  ApostarEnTarjeta ,
                modifier=Modifier.fillMaxWidth()
            ) {
                Text(
                    text="Apostar",
                    textAlign = TextAlign.Center,
                    modifier=Modifier.fillMaxWidth(),
                )
            }
        }
    }
}