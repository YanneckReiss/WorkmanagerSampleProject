package de.yanneckreiss.workmanager_sample_project.core.injection

import androidx.room.Room
import androidx.work.WorkManager
import de.yanneckreiss.workmanager_sample_project.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import de.yanneckreiss.workmanager_sample_project.data.api.provideKtorClient

val dataModule = module {
    singleOf(::provideKtorClient)

    single {
        Room
            .databaseBuilder(androidContext(), AppDatabase::class.java, "aiImageGeneratorDB")
            .build()
    }

    single { get<AppDatabase>().animalDao() }

    single { WorkManager.getInstance(androidContext())}
}
