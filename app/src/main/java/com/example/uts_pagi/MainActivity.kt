package com.example.uts_pagi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cars = mutableListOf<Cars>()
        cars.add(Cars(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpR68s6b1WPryXNro5mowjLUtl4dtXaOD3OQ&usqp=CAU",
            "Rolls Royce",
            "Sport",
            1000,
            2,
            1,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
        ))

        cars.add(Cars(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzJbkb6_JrGaTVshob6S9osVyn4TOMYd_KJA&usqp=CAU",
            "Ferrari",
            "Sport",
            2000,
            2,
            1,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,"
        ))

        rv_cars.apply {
            adapter = CarsRecycleViewAdapter(this@MainActivity,cars)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}