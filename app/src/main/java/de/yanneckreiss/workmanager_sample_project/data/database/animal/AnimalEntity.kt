package de.yanneckreiss.workmanager_sample_project.data.database.animal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import de.yanneckreiss.workmanager_sample_project.data.api.dto.AnimalDTO
import de.yanneckreiss.workmanager_sample_project.data.database.SyncState
import de.yanneckreiss.workmanager_sample_project.domain.models.Animal
import java.util.UUID

@KConMapper(
    toClasses = [Animal::class, AnimalDTO::class],
    fromClasses = [Animal::class]
)
@Entity(tableName = "animals")
data class AnimalEntity(

    @PrimaryKey
    @ColumnInfo(name = "uid")
    val uid: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "sync_state")
    val syncState: SyncState
)
