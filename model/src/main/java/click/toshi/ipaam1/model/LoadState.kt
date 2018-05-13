package click.toshi.ipaam1.model

sealed class LoadState {
    object Initialized : LoadState()
    object Loading : LoadState()
    object Finished : LoadState()
    data class Error(val e: Throwable) : LoadState()
}
