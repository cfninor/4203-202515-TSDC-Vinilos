package com.example.appvinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.models.Collector
import com.example.appvinilos.network.RetrofitClient
import kotlinx.coroutines.launch

class CollectorViewModel : ViewModel() {

    private val _collectors = MutableLiveData<List<Collector>>()
    val collectors: LiveData<List<Collector>> = _collectors

    fun fetchCollectors() {
        viewModelScope.launch {
            try {
                val collectorList = RetrofitClient.instance.getCollectors()
                _collectors.postValue(collectorList)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}