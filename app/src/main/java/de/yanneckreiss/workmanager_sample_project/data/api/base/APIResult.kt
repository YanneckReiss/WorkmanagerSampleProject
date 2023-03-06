package de.yanneckreiss.workmanager_sample_project.data.api.base

/**
 * Can be extended with more fine-granular types.
 */
sealed interface APIResult<T> {
    data class Success<T>(val data: T) : APIResult<T>
    class Error<T> : APIResult<T>
}
