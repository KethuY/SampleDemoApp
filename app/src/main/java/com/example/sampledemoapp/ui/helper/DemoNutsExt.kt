package com.example.sampledemoapp.ui.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import com.example.sampledemoapp.repository.data.DemoNutResponse
import com.example.sampledemoapp.uidatamodel.DemoNutDataModel
import com.example.sampledemoapp.utility.DemoNutHelper.filterNullOrBlank
import com.google.gson.Gson
import java.io.IOException

fun View.show() = View.VISIBLE

fun View.hide() = View.GONE

fun showGroupViews(vararg view: View) {
    view.forEach {
        it.show()
    }
}

fun hideGroupViews(vararg view: View) {
    view.forEach {
        it.hide()
    }
}

fun Context.hasInternet(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}

fun Context.getMockDemoNutsData(): List<DemoNutDataModel>? {
    try {
        val jsonString = assets.open("demonuts.json")
            .bufferedReader()
            .use { it.readText() }
        val resp = Gson().fromJson(
            jsonString,
            DemoNutResponse::class.java
        )
        return resp.data?.map {
            DemoNutDataModel(
                city = it.city.filterNullOrBlank(),
                country = it.country.filterNullOrBlank(),
                imgURL = it.imgURL.filterNullOrBlank(),
                id = it.id.filterNullOrBlank(),
                name = it.name.filterNullOrBlank()
            )
        }
    } catch (e: Exception) {
        return arrayListOf()
    }
}