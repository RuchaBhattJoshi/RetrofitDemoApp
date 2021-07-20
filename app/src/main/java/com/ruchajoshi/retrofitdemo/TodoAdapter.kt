package com.ruchajoshi.retrofitdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ruchajoshi.retrofitdemo.databinding.ItemTodoBinding

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder (var binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TodoItem>(){

        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
           return oldItem == newItem
        }


    }


    private val differ = AsyncListDiffer(this,diffCallback)

    var todos: List<TodoItem>
get()= differ.currentList
    set(value) {  differ.submitList(value)}


    override fun getItemCount() = todos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
     holder.binding.apply {
         val todo = todos[position]
         tvTitle.text = todo.title
         cbDone.isChecked= todo.completed
     }
    }



}