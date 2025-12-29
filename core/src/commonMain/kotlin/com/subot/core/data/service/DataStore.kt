package com.subot.core.data.service

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath

internal const val dataStoreFileName = "subot.preferences_pb"

@OptIn(InternalCoroutinesApi::class)
private val lock = SynchronizedObject()

private lateinit var dataStore: DataStore<Preferences>

@OptIn(InternalCoroutinesApi::class)
internal fun getDataStore(producePath: () -> String): DataStore<Preferences> {
    return synchronized(lock) {
        if (::dataStore.isInitialized) {
            dataStore
        } else {
            PreferenceDataStoreFactory.createWithPath(produceFile = { producePath().toPath()})
                .also { dataStore = it }
        }
    }
}

expect fun createPreferencesDataStore(): DataStore<Preferences>