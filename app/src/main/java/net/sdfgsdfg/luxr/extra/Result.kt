package net.sdfgsdfg.luxr.extra

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    object NoData : Result<Nothing>()

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorType: Throwable) : Result<Nothing>()

    fun successData(): T? {
        return (this as? Success)?.data
    }
}
