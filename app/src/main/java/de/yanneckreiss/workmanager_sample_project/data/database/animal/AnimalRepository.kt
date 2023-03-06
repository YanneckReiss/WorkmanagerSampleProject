package de.yanneckreiss.workmanager_sample_project.data.database.animal

import de.yanneckreiss.workmanager_sample_project.data.database.AnimalEntity.AnimalDao
import de.yanneckreiss.workmanager_sample_project.data.database.SyncState
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class AnimalRepository(private val animalDao: AnimalDao) {

    suspend fun saveAnimal(animal: AnimalEntity) = animalDao.save(animal)

    suspend fun saveAllAnimals(animals: List<AnimalEntity>) = animalDao.saveAll(animals)

    fun watchAnimals(): Flow<List<AnimalEntity>> = animalDao.watchAnimals()

    suspend fun getAllUnSyncedAnimals(): List<AnimalEntity> = animalDao.findAllBySyncState(SyncState.EDITED)
}
