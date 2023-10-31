package com.ed.restclientsample

import com.ed.restclientsample.models.Employee
import com.ed.restclientsample.models.Employees
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IHumanResources {
    @GET("/api/v1/employees")
    public fun getEmployees(): Call<Employees>
}