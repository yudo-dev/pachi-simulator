package com.example.pachisimulator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ゲームデータ
 */

@Entity(tableName = "game_results")
data class GameResult(
    @PrimaryKey(autoGenerate = true)
    // ID
    val id: Int = 0,
    // 何回転で当たったか
    val rotations: Int,
    // いつのデータか（日時）
    val timestamp: Long = System.currentTimeMillis()
)
