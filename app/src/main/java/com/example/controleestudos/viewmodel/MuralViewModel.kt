package com.example.controleestudos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controleestudos.network.DicaEstudo
import com.example.controleestudos.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MuralViewModel : ViewModel() {
    private val _dicas = MutableStateFlow<List<DicaEstudo>>(emptyList())
    val dicas: StateFlow<List<DicaEstudo>> = _dicas

    private val _mensagem = MutableStateFlow("")
    val mensagem: StateFlow<String> = _mensagem

    init {
        buscarDicasDaApi()
    }

    private fun buscarDicasDaApi() {
        viewModelScope.launch {
            try {
                val resultado = RetrofitClient.apiService.buscarDicas()
                _dicas.value = resultado.take(10)
            } catch (e: Exception) {
                _mensagem.value = "Erro ao buscar dados da internet."
            }
        }
    }

    fun enviarMeta(titulo: String) {
        viewModelScope.launch {
            try {
                val novaDica = DicaEstudo(title = titulo, body = "Enviado com sucesso do meu App!")
                val resposta = RetrofitClient.apiService.enviarMetaConcluida(novaDica)
                _mensagem.value = "Sucesso! POST simulado do item ID: ${resposta.id}"
            } catch (e: Exception) {
                _mensagem.value = "Erro ao enviar dados."
            }
        }
    }
}