package com.example.practical4

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    lateinit var textAlarm: TextView
    lateinit var cardSetAlarm: MaterialCardView
    lateinit var cardAlarm: MaterialCardView
    lateinit var txtAlarmTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textAlarm = findViewById(R.id.txtTitle)
        cardSetAlarm = findViewById(R.id.card1)
        cardAlarm = findViewById(R.id.card2)
        txtAlarmTime = findViewById(R.id.txtAlarmTime)

        // Hide alarm card initially
        cardAlarm.visibility = View.GONE

        findViewById<MaterialButton>(R.id.btnCreate).setOnClickListener {
            showTimeDialog()
        }

        findViewById<MaterialButton>(R.id.btnCancel).setOnClickListener {
            cardAlarm.visibility = View.GONE
        }
    }

    private fun showTimeDialog() {
        val calendar = Calendar.getInstance()

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val picker = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                sendDialogDataToActivity(selectedHour, selectedMinute)
            },
            hour,
            minute,
            false
        )

        picker.show()
    }

    private fun sendDialogDataToActivity(hour: Int, minute: Int) {

        val amPm = if (hour >= 12) "PM" else "AM"
        var displayHour = hour % 12
        if (displayHour == 0) displayHour = 12

        val time = String.format("%02d:%02d %s", displayHour, minute, amPm)

        txtAlarmTime.text = time

        // Show second card
        cardAlarm.visibility = View.VISIBLE
    }
}