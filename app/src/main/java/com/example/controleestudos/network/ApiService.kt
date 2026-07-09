package com.example.controleestudos.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class DicaEstudo(
    val id: Int = 0,
    val title: String,
    val body: String
)

interface ApiService {
    @GET("posts")
    suspend fun buscarDicas(): List<DicaEstudo>

    @POST("posts")
    suspend fun enviarMetaConcluida(@Body dica: DicaEstudo): DicaEstudo
}