package de.yanneckreiss.workmanager_sample_project.data.database.AnimalEntity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.yanneckreiss.workmanager_sample_project.data.database.SyncState
import de.yanneckreiss.workmanager_sample_project.data.database.animal.AnimalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(AnimalEntity: AnimalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(AnimalEntitys: List<AnimalEntity>)

    @Query("SELECT * FROM animals")
    fun watchAnimals(): Flow<List<AnimalEntity>>

    @Query("SELECT * FROM animals WHERE sync_state = :syncState")
    fun findAllBySyncState(syncState: SyncState): List<AnimalEntity>
}