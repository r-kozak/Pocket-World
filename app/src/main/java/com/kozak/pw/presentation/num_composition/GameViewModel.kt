package com.kozak.pw.presentation.num_composition

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kozak.pw.R
import com.kozak.pw.data.num_composition.GameRepositoryImpl
import com.kozak.pw.domain.num_composition.entity.GameResult
import com.kozak.pw.domain.num_composition.entity.GameSettings
import com.kozak.pw.domain.num_composition.entity.Level
import com.kozak.pw.domain.num_composition.entity.Question
import com.kozak.pw.domain.num_composition.usecase.GenerateQuestionUseCase

class GameViewModel(private val application: Application, private val level: Level) : ViewModel() {

    private val repository = GameRepositoryImpl // TODO get rid of dependency to data layer
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private var timer: CountDownTimer? = null
    private lateinit var gameSettings: GameSettings

    private var countOfRightAnswers = 0
    private var countOfQuestions = 0

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers: LiveData<Int>
        get() = _percentOfRightAnswers

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String>
        get() = _progressAnswers

    private val _enoughCountOfRightAnswers = MutableLiveData<Boolean>()
    val enoughCountOfRightAnswers: LiveData<Boolean>
        get() = _enoughCountOfRightAnswers

    private val _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswers: LiveData<Boolean>
        get() = _enoughPercentOfRightAnswers

    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    init {
        startGame()
    }

    companion object {
        private const val MILLIS_IN_SECOND = 1_000L
        private const val SECONDS_IN_MINUTE = 60
    }

    private fun startGame() {
        gameSettings = level.gameSettings
        _minPercent.value = gameSettings.minPercentOfRightAnswers

        startTimer()
        generateQuestion()
        updateProgress()
    }

    fun chooseAnswer(answerNumber: Int) {
        checkAnswer(answerNumber)
        updateProgress()
        generateQuestion()
    }

    private fun updateProgress() {
        val percent = if (countOfQuestions == 0) 0
        else ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()

        _percentOfRightAnswers.value = percent
        _progressAnswers.value =
            String.format(
                application.resources.getString(R.string.progress_answers),
                countOfRightAnswers,
                gameSettings.minCountOfRightAnswers
            )
        _enoughCountOfRightAnswers.value = countOfRightAnswers >= gameSettings.minCountOfRightAnswers
        _enoughPercentOfRightAnswers.value = percent >= gameSettings.minPercentOfRightAnswers
    }

    private fun checkAnswer(answerNumber: Int) {
        val rightAnswer = question.value?.rightAnswer
        if (answerNumber == rightAnswer) countOfRightAnswers++
        countOfQuestions++
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)
    }

    private fun startTimer() {
        val gameTimeMillis = gameSettings.gameTimeSeconds.toLong() * MILLIS_IN_SECOND
        timer = object : CountDownTimer(gameTimeMillis, MILLIS_IN_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECOND
        val minutes = seconds / SECONDS_IN_MINUTE
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTE)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            enoughCountOfRightAnswers.value == true && enoughPercentOfRightAnswers.value == true,
            countOfRightAnswers,
            countOfQuestions,
            gameSettings
        )
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}