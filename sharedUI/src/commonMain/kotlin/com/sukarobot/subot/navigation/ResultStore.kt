package com.sukarobot.subot.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.input.key.Key

@Stable
class ResultStore {
    private val results = mutableMapOf<Any, Any?>()

    @Suppress("UNCHECKED_CAST")
    fun <T> getResult(key: Key, value: T?) = results[key] as? T

    fun <T> setResult(key: Any, value: T) {
        results[key] = value
    }

    fun clearResult(key: Any) {
        results.remove(key)
    }

    companion object {
        val Saver = Saver<ResultStore, Map<Any, Any?>>(
            save = { it.results.toMap() },
            restore = { ResultStore().apply { results.putAll(it) } }
        )
    }
}

@Composable
fun rememberResultStore(): ResultStore {
    return rememberSaveable(saver = ResultStore.Saver) {
        ResultStore()
    }
}