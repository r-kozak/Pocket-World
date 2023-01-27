package com.kozak.pw.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kozak.pw.PwConstants
import com.kozak.pw.data.person.PersonItemDao
import com.kozak.pw.data.person.PersonItemEntity

@Database(entities = [PersonItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                // double check to prevent initialize INSTANCE variable twice from 2 different threads
                INSTANCE?.let { return it }

                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    PwConstants.APP_DATABASE_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

    abstract fun personItemDao(): PersonItemDao
}