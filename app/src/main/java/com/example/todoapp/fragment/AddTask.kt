package com.example.todoapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.dataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.db.ToDoListSerializer
import com.example.todoapp.model.ToDo
import com.example.todoapp.utils.Picker
import com.example.todoapp.utils.fullDate
import com.example.todoapp.utils.hour
import com.example.todoapp.utils.minute
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.launch

val Context.dataStore by dataStore("mainFile.json", ToDoListSerializer())

class AddTask : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateEdtIl.setOnClickListener {
            Picker(parentFragmentManager, binding.dateEdtIl)
        }

        binding.addTaskBtn.setOnClickListener {
            val newTodo = ToDo(
                binding.titleTxtIl.editText?.text.toString(),
                binding.descriptionTxtIl.editText?.text.toString(),
                "$hour:$minute",
                fullDate, false
            )

            lifecycleScope.launch {
                requireContext().dataStore.updateData {
                    it.copy(
                        it.todoList.mutate {
                            it.add(newTodo)
                        }
                    )
                }
                Navigation.findNavController(binding.addTaskBtn)
                    .navigate(R.id.action_addTask_to_currentToDos)

            }

        }
    }
}