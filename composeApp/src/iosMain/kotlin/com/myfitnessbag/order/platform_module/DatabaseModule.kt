package com.myfitnessbag.order.platform_module

import androidx.room.Room
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.myfitnessbag.order.core.data.source.local.database.AppDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask


internal fun createDatabase(): AppDatabase {
    val dbFilePath = documentDirectory() + "/myfitnessbag.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
    ).setDriver(BundledSQLiteDriver()).build()

}


actual object AppDatabaseCreator : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase = IosKoinComponent.appDatabase
}


internal object IosKoinComponent : KoinComponent {
    val appDatabase by inject<AppDatabase>()
}

@OptIn(ExperimentalForeignApi::class)
internal fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}