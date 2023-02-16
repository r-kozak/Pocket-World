package com.kozak.pw.domain.game

import android.util.Log
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.space.Galaxy
import com.kozak.pw.domain.space.GalaxyRepository
import com.kozak.pw.domain.space.Universe
import com.kozak.pw.domain.space.UniverseRepository
import org.koin.java.KoinJavaComponent.inject
import kotlin.random.Random
import kotlin.random.nextInt

class StartNewGameUseCase {

    private val pwGameRepository: PwGameRepository by inject(PwGameRepository::class.java)
    private val universeRepository: UniverseRepository by inject(UniverseRepository::class.java)
    private val galaxyRepository: GalaxyRepository by inject(GalaxyRepository::class.java)

    companion object StartGameConstants {
        val UNIVERSES_COUNT_RANGE = 2..4
        val GALAXIES_COUNT_RANGE = 1..2 // galaxies in one Universe
    }

    /**
     * @return
     *      true - if game started successful, otherwise - false
     */
    suspend operator fun invoke(gameSpeed: GameSpeed): Boolean {
        return try {
            if (pwGameRepository.gameInstanceExists()) {
                throw RuntimeException("Game instance already exists! Delete all old data before creating a new world!")
            }
            Log.d(PwConstants.LOG_TAG, "Starting new game with speed: $gameSpeed")
            Log.d(PwConstants.LOG_TAG, "Creating PwGame object")
            pwGameRepository.insert(PwGame(gameSpeed))

            val newUniversesIds = generateUniverses()
            val newGalaxiesIds = generateGalaxies(newUniversesIds)

            true
        } catch (e: Exception) {
            Log.e(PwConstants.LOG_TAG, e.stackTraceToString())
            // rollback all changes when exception occurred while world creation
            pwGameRepository.destroyCurrentWorld()
            false
        }
    }

    private suspend fun generateUniverses(): MutableList<Long> {
        Log.d(PwConstants.LOG_TAG, "Generating Universes")

        val newUniversesIds = mutableListOf<Long>()
        repeat(Random.nextInt(UNIVERSES_COUNT_RANGE)) {
            Log.d(PwConstants.LOG_TAG, "Generating Universe object...")
            val newUniverse = Universe(Random.nextInt(Universe.INITIAL_HEALTH))
            Log.d(PwConstants.LOG_TAG, "Name of new Universe = ${newUniverse.name}")
            // save universe to DB and get its ID to put into dependant objects (galaxies)
            val id = universeRepository.insert(newUniverse)
            newUniversesIds += id
        }
        return newUniversesIds
    }

    private suspend fun generateGalaxies(newUniversesIds: MutableList<Long>): MutableList<Long> {
        Log.d(PwConstants.LOG_TAG, "Generating Galaxies")

        val newGalaxiesIds = mutableListOf<Long>()
        for (universeId in newUniversesIds) {
            repeat(Random.nextInt(GALAXIES_COUNT_RANGE)) {
                val newGalaxy = Galaxy(universeId)
                // save galaxy to DB and get its ID to put into dependant objects (star systems)
                val id = galaxyRepository.insert(newGalaxy)
                newGalaxiesIds += id
            }
        }
        return newGalaxiesIds
    }
}