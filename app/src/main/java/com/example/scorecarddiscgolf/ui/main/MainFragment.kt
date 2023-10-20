package com.example.scorecarddiscgolf.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.adapter.PlayersAdapter
import com.example.scorecarddiscgolf.data.Player
import com.example.scorecarddiscgolf.model.SharedViewModel

class MainFragment : Fragment() {

    private lateinit var playersList: MutableLiveData<List<Player>>
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        playersList = sharedViewModel.players

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
                sharedViewModel.addPlayer(player)
                playersAdapter.notifyDataSetChanged()
                // Clear the EditText after adding a player
                playerNameEditText.text.clear()
            }
        }

        continueButton.setOnClickListener {
            val numberOfHolesText = numberOfHolesEditText.text.toString()
            if (numberOfHolesText.isNotEmpty()) {
                val numberOfHoles = numberOfHolesText.toInt()
                sharedViewModel.updateCurrentHole(numberOfHoles)
                // TODO: Navigate to the HOLEFRAGMENT or perform other actions as needed
                findNavController().navigate(R.id.action_mainFragment_to_holeFragment)
            }
        }

        // Observe the current hole number outside of the button click listener
        sharedViewModel.currentHole.observe(viewLifecycleOwner) { holeNumber ->
            Log.d("MainFragment", "Received hole number: $holeNumber")
            // You can update UI elements here, like a TextView
        }

        return view
    }
}






