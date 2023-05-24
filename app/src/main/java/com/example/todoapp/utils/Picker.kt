package com.example.todoapp.utils

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class Picker(private var fragmentManager: FragmentManager) {
    init {
        makeDatePicker()
    }

    private fun makeTimePicker() {
        val timePicker: MaterialTimePicker =
            MaterialTimePicker.Builder().setTitleText("Select Time")
                .setTimeFormat(TimeFormat.CLOCK_24H).build()
        timePicker.show(fragmentManager, "timePickerInAddTask")
        timePicker.addOnPositiveButtonClickListener {
            hour = timePicker.hour
            minute = timePicker.minute
        }
    }

    private fun makeDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("select data").build()
        datePicker.show(fragmentManager, "datePickerInAddTask")

        datePicker.addOnPositiveButtonClickListener {
            val outputDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            outputDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            fullDate = outputDateFormat.format(it)
            makeTimePicker()
        }

    }
}