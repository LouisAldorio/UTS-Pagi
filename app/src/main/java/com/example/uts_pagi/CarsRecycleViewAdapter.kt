package com.example.uts_pagi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.car_item_layout.view.*

class CarsRecycleViewAdapter(var context : Context,val cars: List<Cars>) : RecyclerView.Adapter<CarHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        return CarHolder(LayoutInflater.from(parent.context).inflate(R.layout.car_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        holder.bind(cars[position])

        holder.itemView.setOnClickListener {
            var intentDetail = Intent(context,DetailActivity::class.java)
            var car = Cars(
                cars[position].photo,
                cars[position].name,
                cars[position].kind,
                cars[position].price,
                cars[position].seats,
                cars[position].baggage_amount,
                cars[position].description
            )

            intentDetail.putExtra(CAR_OBJECT_DATA,car)
            context.startActivity(intentDetail)
        }
    }
}

class CarHolder (view: View) :RecyclerView.ViewHolder(view) {
    private val photo = view.photo
    private val name = view.name
    private val kind = view.kind
    private val description = view.description
    private val price = view.price
    private val seats = view.seat
    private val baggage = view.baggage

    fun bind(car : Cars){
        Picasso.get().load(car.photo).into(photo)
        name.text = car.name
        kind.text = car.kind
        description.text = car.description
        price.text = "Price :" +car.price.toString()
        seats.text = "Seats :" +car.seats.toString()
        baggage.text = "Bag :" + car.baggage_amount.toString()
    }
}