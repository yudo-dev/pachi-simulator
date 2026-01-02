package com.example.pachisimulator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pachisimulator.R
import kotlin.random.Random

/**
 * 確率ゲーム画面
 */

class GameFragment : Fragment() {

    // 当たった回数
    private var hitCount = 0

    // 全部の回転数の合計
    private var totalRotation = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultText = view.findViewById<TextView>(R.id.resultText)
        val statsText = view.findViewById<TextView>(R.id.statsText)
        val actionButton = view.findViewById<Button>(R.id.actionButton)

        actionButton.setOnClickListener {
            // 今回の回転数
            var currentCount = 0

            // 当たりが出るまで回す
            while (true) {
                currentCount++
                if (Random.nextInt(319) == 0) {
                    break
                }
            }

            // 当たり回数を増やす
            hitCount++
            // 合計回転数に足す
            totalRotation += currentCount
            // 平均確率を出す（合計回転数 ÷ 当たり回数）
            val average = totalRotation / hitCount
            // 画面更新
            resultText.text = "${currentCount}回転目で\n当たり！"
            statsText.text = "当たり: ${hitCount}回\n平均: 1/${average}"
        }
    }
}
