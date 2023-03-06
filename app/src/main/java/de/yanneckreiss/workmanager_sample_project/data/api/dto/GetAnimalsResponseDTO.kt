package de.yanneckreiss.workmanager_sample_project.data.api.dto

import kotlinx.serialization.SerialName

data class GetAnimalsResponseDTO(

    @SerialName("animals")
    val animals: List<AnimalDTO>
)
