package com.ruchajoshi.retrofitdemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//if Dependency injection ignore this do on app module.
object RetrofitInstance {

    val api: ToDoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ToDoApi::class.java)

    }

}