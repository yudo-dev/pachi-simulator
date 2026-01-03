package com.example.pachisimulator

import android.app.Application
import com.example.pachisimulator.data.AppDatabase
import com.example.pachisimulator.data.GameRepository

/**
 * アプリケーションクラス
 */
class PachiApplication : Application() {
    // 起動時にデータベース作成
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { GameRepository(database.gameDao()) }
}