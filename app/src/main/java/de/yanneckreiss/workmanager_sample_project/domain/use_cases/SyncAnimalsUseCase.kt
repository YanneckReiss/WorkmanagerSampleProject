package de.yanneckreiss.workmanager_sample_project.domain.use_cases

import de.yanneckreiss.kconmapper.generated.toAnimalDTO
import de.yanneckreiss.kconmapper.generated.toAnimalEntity
import de.yanneckreiss.workmanager_sample_project.data.api.BackendRepository
import de.yanneckreiss.workmanager_sample_project.data.api.base.APIResult
import de.yanneckreiss.workmanager_sample_project.data.api.dto.AnimalDTO
import de.yanneckreiss.workmanager_sample_project.data.database.SyncState
import de.yanneckreiss.workmanager_sample_project.data.database.animal.AnimalEntity
import de.yanneckreiss.workmanager_sample_project.data.database.animal.AnimalRepository
import de.yanneckreiss.workmanager_sample_project.domain.use_cases.base.UseCaseResult
import org.koin.core.annotation.Factory

@Factory
class SyncAnimalsUseCase(
    private val animalRepository: AnimalRepository,
    private val backendRepository: BackendRepository
) {

    suspend fun call(): UseCaseResult<Any> {
        val allUnSyncedAnimals: List<AnimalEntity> = animalRepository.getAllUnSyncedAnimals()
        val animalDTOs: List<AnimalDTO> = allUnSyncedAnimals.map(AnimalEntity::toAnimalDTO)

        return if (animalDTOs.isNotEmpty()) {
            when (backendRepository.saveAnimals(animalDTOs)) {
                is APIResult.Success -> getBackendAnimals()
                is APIResult.Error -> UseCaseResult.Failure()
            }
        } else {
            getBackendAnimals()
        }
    }

    private suspend fun getBackendAnimals(): UseCaseResult<Any> {
        return when (val result: APIResult<List<AnimalDTO>> = backendRepository.getAnimals()) {
            is APIResult.Success -> {
                val animals: List<AnimalEntity> = result.data.map { animalDTO: AnimalDTO ->
                    animalDTO.toAnimalEntity(SyncState.SYNCED)
                }
                animalRepository.saveAllAnimals(animals)
                UseCaseResult.Success(Any())
            }
            is APIResult.Error -> UseCaseResult.Failure()
        }
    }
}
