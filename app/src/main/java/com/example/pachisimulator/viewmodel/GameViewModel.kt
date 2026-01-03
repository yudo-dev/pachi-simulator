package com.example.pachisimulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pachisimulator.data.GameRepository
import com.example.pachisimulator.data.GameResult
import kotlinx.coroutines.launch

/**
 * ゲームリポジトリ
 */
class GameViewModel(private val repository: GameRepository) : ViewModel() {

    // データを監視
    val allResults: LiveData<List<GameResult>> = repository.allResults.asLiveData()
    val averageRotation: LiveData<Double?> = repository.averageRotation.asLiveData()

    // データを保存
    fun recordResult(rotations: Int) {
        viewModelScope.launch {
            val newResult = GameResult(rotations = rotations)
            repository.insert(newResult)
        }
    }

    // リセット
    fun resetData() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}

/**
 * ゲームビューモデルファクトリー
 */
class GameViewModelFactory(private val repository: GameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
