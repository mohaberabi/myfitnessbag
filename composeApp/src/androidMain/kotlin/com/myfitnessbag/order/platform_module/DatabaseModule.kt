package com.myfitnessbag.order.platform_module

import android.content.Context
import androidx.room.Room
import com.myfitnessbag.order.core.data.source.local.database.AppDatabase
import org.koin.dsl.module


internal fun createDatabase(
    context: Context,
): AppDatabase {
    val dbFile = context.getDatabasePath("myfitnessbag.db")
    return Room.databaseBuilder<AppDatabase>(
        context,
        name = dbFile.absolutePath,
    ).build()
}