package com.example.scorecarddiscgolf.model


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.adapter.PlayersAdapter
import com.example.scorecarddiscgolf.databinding.FragmentHoleBinding


class HoleFragment : Fragment() {


    private lateinit var viewModel: HoleViewModel
    private lateinit var playersAdapter: PlayersAdapter
    private lateinit var binding: FragmentHoleBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var holeNumbers = 1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use DataBindingUtil to inflate the layout and set up data binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_hole, container, false
        )

        // Return the root view from the binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get player name, hole number, and score from your data source or preferences
        val score = "0" // Replace with the actual initial score
        var holeNumber = sharedViewModel.getHoleNumber()

        binding.holeNumberTextView?.text =
            "Hole: $holeNumbers" // Retrieve the hole number using sharedViewModel

        // Create a HoleViewModel instance with constructor arguments
        viewModel = HoleViewModel(holeNumber!!, score)

        // Set the ViewModel in the binding
        binding.holeViewModel = viewModel

        playersAdapter = PlayersAdapter(viewModel.playersLiveData)
        binding.playersRecyclerView.adapter = playersAdapter

        // Set the lifecycle owner for LiveData to work with data binding
        binding.lifecycleOwner = viewLifecycleOwner

        // Set an OnClickListener for the save button
        binding.saveButton.setOnClickListener {
            if (holeNumbers <= holeNumber) {
                val scoreText = binding.scoreEditText.text.toString()
                if (scoreText.isNotEmpty()) {
                    viewModel.updateScore(scoreText)
                    holeNumbers++

                    // Clear score's edit text
                    binding.scoreEditText.text.clear()
                }
                binding.holeNumberTextView?.text =
                    "Hole: $holeNumbers"
            }
            else{
                Toast.makeText(context, "Game over", Toast.LENGTH_SHORT).show()
            }

            sharedViewModel.players.observe(viewLifecycleOwner){players ->
                playersAdapter.players = players
                playersAdapter.notifyDataSetChanged()
            }
        }

    }
}
