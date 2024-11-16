package com.huancahuari.andres.laboratoriocalificado03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.huancahuari.andres.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
class Ejercicio01Activity : AppCompatActivity() {
    private lateinit var binding: ActivityEjercicio01Binding
    private val viewModel by lazy { Ejercicio01ViewModel() }
    private val adapter by lazy {
        TeacherAdapter(
            this,
            emptyList()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvTeachers.layoutManager = LinearLayoutManager(this)
        binding.rvTeachers.adapter = adapter
        loadTeacherList()
    }
    private fun loadTeacherList() {
        viewModel.getAllTeachers()
        viewModel.teacherList.observe(this) { teachers ->
            adapter.updateTeachers(teachers)
        }
        viewModel.errorApi.observe(this) { errorMessage ->
            showMessage(errorMessage)
        }
    }
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
