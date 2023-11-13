package com.example.scorecarddiscgolf.adapter

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.model.HoleViewModel

class HoleAdapter(private val holeViewModels: List<HoleViewModel>) :
    RecyclerView.Adapter<HoleAdapter.HoleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hole_item, parent, false)
        return HoleViewHolder(view)
    }

    override fun onBindViewHolder(holder: HoleViewHolder, position: Int) {
        val holeViewModel = holeViewModels[position]
        //Update hole number by 1
        holder.holeNumberTextView.setOnClickListener {
            holeViewModel.setHoleNumber(holeViewModel.getHoleNumber() + 1)
            holder.bind(holeViewModel)
        }
        //Set the Score from player
        holder.playerScoreTextView.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    try {
                        val newScore = holder.playerScoreTextView.text.toString()
                        holeViewModel.updateScore(newScore)
                        holder.bind(holeViewModel)
                    } catch (e: NumberFormatException) {
                        // Show an error message to the user for invalid numeric input
                        Toast.makeText(
                            v?.context,
                            "Please enter a valid number",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return true
                }
                return false
            }
        })

        //Update total score
        holder.totalScoreTextView.setOnClickListener{
            holeViewModel.updateTotalScore()
            holder.bind(holeViewModel)

        }

    }

    override fun getItemCount(): Int = holeViewModels.size

    class HoleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val holeNumberTextView: TextView = itemView.findViewById(R.id.hole_number_text_view)
        val playerScoreTextView: TextView = itemView.findViewById(R.id.player_score_text_view)
        val totalScoreTextView: TextView = itemView.findViewById(R.id.total_score_text_view)

        fun bind(holeViewModel: HoleViewModel) {
            holeNumberTextView.text = holeViewModel.getHoleNumber().toString() // 1-36
            playerScoreTextView.text = holeViewModel.getScore()
        }
    }
}