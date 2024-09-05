package com.myfitnessbag.order.core.data.source.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.myfitnessbag.order.core.data.source.local.database.dao.CartItemDao
import com.myfitnessbag.order.core.data.source.local.database.entity.CartItemEntity
import com.myfitnessbag.order.platform_module.AppDatabaseCreator


@Database(
    entities = [
        CartItemEntity::class,
    ], version = 1
)
@ConstructedBy(AppDatabaseCreator::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartDao(): CartItemDao
}


