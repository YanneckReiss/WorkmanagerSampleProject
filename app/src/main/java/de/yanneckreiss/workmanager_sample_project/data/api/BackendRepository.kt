package de.yanneckreiss.workmanager_sample_project.data.api

import de.yanneckreiss.workmanager_sample_project.data.api.base.APIResult
import de.yanneckreiss.workmanager_sample_project.data.api.dto.AnimalDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.koin.core.annotation.Single

/**
 * Is only dummy and mocks the API
 */
@Single
class BackendRepository(
    private val ktorClient: HttpClient
) {

    private var inMemoryAnimals = mutableListOf<AnimalDTO>()

    fun getAnimals(): APIResult<List<AnimalDTO>> {
        return APIResult.Success(inMemoryAnimals)
    }

    fun saveAnimals(animalDTOs: List<AnimalDTO>): APIResult<List<AnimalDTO>> {
        inMemoryAnimals.addAll(animalDTOs)
        return APIResult.Success(inMemoryAnimals.toList())
    }

    /*
    * Just to get an idea how the API implementation would look like.
    */

//    suspend fun getAnimals(): APIResult<List<AnimalDTO>> {
//
//        val result = ktorClient.get("/animals")
//
//        return when (result.status) {
//            HttpStatusCode.OK -> APIResult.Success(result.body())
//            else -> APIResult.Error()
//        }
//    }
//
//    suspend fun saveAnimals(animalDTOs: List<AnimalDTO>): APIResult<AnimalDTO> {
//
//        val result: HttpResponse = ktorClient.post("/animals") {
//            setBody(animalDTOs)
//        }
//
//        return when (result.status) {
//            HttpStatusCode.OK -> APIResult.Success(result.body())
//            else -> APIResult.Error()
//        }
//    }
}
