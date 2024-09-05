package com.myfitnessbag.order.platform_module

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.myfitnessbag.order.core.util.PrefsDataStore
import com.myfitnessbag.order.core.util.constants.dataStoreFileName
import okio.Path.Companion.toPath


internal fun createDataStore(context: Context): PrefsDataStore {
    return PreferenceDataStoreFactory.createWithPath {
        context.filesDir.resolve(dataStoreFileName).absolutePath.toPath()
    }
}