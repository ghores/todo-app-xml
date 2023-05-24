package com.example.todoapp.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.fragment.currentToDosBinding
import com.example.todoapp.fragment.dataStore
import com.example.todoapp.model.ToDo
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.runBlocking

class ToDoAdaptor(var todoList: MutableList<ToDo>, var context: Context) :
    RecyclerView.Adapter<ToDoAdaptor.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var card = itemView.findViewById<CardView>(R.id.card)
        var titleTb = itemView.findViewById<TextView>(R.id.titleTextBox)
        var descTb = itemView.findViewById<TextView>(R.id.descriptionTextBox)
        var dateText = itemView.findViewById<TextView>(R.id.dateTextBox)
        var dateDTb = itemView.findViewById<TextView>(R.id.dateDescriptionBox)
        var timeText = itemView.findViewById<TextView>(R.id.timeTextBox)
        var timeDTb = itemView.findViewById<TextView>(R.id.timeDescriptionBox)
        var isDoneCheckBox = itemView.findViewById<CheckBox>(R.id.isDoneCh)

        init {
            isDoneCheckBox.setOnCheckedChangeListener { button, isChecked ->
                if (isChecked) {
                    runBlocking {
                        context.dataStore.updateData {
                            it.copy(
                                it.todoList.mutate {
                                    it.removeAt(adapterPosition)
                                }
                            )
                        }
                    }
                    currentToDosBinding.recView.adapter!!.notifyDataSetChanged()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            titleTb.text = todoList[position].title
            descTb.text = todoList[position].description
            timeDTb.text = todoList[position].time
            dateDTb.text = todoList[position].date
            isDoneCheckBox.isChecked = todoList[position].isDone
        }
    }
}