package com.example.scorecarddiscgolf.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.adapter.PlayersAdapter
import com.example.scorecarddiscgolf.data.Player
import com.example.scorecarddiscgolf.model.ScoreCardViewModel

class MainFragment : Fragment() {


    private lateinit var viewModel: ScoreCardViewModel
    private val playersList = mutableListOf<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScoreCardViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val playerNameEditText = view.findViewById<EditText>(R.id.player_name_edit_text)
        val addPlayerButton = view.findViewById<Button>(R.id.add_player_button)
        val numberOfHolesEditText = view.findViewById<EditText>(R.id.holes_edit_text)
        val continueButton = view.findViewById<Button>(R.id.continue_button)

        val playersRecyclerView = view.findViewById<RecyclerView>(R.id.players_recycler_view)
        val playersAdapter = PlayersAdapter(playersList)
        playersRecyclerView.adapter = playersAdapter
        playersRecyclerView.layoutManager = LinearLayoutManager(context)

        // Handle the "Add Player" button click event
        addPlayerButton.setOnClickListener {
            val playerName = playerNameEditText.text.toString()
            if (playerName.isNotEmpty()) {
                val player = Player(playerName, 0)
                playersList.add(player)
                playersAdapter.notifyDataSetChanged()
                // Clear the EditText after adding a player
                playerNameEditText.text.clear()
            }
        }
            // Handle the "Continue" button click event
        continueButton.setOnClickListener {
            val numberOfHolesText = numberOfHolesEditText.text.toString()
            if (numberOfHolesText.isNotEmpty()) {
                val numberOfHoles = numberOfHolesText.toInt()
                saveNumberOfHoles(numberOfHoles)
                // TODO: Navigate to the HOLEFRAGMENT or perform other actions as needed
            }
        }

        return view
    }

    private fun saveNumberOfHoles(numberOfHoles: Int) {
        val sharedPreferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("number_of_holes", numberOfHoles)
        editor.apply()
    }
    private fun getNumberOfHoles(): Int {
        val sharedPreferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("number_of_holes", 0) // 0 is the default value
    }



}





