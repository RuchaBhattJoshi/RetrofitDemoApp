package com.ruchajoshi.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruchajoshi.retrofitdemo.databinding.ActivityMainBinding
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var todoadapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview()

        lifecycleScope.launchWhenCreated {
            //binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch (e: IOException) {
                Log.e(TAG, "you might not have internet conncetion")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException: unexpected response ")
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                todoadapter.todos = response.body()!!
            } else {
                Log.e(TAG, "not successful ")

            }

        }
    }

    private fun setupRecyclerview()= binding.rvTodos.apply {
    todoadapter = TodoAdapter()
    adapter = todoadapter
    layoutManager = LinearLayoutManager(this@MainActivity)

}

}