package com.ed.restclientsample.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ed.restclientsample.ServiceRepository
import com.ed.restclientsample.models.Employee
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HumanResourcesVM(sr: ServiceRepository) : ViewModel() {
    private val repository: ServiceRepository = sr

    val department = mutableStateOf<List<Employee>>(emptyList())

    init {
        loadEmployees()
    }

    fun loadEmployees() {
        repository.GetEmployees { employees ->
            if (employees != null) {
                department.value = employees
                Log.d("HumanResourcesVM", "Employees loaded successfully ${department.value}")
            } else {
                viewModelScope.launch {
                    delay(2000)
                    loadEmployees()
                }
            }
        }
    }
}
