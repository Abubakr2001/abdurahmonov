package com.leo.labtinkoff.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.leo.labtinkoff.model.RandomDataGifs
import com.leo.labtinkoff.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RandomDataViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    fun gifsData(): LiveData<RandomDataGifs>{
        val gifs = MutableLiveData<RandomDataGifs>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                gifs.postValue(repository.getRandomGifs())
            }catch (e:Exception){
                Log.d("error", "error: $e")
            }
        }
        return  gifs
    }

}