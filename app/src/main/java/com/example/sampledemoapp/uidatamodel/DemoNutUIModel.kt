package com.example.sampledemoapp.uidatamodel

data class DemoNutUIModel(
    val desc: String,
    val id: String,
    val imgURL: String,
    val name: String
)

data class DemoNutDataModel(
    val city: String,
    val country: String,
    val id: String,
    val imgURL: String,
    val name: String
)