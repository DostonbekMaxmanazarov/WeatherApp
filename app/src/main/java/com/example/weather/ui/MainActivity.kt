package com.example.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.model.model.WeatherRoot
import com.example.weather.presenter.IMainActivityPresenter
import com.example.weather.presenter.IMainActivityView
import com.example.weather.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), IMainActivityView {

    private lateinit var binding: ActivityMainBinding

    private var adapterSpinner: SpinnerAdapter? = null

    private var presenter: IMainActivityPresenter? = null

    private var dialog: AlertDialog? = null

    private var countryName: String = "Uzbekistan"

    private var top: Animation? = null
    private var left: Animation? = null
    private var right: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        top = AnimationUtils.loadAnimation(this, R.anim.topsun)
        left = AnimationUtils.loadAnimation(this, R.anim.left)
        right = AnimationUtils.loadAnimation(this, R.anim.right)

        loadAnimation()

        adapterSpinner = SpinnerAdapter(loadData())
        binding.spinner.adapter = adapterSpinner
        binding.spinner.onItemSelectedListener = selectedItem

        presenter = MainActivityPresenter(this)
    }

    fun loadAnimation() {
        binding.mainLottie.animation = top
        binding.temp.animation = left
        binding.gradus.animation = left
        binding.mainText.animation = right
    }

    override fun onResume() {
        super.onResume()
        presenter?.loadWeather(countryName)
    }

    private fun loadData(): List<String> {
        val data = ArrayList<String>()

        data.add("Uzbekistan")
        data.add("Tashkent")
        data.add("Jizzakh")
        data.add("Navoiy")
        data.add("Samarkand")
        data.add("Bukhara")
        data.add("Qarshi")
        data.add("Shahrisabz")
        data.add("Namangan")
        data.add("Andijan")
        data.add("Kokand")
        return data
    }

    override fun sendWeather(weatherRoot: WeatherRoot) {
        binding.temp.text = weatherRoot.main.temp.toString()

        binding.maxTemp.text = weatherRoot.main.tempMax.toString()
        binding.minTemp.text = weatherRoot.main.tempMin.toString()


        binding.mainText.text = weatherRoot.weather[0].description

//        binding.mainText.text = weatherRoot.weather[0].main
        //Glide.with(this).load(weatherRoot.weather[0].icon).into(binding.iconWeather)

    }

    override fun onFail() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Fail")
            .setMessage("Something went wrong please try again")
            .create()
        alertDialog.show()

    }

    override fun showRefresh() {
        binding.refresh.setOnRefreshListener {
            presenter?.loadWeather(countryName)
        }
    }

    override fun hideRefresh() {
        binding.refresh.isRefreshing = false
    }

    private val selectedItem = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            countryName = parent?.getItemAtPosition(position).toString()
            presenter?.loadWeather(countryName)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            Toast.makeText(this@MainActivity, "No select", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter?.cancel()
    }

}