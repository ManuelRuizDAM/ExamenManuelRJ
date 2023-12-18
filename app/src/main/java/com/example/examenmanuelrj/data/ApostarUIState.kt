package com.example.examenmanuelrj.data

data class ApostarUIState(
    val CantidadAApostar: String = "0",
    val LoteriaAApostar: String = "",
    val VecesApostada: Int =0,
    val dineroGastado: Int = 0,
    val dineroGanado: Int=0,
    val Ganado:Boolean=false
)
