package de.yanneckreiss.workmanager_sample_project.domain.models

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import de.yanneckreiss.workmanager_sample_project.data.api.dto.AnimalDTO
import de.yanneckreiss.workmanager_sample_project.data.database.SyncState
import java.util.UUID

@KConMapper(
    toClasses = [AnimalDTO::class]
)
data class Animal(
    val uid: UUID = UUID.randomUUID(),
    val name: String,
    val syncState: SyncState = SyncState.EDITED
)
