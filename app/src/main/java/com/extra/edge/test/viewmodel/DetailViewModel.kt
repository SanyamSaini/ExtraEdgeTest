package com.extra.edge.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extra.edge.test.model.Rocket
import com.extra.edge.test.repository.RocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: RocketRepository) : ViewModel() {

    val rocketDetail: LiveData<Rocket>
        get() = repository.rocketDetail

    fun getRocketDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRocketDetail(id)
        }
    }
}