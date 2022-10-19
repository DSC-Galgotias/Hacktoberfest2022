package com.example.agetominutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate:TextView? = null
    private var finalInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonDatePicker = findViewById<Button>(R.id.button2)
        tvSelectedDate = findViewById(R.id.textview3)
        finalInMinutes = findViewById(R.id.finalInMinutes)
        buttonDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val date = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, SelectedDayOfMonth ->
                Toast.makeText(this, "$SelectedDayOfMonth/${selectedMonth+1}/$selectedYear",
                    Toast.LENGTH_LONG).show()
                val SelectedDate = "$SelectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text=SelectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH) //this will convert date which is in string datatype to selected date format
                val theDate= sdf.parse(SelectedDate)
                val selectedDateInMinutes = theDate.time / 60000 //here .time Returns the number of milliseconds since January 1, 1970, 00:00:00
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) //here System.currentTimeMillis() gives the time in millisecond from January 1, 1970, 00:00:00 to current time
                val currentDateInMinutes = currentDate.time / 60000
                val final = currentDateInMinutes - selectedDateInMinutes
                finalInMinutes?.text = final.toString()

            },
            year, month, date
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}

