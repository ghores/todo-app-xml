package com.example.todoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.model.ToDo
import com.example.todoapp.utils.Picker
import com.example.todoapp.utils.fullDate
import com.example.todoapp.utils.hour
import com.example.todoapp.utils.minute


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
            Picker(parentFragmentManager)
        }

        binding.addTaskBtn.setOnClickListener {
            val newTodo = ToDo(
                binding.titleTxtIl.editText?.text.toString(),
                binding.descriptionTxtIl.editText?.text.toString(),
                "$hour:$minute",
                fullDate, false
            )
        }
    }
}