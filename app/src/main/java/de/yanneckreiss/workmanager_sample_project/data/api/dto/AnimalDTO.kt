package de.yanneckreiss.workmanager_sample_project.data.api.dto

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import de.yanneckreiss.workmanager_sample_project.core.serialization.UUIDSerializer
import de.yanneckreiss.workmanager_sample_project.data.database.animal.AnimalEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@KConMapper(toClasses = [AnimalEntity::class])
@Serializable
data class AnimalDTO(

    @Serializable(with = UUIDSerializer::class)
    @SerialName("uid")
    val uid: UUID,

    @SerialName("name")
    val name: String
)
