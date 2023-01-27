package com.abhishek.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
   private var selectedDate: TextView? = null
    private var ageInMinute: TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDataaa: Button= findViewById(R.id.btnDate)
        selectedDate = findViewById(R.id.selectedDate)
        ageInMinute = findViewById(R.id.ageInMinutes)
       btnDataaa.setOnClickListener {
           clickDatePicker()
        //   Toast.makeText(this, "button pressed", Toast.LENGTH_LONG).show()
       }
    }

   private fun clickDatePicker(){
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month= myCalander.get(Calendar.MONTH)
        val date = myCalander.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
        { _, selectedYear, selectedMonth, selectedDateOfMonth ->
            Toast.makeText(this, "year is $selectedYear and month is ${selectedMonth+1} and date is $selectedDateOfMonth", Toast.LENGTH_LONG).show()
            val selectedDatee = "$selectedDateOfMonth/${selectedMonth+1}/$selectedYear"
            selectedDate?.text = selectedDatee

            val sdf = SimpleDateFormat ("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDatee)
            theDate?.let {
                val selectedDateInMinutes = theDate.time/ 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time/ 60000
                    val differenceInMinutes = currentDateInMinutes- selectedDateInMinutes
                    ageInMinute?.text= differenceInMinutes.toString()
                }
            }
        },
            year,
            month,
            date
        )
        dpd.datePicker.maxDate= System.currentTimeMillis() - 86400000
        dpd.show()
    }

}