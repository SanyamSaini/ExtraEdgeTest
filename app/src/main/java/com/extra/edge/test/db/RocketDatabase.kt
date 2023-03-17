package com.extra.edge.test.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.extra.edge.test.model.Rocket

@Database(entities = [Rocket::class], version = 1)
@TypeConverters(Converters::class)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketDao

    companion object {
        @Volatile
        private var INSTANCE: RocketDatabase? = null

        fun getInstance(context: Context): RocketDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, RocketDatabase::class.java, "rocketDB")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}