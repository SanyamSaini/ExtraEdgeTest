package com.extra.edge.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extra.edge.test.model.Rocket
import com.extra.edge.test.repository.Response
import com.extra.edge.test.repository.RocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: RocketRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
                repository.getRockets()
        }
    }

    val rockets: LiveData<Response<List<Rocket>>>
        get() = repository.rockets
}