package com.example.scorecarddiscgolf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.model.HoleViewModel

class HoleItemView(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.hole_item, parent, false)
) {

    private val holeNumberTextView: TextView = itemView.findViewById(R.id.hole_number_text_view)
    private val playerScoreTextView: TextView = itemView.findViewById(R.id.player_score_text_view)
    private val totalScoreTextView: TextView = itemView.findViewById(R.id.total_score_text_view)

    fun bind(holeViewModel: HoleViewModel) {
        holeNumberTextView.text = holeViewModel.getHoleNumber().toString() // 1-36
        playerScoreTextView.text = holeViewModel.getScore()
    }
}