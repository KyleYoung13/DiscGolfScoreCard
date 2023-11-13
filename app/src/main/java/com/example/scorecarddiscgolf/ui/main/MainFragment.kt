package com.example.scorecarddiscgolf.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.adapter.PlayersAdapter
import com.example.scorecarddiscgolf.data.Player
import com.example.scorecarddiscgolf.databinding.FragmentMainBinding
import com.example.scorecarddiscgolf.model.SharedViewModel


class MainFragment : Fragment() {

    private lateinit var playersList: MutableLiveData<List<Player>>
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        //Populating list of players
        playersList = sharedViewModel.players

        //val playerNameEditText = findViewById<EditText>(R.id.player_name_edit_text)
        //val addPlayerButton = findViewById<Button>(R.id.add_player_button)
        //val numberOfHolesEditText = view.findViewById<EditText>(R.id.holes_edit_text)
        // continueButton = view.findViewById<Button>(R.id.continue_button)

        //val playersRecyclerView = view.findViewById<RecyclerView>(R.id.players_recycler_view)
        //playersRecyclerView.layoutManager = LinearLayoutManager(context)



        // Handle the "Add Player" button click event
        binding.addPlayerButton.setOnClickListener {
            val playerName = binding.playerNameEditText.text.toString()
            if (playerName.isNotEmpty()) {
                val player = Player(playerName, 0)
                sharedViewModel.addPlayer(player)
                // Clear the EditText after adding a player
                binding.playerNameEditText.text.clear()
            }
        }

        binding.continueButton.setOnClickListener {
            val numberOfHolesText = binding.holesEditText.text.toString()
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)


        val holeObserver = Observer<List<Player>>{ players ->
            binding.playersRecyclerView.adapter = PlayersAdapter(players)
            binding.playersRecyclerView.layoutManager = LinearLayoutManager(context)
        }

        sharedViewModel.players.observe(viewLifecycleOwner, holeObserver)
    }
}






