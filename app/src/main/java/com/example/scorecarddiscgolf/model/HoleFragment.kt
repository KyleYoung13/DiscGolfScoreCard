package com.example.scorecarddiscgolf.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.adapter.PlayersAdapter
import com.example.scorecarddiscgolf.databinding.FragmentHoleBinding


class HoleFragment : Fragment() {

    private val holeNumber by lazy {
        arguments?.getInt("holeNumber", 1) ?: 1
    }

    private lateinit var viewModel: HoleViewModel
    private lateinit var playersAdapter: PlayersAdapter
    val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use DataBindingUtil to inflate the layout and set up data binding
        val binding: FragmentHoleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_hole, container, false
        )

        // Get player name, hole number, and score from your data source or preferences
        val playerName = "John" // Replace with the actual player name
        val score = "0" // Replace with the actual initial score

        val holeNumberTextView = view?.findViewById<TextView>(R.id.holeNumberTextView)
        holeNumberTextView?.text = "Hole: $holeNumber"


        // Create a HoleViewModel instance with constructor arguments18
        viewModel = HoleViewModel(playerName, holeNumber, score)

        // Set the ViewModel in the binding
        binding.holeViewModel = viewModel

        playersAdapter = PlayersAdapter(emptyList())

        // Set the lifecycle owner for LiveData to work with data binding
        binding.lifecycleOwner = viewLifecycleOwner

        // Set an OnClickListener for the save button
        binding.saveButton.setOnClickListener {
            val scoreText = binding.scoreEditText.text.toString()
            if (scoreText.isNotEmpty()) {
                viewModel.updateScore(scoreText)

                // Clear score's edit text
                binding.scoreEditText.text.clear()
            }
        }

        // Return the root view from the binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the playersLiveData from your ViewModel
        viewModel.playersLiveData.observe(viewLifecycleOwner) { players ->
            // Update your RecyclerView with the new list of players
            playersAdapter.updatePlayers(players)
        }
    }
}
