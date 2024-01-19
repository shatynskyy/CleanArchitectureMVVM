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
import com.bumptech.glide.Glide
import com.example.cleanarchitecturemvvm.R
import com.example.cleanarchitecturemvvm.viewmodels.RandomBeerViewModel
import com.example.cleanarchitecturemvvm.databinding.FragmentRandomBeerBinding
import com.example.cleanarchitecturemvvm.utility.ExceptionsWrapper
import com.example.cleanarchitecturemvvm.utility.ResponseWrapper
import com.example.cleanarchitecturemvvm.utility.gone
import com.example.cleanarchitecturemvvm.utility.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomBeerFragment : Fragment() {

    private val viewModel: RandomBeerViewModel by viewModels<RandomBeerViewModel>()
    private var _binding: FragmentRandomBeerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomBeerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRandomBeer()
        initButtons()
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.randomBeerStateFlow.collect { result ->
                    when(result){
                        is ResponseWrapper.Loading ->{
                            binding.apply {
                                binding.mainLayout.gone()
                                binding.progressBar.visible()
                                binding.errorLayout.gone()
                            }
                        }
                        is ResponseWrapper.Success->{
                            binding.apply {
                                mainLayout.visible()
                                progressBar.gone()
                                errorLayout.gone()
                                Glide.with(requireContext())
                                    .load(result.data.image)
                                    .placeholder(R.drawable.default_beer)
                                    .error(R.drawable.default_beer)
                                    .into(image)
                                title.text = result.data.name
                                abv.text = getString(R.string.abv_percent, result.data.abv)
                                firstBrewed.text = result.data.firstBrewed
                            }
                        }
                        is ResponseWrapper.Error->{
                            binding.apply {
                                mainLayout.gone()
                                progressBar.gone()
                                errorLayout.visible()
                                errorTitle.text = ExceptionsWrapper.checkResponseError(exception = result.exception, context = requireContext())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initButtons() {
        binding.apply {
            randomBeerBtn.setOnClickListener {
                viewModel.getRandomBeer()
            }
            saveBeerBtn.setOnClickListener {
                viewModel.insertBeer()
            }
            retryButton.setOnClickListener {
                viewModel.getRandomBeer()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}