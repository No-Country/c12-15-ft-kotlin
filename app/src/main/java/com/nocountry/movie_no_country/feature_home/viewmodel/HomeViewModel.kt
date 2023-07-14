package com.nocountry.movie_no_country.feature_home.viewmodel

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nocountry.movie_no_country.feature_home.HomeAdapter
import com.nocountry.movie_no_country.feature_home.model.Cartelera
import com.nocountry.movie_no_country.feature_home.model.Results
import com.nocountry.movie_no_country.feature_home.service.DataProcess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(app:Application): AndroidViewModel(app) {
    val listCart = MutableLiveData<ArrayList<Cartelera>>()

    init {
        listCart.value= ArrayList()
    }

    fun getCarteleras(activity: Activity, adapter: HomeAdapter){
        CoroutineScope(Dispatchers.IO).launch {
            var res : Response<Results> = DataProcess().getCartelera()

            activity.runOnUiThread{
                if(res.isSuccessful){
                    Log.i("result",res.toString())
                    Log.i("result",res.body().toString())
                    var obj = res.body()?.cartelera
                    Log.i("result",obj?.size.toString())
                    listCart.value?.addAll(obj!!)
                    Log.i("result",listCart.value.toString())
                    adapter.notifyDataSetChanged()
                }

            }
        }
    }
}