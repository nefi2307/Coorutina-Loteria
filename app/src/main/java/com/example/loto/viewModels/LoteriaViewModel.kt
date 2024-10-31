package com.example.loto.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class LoteriaViewModel: ViewModel() {
    private val _lotoNumbers = mutableStateOf(emptyList<Int>()) // inicializas lista vacia,
    val lotonumbers: State<List<Int>> = _lotoNumbers // para la otra vista (LoteriaView) lo gráfico
    var isLoading by mutableStateOf(false)

    private suspend fun GenerateNumbers()
    {
        var num =6 // para repetir el ciclo y mostrar los numeros
        while (num!=0)
        {
            delay(500) // para simular la carga
            val numRand = Random.nextInt(100+1) // genera el numero random
            _lotoNumbers.value += listOf(numRand)

            num--
        }
    }

    fun Llamada(){
        viewModelScope.launch {
            isLoading = true
            try {
                GenerateNumbers()
            }catch (e:Exception){
                print("Error ${e.message}")
            }finally {
                isLoading = false
            }
        }
    }
}