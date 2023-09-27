package com.example.scorecarddiscgolf.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.databinding.FragmentHoleBinding
import com.example.scorecarddiscgolf.model.HoleViewModel

class HoleFragment : Fragment() {

    private lateinit var viewModel: HoleViewModel

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
        val holeNumber = 1 // Replace with the actual hole number
        val score = "0" // Replace with the actual initial score

        // Create a HoleViewModel instance with constructor arguments
        val viewModel = HoleViewModel(playerName, holeNumber, score)

        // Set the ViewModel in the binding
        binding.holeViewModel = viewModel

        // Set the lifecycle owner for LiveData to work with data binding
        binding.lifecycleOwner = viewLifecycleOwner

        // Set an OnClickListener for the save button
        binding.saveButton.setOnClickListener {
            val scoreText = binding.scoreEditText.text.toString()
            if (scoreText.isNotEmpty()) {
                viewModel.updateScore(scoreText)
            }
        }

        // Return the root view from the binding
        return binding.root
    }
}
