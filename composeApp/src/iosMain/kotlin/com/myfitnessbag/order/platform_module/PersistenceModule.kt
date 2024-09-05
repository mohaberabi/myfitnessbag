package com.myfitnessbag.order.platform_module

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.myfitnessbag.order.core.util.PrefsDataStore
import com.myfitnessbag.order.core.util.constants.dataStoreFileName
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask


internal fun createDataStore(): PrefsDataStore {
    return PreferenceDataStoreFactory.createWithPath {
        createDocsPath().toPath()
    }
}


@OptIn(ExperimentalForeignApi::class)
internal fun createDocsPath(): String {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory).path + "/$dataStoreFileName"
}