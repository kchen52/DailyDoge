package dev.ktown.dailydoge.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import dev.ktown.dailydoge.models.Doge
import dev.ktown.dailydoge.repositories.network.ShibeOnlineApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogeRepository {
    init{
        // TODO: Use this to determine whether the singleton is actually implemented correctly
        println("Creating DogeRepo")
    }
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default + Job())
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://shibe.online/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val shibeOnlineApi: ShibeOnlineApi = retrofit.create(ShibeOnlineApi::class.java)
    val observableDoges = MutableLiveData<List<Doge>>()

    fun fetchDoges() {
        coroutineScope.launch {
            val result = async {
                shibeOnlineApi.getShibes()
            }
            val dogeList = result.await().body()?.map { Doge(it) } ?: emptyList()
            observableDoges.postValue(dogeList)
        }
    }
}