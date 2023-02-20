package com.kozak.pw.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kozak.pw.PwConstants
import com.kozak.pw.data.galaxy.GalaxyDao
import com.kozak.pw.data.galaxy.GalaxyEntity
import com.kozak.pw.data.game.PwGameDao
import com.kozak.pw.data.news.NewsDao
import com.kozak.pw.data.news.NewsEntity
import com.kozak.pw.data.person.PersonDao
import com.kozak.pw.data.person.PersonEntity
import com.kozak.pw.data.star.PlanetDao
import com.kozak.pw.data.star.PlanetEntity
import com.kozak.pw.data.star.StarDao
import com.kozak.pw.data.star.StarEntity
import com.kozak.pw.data.star_system.StarSystemDao
import com.kozak.pw.data.star_system.StarSystemEntity
import com.kozak.pw.data.universe.UniverseDao
import com.kozak.pw.data.universe.UniverseEntity
import com.kozak.pw.domain.game.PwGameEntity
import kotlin.reflect.KFunction0

@Database(
    entities = [PersonEntity::class, NewsEntity::class, PwGameEntity::class, UniverseEntity::class,
        GalaxyEntity::class, StarSystemEntity::class, StarEntity::class, PlanetEntity::class],
    version = 1,
    exportSchema = true
)
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
                )
                    //.fallbackToDestructiveMigration() // TODO remove on production
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

    abstract fun personDao(): PersonDao
    abstract fun newsDao(): NewsDao
    abstract fun gameDao(): PwGameDao
    abstract fun universeDao(): UniverseDao
    abstract fun galaxyDao(): GalaxyDao
    abstract fun starSystemDao(): StarSystemDao
    abstract fun starDao(): StarDao
    abstract fun planetDao(): PlanetDao

    // TODO on adding a DAO's function - add it to this list too for deletion DAO's table data while world recreation
    fun getAllDAOs(): List<KFunction0<BaseDao<out BaseEntity>>> {
        return listOf(
            ::personDao,
            ::newsDao,
            ::gameDao,
            ::universeDao,
            ::galaxyDao,
            ::starSystemDao,
            ::starDao,
            ::planetDao
        )
    }

}