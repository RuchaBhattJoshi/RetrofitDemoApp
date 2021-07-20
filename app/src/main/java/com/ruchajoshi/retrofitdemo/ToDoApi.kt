package com.ruchajoshi.retrofitdemo

import retrofit2.Response
import retrofit2.http.GET

interface ToDoApi {

    @GET("/todos")
    suspend fun getTodos(): Response<List<TodoItem>>

    // @POST("/createTodo")
   // fun createTodo( @Body todo: TodoItem):Response<CreateTodoResponse>

}