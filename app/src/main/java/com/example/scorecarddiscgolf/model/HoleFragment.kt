package com.example.scorecarddiscgolf.model


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.scorecarddiscgolf.R
import com.example.scorecarddiscgolf.adapter.PlayersAdapter
import com.example.scorecarddiscgolf.data.Player
import com.example.scorecarddiscgolf.databinding.FragmentHoleBinding


class HoleFragment : Fragment() {

    private lateinit var playersAdapter: PlayersAdapter
    private lateinit var binding: FragmentHoleBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val holeViewModel: HoleViewModel by viewModels()
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
        playersAdapter = PlayersAdapter(sharedViewModel.players)
        binding.playersRecyclerView.adapter = playersAdapter

        binding.holeViewModel = holeViewModel
        //Create ViewModelProvider for HoleViewModel
        holeViewModel == ViewModelProvider(this).get(HoleViewModel::class.java)

        // Return the root view from the binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        holeViewModel.playersLiveData.observe(viewLifecycleOwner) { players ->
            playersAdapter.players = players
            playersAdapter.notifyDataSetChanged()
        }

        // Get player name, hole number, and score from your data source or preferences
        var holeNumber = sharedViewModel.getHoleNumber()

        binding.holeNumberTextView?.text =
            "Hole: $holeNumbers" // Retrieve the hole number using sharedViewModel

        // Set the ViewModel in the binding
        binding.holeViewModel = holeViewModel

        // Set the lifecycle owner for LiveData to work with data binding
        binding.lifecycleOwner = viewLifecycleOwner

        // Set an OnClickListener for the save button
        binding.saveButton.setOnClickListener {
            if (holeNumbers <= holeNumber!!) {
                val scoreText = binding.scoreEditText.text.toString()
                if (scoreText.isNotEmpty()) {
                    holeViewModel.updateScore(scoreText)
                    holeNumbers++

                    // Clear score's edit text
                    binding.scoreEditText.text.clear()
                }
                binding.holeNumberTextView?.text = "Hole: $holeNumbers"
            }
            else{
                Toast.makeText(context, "Game over", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
