package com.example.pachisimulator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * 確率ゲームDao
 */
@Dao
interface GameDao {
    // データを保存する
    @Insert
    suspend fun insert(result: GameResult)

    // 履歴を取得する
    @Query("SELECT * FROM game_results ORDER BY id DESC")
    fun getAllResults(): Flow<List<GameResult>>

    // 平均確率を計算する
    @Query("SELECT AVG(rotations) FROM game_results")
    fun getAverageRotation(): Flow<Double?>

    // 履歴を削除する
    @Query("DELETE FROM game_results")
    suspend fun deleteAll()
}
