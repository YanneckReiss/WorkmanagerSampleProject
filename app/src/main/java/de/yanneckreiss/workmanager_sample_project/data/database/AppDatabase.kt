package de.yanneckreiss.workmanager_sample_project.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.yanneckreiss.workmanager_sample_project.data.database.AnimalEntity.AnimalDao
import de.yanneckreiss.workmanager_sample_project.data.database.animal.AnimalEntity

@Database(
    entities = [AnimalEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
}
