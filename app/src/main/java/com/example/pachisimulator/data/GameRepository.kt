package com.example.pachisimulator.data

import kotlinx.coroutines.flow.Flow

/**
 * ゲーム結果リポジトリ
 */
class GameRepository(private val gameDao: GameDao) {

    // 全データの取得
    val allResults: Flow<List<GameResult>> = gameDao.getAllResults()

    // 平均確率の取得
    val averageRotation: Flow<Double?> = gameDao.getAverageRotation()

    // データの保存
    suspend fun insert(result: GameResult) {
        gameDao.insert(result)
    }

    // データ全削除
    suspend fun deleteAll() {
        gameDao.deleteAll()
    }
}
