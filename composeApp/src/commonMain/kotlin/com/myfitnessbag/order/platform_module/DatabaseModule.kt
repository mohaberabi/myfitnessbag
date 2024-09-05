package com.myfitnessbag.order.platform_module

import androidx.room.RoomDatabaseConstructor
import com.myfitnessbag.order.core.data.source.local.database.AppDatabase

expect object AppDatabaseCreator : RoomDatabaseConstructor<AppDatabase>
