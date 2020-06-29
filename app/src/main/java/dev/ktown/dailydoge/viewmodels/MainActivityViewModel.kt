package dev.ktown.dailydoge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.ktown.dailydoge.models.Doge
import dev.ktown.dailydoge.repositories.DogeRepository

class MainActivityViewModel: ViewModel() {
    private val dogeRepository = DogeRepository
    private var doges: LiveData<List<Doge>> = dogeRepository.observableDoges
    // TODO: Implement loading indicator
    val isUpdating: LiveData<Boolean> = MutableLiveData<Boolean>()

    fun getDogeObservable(): LiveData<List<Doge>> {
        return doges
    }

    fun fetchDoges() {
        dogeRepository.fetchDoges()
    }
}