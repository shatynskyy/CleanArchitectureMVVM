package com.example.cleanarchitecturemvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturemvvm.models.BeerModelPresentation
import com.example.cleanarchitecturemvvm.models.toBeerPresentation
import com.example.cleanarchitecturemvvm.utility.ResponseWrapper
import com.example.data.exceptions.GeneralException
import com.example.domain.models.BeerDomainModel
import com.example.domain.usecases.GetRandomBeerUseCase
import com.example.domain.usecases.InsertBeerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomBeerViewModel @Inject constructor(private val getRandomBeerUseCase: GetRandomBeerUseCase, private val insertBeerUseCase: InsertBeerUseCase): ViewModel() {

    private val _randomBeerLiveData = MutableLiveData<ResponseWrapper<BeerModelPresentation>>()
    var randomBeerLiveData : LiveData<ResponseWrapper<BeerModelPresentation>> = _randomBeerLiveData

    private var beer: BeerDomainModel? = null

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _randomBeerLiveData.postValue(ResponseWrapper.Error(throwable))
        throwable.printStackTrace()
    }

    private val coroutineExceptionHandlerInsertBeer = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getRandomBeer(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _randomBeerLiveData.postValue(ResponseWrapper.Loading())
            val randomBeer = getRandomBeerUseCase.invoke()
            beer = randomBeer
            _randomBeerLiveData.postValue(ResponseWrapper.Success(randomBeer.toBeerPresentation()))
        }
    }

    fun insertBeer(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandlerInsertBeer) {
            beer?.let {
                insertBeerUseCase.invoke(it)
                getRandomBeer()
            }
        }
    }

}
