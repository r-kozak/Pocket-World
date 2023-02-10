package com.kozak.pw.domain.game

import android.util.Log
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.space.Universe
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random
import kotlin.random.nextInt

class StartNewGameUseCase(private val pwGameRepository: PwGameRepository) {

    companion object StartGameConstants {
        val UNIVERSES_COUNT_RANGE = 2..4
    }

    /**
     * @return
     *      true - if game started successful, otherwise - false
     */
    operator fun invoke(gameSpeed: GameSpeed): Boolean {
        return try {
            Log.d(PwConstants.LOG_TAG, "Starting new game with speed: $gameSpeed")

            Log.d(PwConstants.LOG_TAG, "Creating PwGame object")
            val newPwGame = PwGame(Clock.System.now().toLocalDateTime(TimeZone.UTC), gameSpeed)
            pwGameRepository.addNewPwGame(newPwGame)

            Log.d(PwConstants.LOG_TAG, "Generating Universes")
            repeat(Random.nextInt(UNIVERSES_COUNT_RANGE)) {
                Log.d(PwConstants.LOG_TAG, "Generating PwGod object")
                val newUniverse = Universe()
                // TODO save to DB and get its ID to
            }
//            Log.d(PwConstants.LOG_TAG, "Generating PwGod object")
//            val newPwGod = PwGod()

            true
        } catch (e: Exception) {
            Log.e(PwConstants.LOG_TAG, e.message ?: "Error occurred while starting new game")
            false
        }
    }
}