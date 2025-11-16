package com.example.appvinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.models.Collector
import com.example.appvinilos.repositories.CollectorRepository
import kotlinx.coroutines.launch

class CollectorViewModel(private val collectorRepository: CollectorRepository) : ViewModel() {

    private val _collectors = MutableLiveData<List<Collector>>()
    val collectors: LiveData<List<Collector>> = _collectors

    fun fetchCollectors() {
        viewModelScope.launch {
            try {
                _collectors.postValue(collectorRepository.getCollectors())
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}