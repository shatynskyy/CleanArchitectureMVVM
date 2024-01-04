package com.example.cleanarchitecturemvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturemvvm.models.BeerModelPresentation
import com.example.cleanarchitecturemvvm.models.toBeerDomain
import com.example.cleanarchitecturemvvm.models.toBeerPresentation
import com.example.data.exceptions.GeneralException
import com.example.domain.usecases.DeleteBeerUseCase
import com.example.domain.usecases.GetBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeersViewModel @Inject constructor(private val getBeersUseCase: GetBeersUseCase, private val deleteBeerUseCase: DeleteBeerUseCase) : ViewModel() {

    private val _beersLiveData = MutableLiveData<List<BeerModelPresentation>>()
    val beersLiveData : LiveData<List<BeerModelPresentation>> = _beersLiveData

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getBeers(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getBeersUseCase.invoke().map { it.toBeerPresentation() }
            _beersLiveData.postValue(list)
        }
    }

    fun deleteBeer(beer: BeerModelPresentation){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            deleteBeerUseCase.invoke(beer = beer.toBeerDomain())
            getBeers()
        }
    }


}