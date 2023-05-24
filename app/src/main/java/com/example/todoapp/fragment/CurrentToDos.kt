package com.example.todoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adaptor.ToDoAdaptor
import com.example.todoapp.databinding.FragmentCurrentToDosBinding
import com.example.todoapp.model.ToDo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


lateinit var currentToDosBinding: FragmentCurrentToDosBinding

class CurrentToDos : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentToDosBinding = FragmentCurrentToDosBinding.inflate(inflater, container, false)
        return currentToDosBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentToDosBinding.addTaskFabBtn.setOnClickListener {
            Navigation.findNavController(currentToDosBinding.addTaskFabBtn)
                .navigate(R.id.action_currentToDos_to_addTask)
        }
        lifecycleScope.launch {
            initRecView()
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            initRecView()
        }
    }

    private suspend fun initRecView() {
        val adapter = ToDoAdaptor(
            requireContext().dataStore.data.first().todoList.toMutableList(),
            requireContext()
        )
        currentToDosBinding.recView.adapter = adapter
        currentToDosBinding.recView.layoutManager = LinearLayoutManager(requireContext())

    }

}