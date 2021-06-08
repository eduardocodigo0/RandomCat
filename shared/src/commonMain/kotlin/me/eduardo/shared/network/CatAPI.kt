package me.eduardo.shared.network

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import me.eduardo.shared.network.responses.CatRespose


class CatAPI {

    private val httpClient = HttpClient {
        install(JsonFeature){
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getCat(): CatRespose{
        return httpClient.get(catEndPoint)
    }


    companion object{

        private const val catEndPoint = "https://aws.random.cat/meow"

    }

}