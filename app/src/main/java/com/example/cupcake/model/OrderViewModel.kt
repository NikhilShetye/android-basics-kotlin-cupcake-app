package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel: ViewModel() {
    private val _quantity=MutableLiveData<Int>()
    val quantity:LiveData<Int> get() = _quantity

    private val _flavor=MutableLiveData<String>()
    val flavour:LiveData<String> get() = _flavor

    private val _date=MutableLiveData<String>()
    val date:LiveData<String>   get() = _date

    private val _price=MutableLiveData<Double>()
    val price:LiveData<Double>  get() = _price

    fun setQuantity(numOfCupCakes:Int){
        _quantity.value=numOfCupCakes
    }
    fun setFlavour(desiredfalvour:String){
        _flavor.value=desiredfalvour
    }
    fun setDate(pickedDate:String){
        _date.value=pickedDate
    }

    val dateOptions=getPickupOptions()

    init {
        resetOrder()
    }


    fun hasNoFlavourSet():Boolean{
        return _flavor.value.isNullOrEmpty()
    }
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }
}