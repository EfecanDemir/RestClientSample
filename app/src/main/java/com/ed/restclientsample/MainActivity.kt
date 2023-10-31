package com.ed.restclientsample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ed.restclientsample.models.Employee
import com.ed.restclientsample.ui.theme.RestClientSampleTheme
import com.ed.restclientsample.viewmodels.HumanResourcesVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var vm: HumanResourcesVM = HumanResourcesVM(ServiceRepository())
        vm.loadEmployees()
        setContent {
            RestClientSampleTheme {
                val employeeList = vm.department.value ?: emptyList()
                EmployeesDisplay(employeeList)
                Log.e("HumanResourcesVM", "Employees loaded  ${employeeList}")

            }
        }
    }
}

@Composable
fun EmployeesDisplay(employeeList: List<Employee>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Employee List",
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        if (employeeList.isNotEmpty()) {
            LazyColumn {
                items(employeeList) { employee ->
                    EmployeeDisplay(employee)
                }
            }
        } else {
            Text(text = "Loading employees...")
        }
    }
}

@Composable
fun EmployeeDisplay(employee: Employee) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .border(1.dp, Color.Gray)
            .padding(8.dp)
    ) {
        Text(text = "Name: ${employee.employee_name}", fontWeight = FontWeight.Bold)
        Text(text = "Salary: ${employee.employee_salary} TL")
        Text(text = "Age: ${employee.employee_age}")
    }
}