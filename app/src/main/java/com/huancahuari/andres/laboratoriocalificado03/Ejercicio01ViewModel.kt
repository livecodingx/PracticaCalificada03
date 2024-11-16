package com.huancahuari.andres.laboratoriocalificado03

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class Ejercicio01ViewModel : ViewModel() {

    val teacherList = MutableLiveData<List<TeacherResponse>>()
    val errorApi = MutableLiveData<String>()

    private val client = OkHttpClient()

    init {
        getAllTeachers()
    }

    fun getAllTeachers() {
        val request = Request.Builder()
            .url("https://private-effe28-tecsup1.apiary-mock.com/list/teacher")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorApi.postValue("Error al cargar datos. Verificar la conexión a internet.")
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    errorApi.postValue("Error en la respuesta del servidor")
                    return
                }
                try {
                    val jsonObject = Gson().fromJson(response.peekBody(Long.MAX_VALUE).charStream(), JsonObject::class.java)
                    val teachersJsonArray = jsonObject.getAsJsonArray("teachers")
                    val teacherListType = object : TypeToken<List<TeacherResponse>>() {}.type
                    val teachers = Gson().fromJson<List<TeacherResponse>>(teachersJsonArray, teacherListType)
                    teacherList.postValue(teachers)
                } catch (e: Exception) {
                    e.printStackTrace()
                    errorApi.postValue("Error al procesar los datos")
                }
            }
        })
    }
}
