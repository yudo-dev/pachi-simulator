package com.example.pachisimulator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pachisimulator.viewmodel.GameViewModel
import com.example.pachisimulator.viewmodel.GameViewModelFactory
import com.example.pachisimulator.PachiApplication
import com.example.pachisimulator.R
import kotlin.random.Random

/**
 * 確率ゲーム画面
 */

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels {
        GameViewModelFactory((requireActivity().application as PachiApplication).repository)
    }

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
        val resetButton = view.findViewById<Button>(R.id.resetButton) // ★ボタンを見つける
        // データ監視
        viewModel.allResults.observe(viewLifecycleOwner) { list ->
            // 当たり回数
            val totalHits = list.size
            // 平均確率
            viewModel.averageRotation.observe(viewLifecycleOwner) { avg ->
                // 小数点第1位まで表示（データがないときは ---）
                val avgText = if (avg != null) String.format("%.1f", avg) else "---"
                statsText.text = "当たり: ${totalHits}回\n平均: 1/${avgText}"
            }
        }

        // リセットボタン押下
        resetButton.setOnClickListener {
            // データを全消去
            viewModel.resetData()
        }

        // 抽選開始ボタン押下
        actionButton.setOnClickListener {
            var count = 0

            // 抽選ループ（1/319）
            while (true) {
                count++
                if (Random.nextInt(319) == 0) {
                    break
                }
            }

            // 結果を表示
            resultText.text = "${count}回転目で\n当たり！"
            viewModel.recordResult(count)
        }
    }
}
