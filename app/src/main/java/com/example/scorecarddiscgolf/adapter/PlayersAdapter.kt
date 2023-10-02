package com.example.scorecarddiscgolf.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.data.Player

class PlayersAdapter(private val players: List<Player>) :
    RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int = players.size

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerNameTextView: TextView = itemView.findViewById(R.id.player_name_text_view)
        private val playerScoreEditText: EditText = itemView.findViewById(R.id.player_score_edit_text)

        fun bind(player: Player) {
            playerNameTextView.text = player.playerName
            playerScoreEditText.setText(player.playerScore.toString())

            // Add a TextChangedListener to the EditText to update the player's score
            playerScoreEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    // Update the player's score in your data structure (e.g., viewModel)
                    val newScore = s.toString().toIntOrNull() ?: 0
                    player.playerScore = newScore
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Not needed
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Not needed
                }
            })
        }
    }
}


