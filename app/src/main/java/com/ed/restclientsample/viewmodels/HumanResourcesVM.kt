package com.ed.restclientsample.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ed.restclientsample.ServiceRepository
import com.ed.restclientsample.models.Employee

class HumanResourcesVM(sr: ServiceRepository) : ViewModel() {
    private var repository: ServiceRepository? = null

    init {
        this.repository = sr
    }

    private val _department = MutableLiveData<List<Employee>>()
    val department: LiveData<List<Employee>>
        get() = _department

    fun loadEmployees() {
        repository?.GetEmployees { employees ->
            if (employees != null) {
                _department.value = employees
                Log.d("HumanResourcesVM", "Employees loaded successfully ${_department.value}")
            }
        }
    }
}