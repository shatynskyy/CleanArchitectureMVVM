package com.example.cleanarchitecturemvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturemvvm.models.BeerModelPresentation
import com.example.cleanarchitecturemvvm.models.toBeerDomain
import com.example.cleanarchitecturemvvm.models.toBeerPresentation
import com.example.domain.usecases.DeleteBeerUseCase
import com.example.domain.usecases.GetBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeersViewModel @Inject constructor(private val getBeersUseCase: GetBeersUseCase, private val deleteBeerUseCase: DeleteBeerUseCase) : ViewModel() {

    private val _beersStateFlow = MutableStateFlow<List<BeerModelPresentation>>(emptyList())
    val beersStateFlow: StateFlow<List<BeerModelPresentation>> = _beersStateFlow

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getBeers(){
        viewModelScope.launch(Dispatchers.IO) {
            getBeersUseCase.invoke().collect { beers ->
                _beersStateFlow.value = beers.map { it.toBeerPresentation() }
            }
        }
    }

    fun deleteBeer(beer: BeerModelPresentation){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            deleteBeerUseCase.invoke(beer = beer.toBeerDomain())
        }
    }


}