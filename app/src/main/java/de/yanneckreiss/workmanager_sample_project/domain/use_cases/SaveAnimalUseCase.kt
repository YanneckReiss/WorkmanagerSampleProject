package de.yanneckreiss.workmanager_sample_project.domain.use_cases

import de.yanneckreiss.kconmapper.generated.toAnimalEntity
import de.yanneckreiss.workmanager_sample_project.data.database.animal.AnimalEntity
import de.yanneckreiss.workmanager_sample_project.data.database.animal.AnimalRepository
import de.yanneckreiss.workmanager_sample_project.domain.models.Animal
import de.yanneckreiss.workmanager_sample_project.domain.worker.sync_animals.ScheduleSyncAnimalsWorker
import org.koin.core.annotation.Factory

@Factory
class SaveAnimalUseCase(
    private val repository: AnimalRepository,
    private val scheduleSyncAnimalsWorker: ScheduleSyncAnimalsWorker
) {

    suspend fun call(animal: Animal) {

        val animalToSave: AnimalEntity = animal.toAnimalEntity()
        repository.saveAnimal(animalToSave)

        scheduleSyncAnimalsWorker.schedule()
    }
}
