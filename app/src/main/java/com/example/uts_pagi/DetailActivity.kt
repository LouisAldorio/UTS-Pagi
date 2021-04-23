package com.example.uts_pagi

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*


class DetailActivity : AppCompatActivity() {

    var day = 0
    var month = 0
    var newYear = 0

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var pendingIntent: PendingIntent? = null
        var startIntent: Intent? = null
        var alarmManager: AlarmManager? = null

        val data = intent.getParcelableExtra<Cars>(CAR_OBJECT_DATA)

        detail_name.text = data?.name
        description.text = data?.description
        detail_kind.text = data?.kind
        price.text = data?.price.toString() + "/day"
        seats.text = data?.seats.toString()
        baggage.text = data?.baggage_amount.toString()
        Picasso.get().load(data?.photo).into(detail_photo)

        date.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val mYear: Int = c.get(Calendar.YEAR)
            val mMonth: Int = c.get(Calendar.MONTH)
            val mDay: Int = c.get(Calendar.DAY_OF_MONTH)

            var datePickerDialog = DatePickerDialog(this@DetailActivity, OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

                date.text = dayOfMonth.toString() + "/" + (monthOfYear + 1).toString() + "/" + year.toString()
                day = dayOfMonth
                month = monthOfYear
                newYear = year


                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()

        }

        val numbers = resources.getStringArray(R.array.number
        )

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, numbers)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@DetailActivity, numbers[position], Toast.LENGTH_SHORT).show()
                    val calculated = numbers[position].toInt() * intent.getParcelableExtra<Cars>(CAR_OBJECT_DATA)?.price!!
                    total.text = calculated.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        rent.setOnClickListener {
            val cal = Calendar.getInstance()
            cal[Calendar.DAY_OF_MONTH] = day
            cal[Calendar.MONTH] = month
            cal[Calendar.YEAR] = newYear

            var timeToWait = cal
            timeToWait.add(Calendar.SECOND,5)
            startIntent = Intent(this@DetailActivity, ScheduledAlarmReceiver::class.java)

            startIntent?.putExtra(ALARM_MANAGER_CHANNELID,"Daily reminder")
            startIntent?.putExtra(EXTRA_PESAN,day.toString() + "/" + (month + 1).toString() + "/" + newYear.toString())

            pendingIntent = PendingIntent.getBroadcast(this@DetailActivity,100,startIntent,0)

            alarmManager?.set(AlarmManager.RTC,timeToWait.timeInMillis,pendingIntent)
        }
    }
}