package com.ed.restclientsample

import android.util.Log
import com.ed.restclientsample.models.Employee
import com.ed.restclientsample.models.Employees
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceRepository {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://dummy.restapiexample.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var proxy: IHumanResources = retrofit.create(IHumanResources::class.java)

    public fun GetEmployees(callback: (List<Employee>?) -> Unit) {
        proxy.getEmployees().enqueue(
            object : Callback<Employees> {
                override fun onResponse(call: Call<Employees>, response: Response<Employees>) {
                    if (response.isSuccessful) {
                        val employees = response.body()?.data
                        callback(employees)
                    } else {
                        Log.e("ServiceRepository", "Response not successful: ${response.code()} - ${response.message()}")
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<Employees>, t: Throwable) {
                    Log.e("ServiceRepository", "Request failed: ${t.message}")
                    callback(null)
                }
            }
        )
    }
}
