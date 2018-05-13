package click.toshi.ipaam1.util

import timber.log.Timber

fun defaultErrorHandler(): (Throwable) -> Unit = { e -> Timber.e(e) }
