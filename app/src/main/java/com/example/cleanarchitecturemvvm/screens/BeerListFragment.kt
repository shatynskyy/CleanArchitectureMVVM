package com.example.cleanarchitecturemvvm.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleanarchitecturemvvm.adapter.BeersAdapter
import com.example.cleanarchitecturemvvm.databinding.FragmentBeerListBinding
import com.example.cleanarchitecturemvvm.utility.gone
import com.example.cleanarchitecturemvvm.utility.visible
import com.example.cleanarchitecturemvvm.viewmodels.BeersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeerListFragment : Fragment() {

    private var _binding: FragmentBeerListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BeersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BeersAdapter{ beer ->
            viewModel.deleteBeer(beer = beer)
        }
        binding.recycleViewMyGroupsList.adapter = adapter
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.beersStateFlow.collect { list ->
                    adapter.updateList(list)
                    if (list.isEmpty()) binding.emptyListError.visible() else binding.emptyListError.gone()
                }
            }
        }
        viewModel.getBeers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}