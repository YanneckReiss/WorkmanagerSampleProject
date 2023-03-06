package de.yanneckreiss.workmanager_sample_project.domain.worker.sync_animals

import android.content.Context
import androidx.work.*
import de.yanneckreiss.workmanager_sample_project.domain.use_cases.SyncAnimalsUseCase
import de.yanneckreiss.workmanager_sample_project.domain.use_cases.base.UseCaseResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class SyncAnimalsWorker(context: Context, workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters), KoinComponent {

    companion object {
        const val UNIQUE_WORK_NAME = "ScheduleSyncAnimalsWorker"
    }

    private val syncAnimalsUseCase: SyncAnimalsUseCase by inject()

    override suspend fun doWork(): Result {
        return when(syncAnimalsUseCase.call()) {
            is UseCaseResult.Success -> Result.success()
            is UseCaseResult.Failure -> Result.retry()
        }
    }
}
