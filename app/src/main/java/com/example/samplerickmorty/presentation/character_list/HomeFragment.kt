package com.example.samplerickmorty.presentation.character_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.samplerickmorty.R
import com.example.samplerickmorty.presentation.adapters.CharacterAdapter
import com.example.samplerickmorty.data.ResultsItem
import com.example.samplerickmorty.databinding.FragmentHomeBinding
import com.example.samplerickmorty.presentation.CharacterViewModel
import com.example.samplerickmorty.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val TAG = "APIRESPONSE"
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CharacterViewModel>()
    private lateinit var adapter: CharacterAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        adapter = CharacterAdapter(::onNoteClicked)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characterRV.layoutManager =
            StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        binding.characterRV.adapter = adapter
        lifecycleScope.launchWhenStarted {
            viewModel.characterResponse.collect{
                when(it){
                    is NetworkResult.Error -> {
                        Log.d(TAG, "onViewCreated: Error occurred ${it.message}")
                    }
                    is NetworkResult.Loading -> {
                        Log.d(TAG, "onViewCreated: Loading")
                    }
                    is NetworkResult.Success -> {
                        adapter.submitList(it.data?.results)
                        Log.d(TAG, "onViewCreated: Success with data ${it.data?.results}")
                    }

                }
            }
        }

    }


    private fun onNoteClicked(character: ResultsItem){
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }


}