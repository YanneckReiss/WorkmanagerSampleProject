package de.yanneckreiss.workmanager_sample_project.domain.worker.sync_animals

import androidx.work.*
import de.yanneckreiss.workmanager_sample_project.domain.worker.sync_animals.SyncAnimalsWorker.Companion.UNIQUE_WORK_NAME
import org.koin.core.annotation.Factory

@Factory
class ScheduleSyncAnimalsScheduler(
 private val workManager: WorkManager
) {

    fun schedule() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncAnimalsWorkRequest = OneTimeWorkRequestBuilder<SyncAnimalsWorker>()
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniqueWork(UNIQUE_WORK_NAME, ExistingWorkPolicy.APPEND_OR_REPLACE, syncAnimalsWorkRequest)
    }
}
