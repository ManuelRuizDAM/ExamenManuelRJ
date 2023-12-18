package com.example.examenmanuelrj.ui

import androidx.lifecycle.ViewModel
import com.example.ExamenAndroidDAMTheme.data.DataSource
import com.example.ExamenAndroidDAMTheme.data.LoteriaTipo
import com.example.examenmanuelrj.data.ApostarUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ApostarViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(ApostarUIState())
    val uiState: StateFlow<ApostarUIState> = _uiState.asStateFlow()


    fun setLoteriaAApostar(nameLotreria: String) {
        _uiState.update { currentState ->
            currentState.copy(
                LoteriaAApostar = nameLotreria
            )
        }
    }

    fun setCantidadAApostar(cantidadLoteria: String) {
        _uiState.update { currentState ->
            currentState.copy(
                CantidadAApostar = cantidadLoteria
            )
        }
    }

    fun setApuestaEnTarjeta(cantidadApuesta:String,premio:Int) {
        if (cantidadApuesta!="0"){
            val numberLoteria= (1..4).random()
            val ganador= (1..4).random()
            _uiState.update { currentState ->
                currentState.copy(
                    VecesApostada = uiState.value.VecesApostada + 1,
                    dineroGastado = uiState.value.dineroGastado + Integer.parseInt(cantidadApuesta)

                )
            }

            if (numberLoteria==ganador){
                _uiState.update { currentState ->
                    currentState.copy(
                        dineroGanado = uiState.value.dineroGanado +(premio* Integer.parseInt(cantidadApuesta)),
                        Ganado = true
                    )
                }
            }else{
                _uiState.update { currentState ->
                    currentState.copy(
                        Ganado = false
                    )
                }
            }
        }
    }

    fun setApuestaEscrita(){
        val nombre:String=uiState.value.LoteriaAApostar
        val cantidadApuesta:String=uiState.value.CantidadAApostar

        val loterias= DataSource.loterias
        var loteriaSeleccionada: LoteriaTipo? =null

        for (loteria in loterias){
            if (loteria.nombre==nombre){
                loteriaSeleccionada=loteria
            }
        }
        if (loteriaSeleccionada!=null){
            if (cantidadApuesta!="0"){
                val numberLoteria= (1..4).random()
                val ganador= (1..4).random()
                _uiState.update { currentState ->
                    currentState.copy(
                        VecesApostada = uiState.value.VecesApostada + 1,
                        dineroGastado = uiState.value.dineroGastado + Integer.parseInt(cantidadApuesta)

                    )
                }

                if (numberLoteria==ganador){
                    _uiState.update { currentState ->
                        currentState.copy(
                            dineroGanado = uiState.value.dineroGanado +(loteriaSeleccionada.premio* Integer.parseInt(cantidadApuesta)),
                            Ganado = true
                        )
                    }
                }else{
                    _uiState.update { currentState ->
                        currentState.copy(
                            Ganado = false
                        )
                    }
                }
            }
        }

    }
}