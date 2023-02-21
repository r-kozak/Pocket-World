package com.kozak.pw.domain.game

import android.util.Log
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.Size
import com.kozak.pw.domain.space.*
import org.koin.java.KoinJavaComponent.inject
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.random.nextLong

class StartNewGameUseCase {

    private val pwGameRepository: PwGameRepository by inject(PwGameRepository::class.java)
    private val universeRepository: UniverseRepository by inject(UniverseRepository::class.java)
    private val galaxyRepository: GalaxyRepository by inject(GalaxyRepository::class.java)
    private val starSystemRepository: StarSystemRepository by inject(StarSystemRepository::class.java)
    private val starRepository: StarRepository by inject(StarRepository::class.java)
    private val planetRepository: PlanetRepository by inject(PlanetRepository::class.java)

    companion object StartGameConstants {
        private val UNIVERSES_COUNT_RANGE = 2..4
        private val GALAXIES_COUNT_RANGE = 1..2 // galaxies in one Universe

        private val STAR_SYSTEMS_COUNT_RANGE = 2..7 // star systems in one Galaxy

        private val STARS_COUNT_RANGE = 1..3 // stars in one StarSystem
        private val STAR_MASS_RANGE = 100_000_000_000L..999_999_999_999L
        private val STAR_SIZE_RANGE = 20..50

        private val PLANETS_COUNT_RANGE = 1..12 // planets of one Star
        private val PLANET_MASS_RANGE = 100_000_000L..999_999_999L
        private val PLANETS_SIZE_RANGE = 1..5
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
            val newStarSystemsIds = generateStarSystems(newGalaxiesIds)
            generateStars(newStarSystemsIds)

            true
        } catch (e: Exception) {
            Log.e(PwConstants.LOG_TAG, e.stackTraceToString())
            // rollback all changes when exception occurred while world creation
            pwGameRepository.destroyCurrentWorld()
            false
        }
    }

    private suspend fun generateUniverses(): List<Long> {
        Log.d(PwConstants.LOG_TAG, "Generating Universes")

        val newUniversesIds = mutableListOf<Long>()
        repeat(Random.nextInt(UNIVERSES_COUNT_RANGE)) {
            Log.d(PwConstants.LOG_TAG, "Generating Universe object...")
            val newUniverse = Universe().apply {
                health = Random.nextInt(Universe.INITIAL_HEALTH)
            }
            Log.d(PwConstants.LOG_TAG, "Name of new Universe = ${newUniverse.name}")
            // save universe to DB and get its ID to put into dependant objects (galaxies)
            val id = universeRepository.insert(newUniverse)
            newUniversesIds += id
        }
        return newUniversesIds
    }

    private suspend fun generateGalaxies(universesIds: List<Long>): List<Long> {
        Log.d(PwConstants.LOG_TAG, "Generating Galaxies")

        val newGalaxiesIds = mutableListOf<Long>()
        for (universeId in universesIds) {
            repeat(Random.nextInt(GALAXIES_COUNT_RANGE)) {
                val newGalaxy = Galaxy(universeId)
                // save galaxy to DB and get its ID to put into dependant objects (star systems)
                val id = galaxyRepository.insert(newGalaxy)
                newGalaxiesIds += id
            }
        }
        return newGalaxiesIds
    }

    private suspend fun generateStarSystems(galaxiesIds: List<Long>): List<Long> {
        Log.d(PwConstants.LOG_TAG, "Generating Star Systems")

        val newStarSystemsIds = mutableListOf<Long>()
        for (galaxyId in galaxiesIds) {
            repeat(Random.nextInt(STAR_SYSTEMS_COUNT_RANGE)) {
                val newStarSystem = StarSystem(galaxyId)
                // save star system to DB and get its ID to put into dependant objects (stars)
                val id = starSystemRepository.insert(newStarSystem)
                newStarSystemsIds += id
            }
        }
        return newStarSystemsIds
    }

    private suspend fun generateStars(starSystemsIds: List<Long>) {
        Log.d(PwConstants.LOG_TAG, "Generating Star Systems")

        for (starSystemId in starSystemsIds) {
            val starsIds = mutableListOf<Long>()
            repeat(Random.nextInt(STARS_COUNT_RANGE)) {
                val mass = Random.nextLong(STAR_MASS_RANGE)
                val sideSize = Random.nextInt(STAR_SIZE_RANGE)
                val size = Size(sideSize, sideSize)

                val newStar = Star(mass, size, starSystemId)
                // save star to DB and get its ID to put into dependant objects (star systems)
                val id = starRepository.insert(newStar)
                starsIds += id
            }
            // generate planets just for one star of the star system
            generatePlanets(starsIds.random())
        }
    }

    private suspend fun generatePlanets(starId: Long) {
        val planetsIds = mutableListOf<Long>()
        repeat(Random.nextInt(PLANETS_COUNT_RANGE)) {
            val mass = Random.nextLong(PLANET_MASS_RANGE)
            val sideSize = Random.nextInt(PLANETS_SIZE_RANGE)
            val size = Size(sideSize, sideSize)
            // save planet to DB and get its ID to put into dependant objects (star systems)
            val id = planetRepository.insert(Planet(mass, size, starId))
            planetsIds += id
        }
    }
}