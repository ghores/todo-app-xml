package com.example.todoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adaptor.ToDoAdaptor
import com.example.todoapp.databinding.FragmentCurrentToDosBinding
import com.example.todoapp.model.ToDo

val toDoList = mutableListOf<ToDo>()

class CurrentToDos : Fragment() {

    private lateinit var binding: FragmentCurrentToDosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentToDosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addTaskFabBtn.setOnClickListener {
            Navigation.findNavController(binding.addTaskFabBtn)
                .navigate(R.id.action_currentToDos_to_addTask)
        }
        initRecView()
    }

    override fun onResume() {
        super.onResume()
        initRecView()
    }

    private fun initRecView() {
        val adapter = ToDoAdaptor(
            toDoList,
            requireContext()
        )
        binding.recView.adapter = adapter
        binding.recView.layoutManager = LinearLayoutManager(requireContext())
    }

}