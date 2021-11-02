package com.example.weather.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.example.weather.R
import com.example.weather.databinding.ItemSpinnerBinding

class SpinnerAdapter(val data: List<String>) : BaseAdapter() {

    private var binding: ItemSpinnerBinding? = null

    override fun getCount(): Int = data.size

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_spinner, parent, false)
//        binding = ItemSpinnerBinding.bind(view)
//
//        binding!!.tvCity.text = data[position]

        view.findViewById<AppCompatTextView>(R.id.tv_City)
            .text = data[position]

        return view
    }
}