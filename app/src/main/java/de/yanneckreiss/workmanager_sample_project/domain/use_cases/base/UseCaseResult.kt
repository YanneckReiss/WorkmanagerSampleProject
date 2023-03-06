package de.yanneckreiss.workmanager_sample_project.domain.use_cases.base

sealed interface UseCaseResult<T> {
    data class Success<T>(val resultObject: T) : UseCaseResult<T>
    class Failure<T> : UseCaseResult<T>
}