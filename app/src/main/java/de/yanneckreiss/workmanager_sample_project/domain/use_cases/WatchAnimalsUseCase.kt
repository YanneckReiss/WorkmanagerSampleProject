package de.yanneckreiss.workmanager_sample_project.domain.use_cases

import de.yanneckreiss.kconmapper.generated.toAnimal
import de.yanneckreiss.workmanager_sample_project.data.database.animal.AnimalEntity
import de.yanneckreiss.workmanager_sample_project.data.database.animal.AnimalRepository
import de.yanneckreiss.workmanager_sample_project.domain.models.Animal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class WatchAnimalsUseCase(
    private val repository: AnimalRepository
) {

    fun call(): Flow<List<Animal>> = repository
        .watchAnimals()
        .map { animals -> animals.map(AnimalEntity::toAnimal) }
}
